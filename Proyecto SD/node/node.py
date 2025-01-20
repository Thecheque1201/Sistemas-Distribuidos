import requests
import os
import hashlib
import bencodepy
from tqdm import tqdm
import threading
import time

# Función para pedir al usuario la IP y puerto del tracker
def get_tracker_info():
    ip = input("Ingrese la dirección IP del tracker: ")
    port = input("Ingrese el puerto del tracker: ")
    return f"http://{ip}:{port}"

# Obtener la dirección del tracker
tracker_url = get_tracker_info()
upload_url = f"{tracker_url}/upload"
files_url = f"{tracker_url}/files"
get_torrent_url = f"{tracker_url}/get_torrent"
upload_torrent_url = f"{tracker_url}/upload_torrent"

# Directorios donde se almacenan los archivos .torrent generados
TORRENT_DIR = '/home/ubuntu/Proyecto SD/node/torrents'
SHARED_FILES_DIR = '/home/ubuntu/Proyecto SD/node/shared_files'
TORRENTS_DOWNLOADED_DIR = '/home/ubuntu/Proyecto SD/node/torrents_downloaded'  # Para los torrents descargados

# Asegúrate de que los directorios existan
if not os.path.exists(TORRENT_DIR):
    os.makedirs(TORRENT_DIR)

if not os.path.exists(SHARED_FILES_DIR):
    os.makedirs(SHARED_FILES_DIR)

if not os.path.exists(TORRENTS_DOWNLOADED_DIR):
    os.makedirs(TORRENTS_DOWNLOADED_DIR)

# Información de archivos y piezas
files_info = {}

# Función para obtener los peers de un archivo
def get_peers(file_name):
    try:
        response = requests.get(tracker_url, params={'file': file_name})
        if response.status_code == 200:
            data = response.json()
            return data.get('peers', {})
        else:
            print(f"Error al obtener peers para {file_name}. Código de estado: {response.status_code}")
            return {}
    except requests.exceptions.RequestException as e:
        print(f"Error al hacer la solicitud de obtener peers: {e}")
        return {}

# Función para cargar un archivo torrent en el tracker con barra de progreso
def upload_torrent_file(torrent_name):
    try:
        torrent_file_path = os.path.join(TORRENT_DIR, torrent_name)
        with open(torrent_file_path, 'rb') as f:
            total_size = os.path.getsize(torrent_file_path)
            
            with tqdm(
                desc=f"Subiendo {torrent_name}",
                total=total_size,
                unit='B',
                unit_scale=True
            ) as bar:
                response = requests.post(upload_torrent_url, files={'torrent': f}, stream=True)
                
                if response.status_code == 200:
                    print(f"Archivo torrent {torrent_name} subido correctamente.")
                else:
                    print("Error al subir el archivo torrent.")
    except Exception as e:
        print(f"Error al intentar subir el archivo torrent: {e}")

# Función para generar un archivo torrent
def generate_torrent(file_path, piece_length=512 * 1024):
    if not os.path.exists(file_path):
        print(f"Error: El archivo '{file_path}' no existe.")
        return None
    
    file_name = os.path.basename(file_path)
    file_size = os.path.getsize(file_path)
    num_pieces = (file_size + piece_length - 1) // piece_length  # Número de piezas

    print(f"\nGenerando archivo torrent para '{file_name}':")
    print(f"  Tamaño del archivo: {file_size} bytes")
    print(f"  Tamaño de cada pieza: {piece_length} bytes")
    print(f"  Número total de piezas: {num_pieces}\n")

    pieces = []
    with open(file_path, 'rb') as f, tqdm(
        desc=f"Generando {file_name}",
        total=num_pieces,
        unit='pieza',
        unit_scale=True
    ) as bar:
        while True:
            piece = f.read(piece_length)
            if not piece:
                break
            pieces.append(hashlib.sha1(piece).digest())
            bar.update(1)

    torrent_info = {
        'announce': tracker_url,  # URL del tracker
        'info': {
            'name': file_name,
            'piece length': piece_length,
            'pieces': b''.join(pieces),
            'length': file_size,
            'files': [{
                'path': [file_name],
                'length': file_size
            }]
        }
    }

    torrent_file_name = os.path.join(TORRENT_DIR, f"{file_name}.torrent")
    with open(torrent_file_name, 'wb') as f:
        f.write(bencodepy.encode(torrent_info))
    
    print(f"Archivo torrent '{torrent_file_name}' generado correctamente.")
    return torrent_file_name

# Función para descargar un archivo torrent
def download_torrent(torrent_name):
    if not os.path.exists(TORRENTS_DOWNLOADED_DIR):
        os.makedirs(TORRENTS_DOWNLOADED_DIR)
    
    print(f"Descargando archivo torrent: {torrent_name}")
    response = requests.get(f"{get_torrent_url}?torrent={torrent_name}", stream=True)
    if response.status_code == 200:
        total_size = int(response.headers.get('Content-Length', 0))  # Tamaño total del archivo
        with open(f"{TORRENTS_DOWNLOADED_DIR}/{torrent_name}", 'wb') as f, tqdm(
            desc=torrent_name,
            total=total_size,
            unit='B', unit_scale=True
        ) as bar:
            for data in response.iter_content(chunk_size=1024):
                f.write(data)
                bar.update(len(data))  # Actualizar la barra de progreso

        print(f"Archivo torrent {torrent_name} descargado correctamente.")
    else:
        print("Error al descargar el archivo torrent.")

# Función para visualizar los archivos en el directorio "torrents"
def list_generated_torrents():
    print("\n--- Archivos en el directorio 'torrents' (Torrents creados) ---")
    if os.path.exists(TORRENT_DIR):
        files = os.listdir(TORRENT_DIR)
        if files:
            for file in files:
                print(f"- {file}")
        else:
            print("No hay archivos en el directorio 'torrents'.")
    else:
        print("El directorio 'torrents' no existe.")

# Función para visualizar los archivos descargados
def list_downloaded_torrents():
    print("\n--- Archivos descargados en el directorio 'torrents_downloaded' ---")
    if os.path.exists(TORRENTS_DOWNLOADED_DIR):
        files = os.listdir(TORRENTS_DOWNLOADED_DIR)
        if files:
            for file in files:
                print(f"- {file}")
        else:
            print("No hay archivos descargados en el directorio 'torrents_downloaded'.")
    else:
        print("El directorio 'torrents_downloaded' no existe.")

# Función para consultar nodos conectados
def get_connected_nodes():
    try:
        response = requests.get(f"{tracker_url}/nodes")
        if response.status_code == 200:
            nodes = response.json()
            print("\n--- Nodos Conectados ---")
            for node, data in nodes['nodes'].items():
                print(f"IP: {node}, Puerto: {data['port']}, Archivos: {data['torrents']}, Estado: {data['status']}")
        else:
            print("Error al obtener nodos conectados.")
    except requests.exceptions.RequestException as e:
        print(f"Error de conexión con el tracker: {e}")

# Menú interactivo
def menu():
    while True:
        print("\nMenú de opciones:")
        print("1. Subir un archivo torrent")
        print("2. Descargar un archivo torrent")
        print("3. Generar un archivo torrent")
        print("4. Ver archivos torrent generados")  # Torrents creados
        print("5. Ver nodos conectados")
        print("6. Ver archivos descargados")  # Torrents descargados
        print("7. Salir")

        choice = input("Elija una opción: ")

        if choice == '1':
            torrent_name = input("Ingrese el nombre del archivo torrent a subir: ")
            upload_torrent_file(torrent_name)
        elif choice == '2':
            torrent_name = input("Ingrese el nombre del archivo torrent a descargar: ")
            download_torrent(torrent_name)
        elif choice == '3':
            print("\n--- Generar un archivo .torrent ---")
            file_name = input("Ingrese el nombre del archivo en 'shared_files/' que desea generar: ")
            file_path = f"shared_files/{file_name}"
            generate_torrent(file_path)
        elif choice == '4':
            list_generated_torrents()  # Ver torrents creados
        elif choice == '5':
            get_connected_nodes()  # Consultar nodos conectados
        elif choice == '6':
            list_downloaded_torrents()  # Ver archivos descargados
        elif choice == '7':
            print("Saliendo...")
            break
        else:
            print("Opción no válida. Intente de nuevo.")

if __name__ == "__main__":
    menu()
