import os
import threading
import time
import bencodepy
from collections import defaultdict
from flask import Flask, request, jsonify, send_from_directory

app = Flask(__name__)

# Directorios donde se almacenan los archivos .torrent generados
TORRENT_DIR = '/home/ubuntu/Proyecto SD/tracker/torrents'
SHARED_FILES_DIR = '/home/ubuntu/Proyecto SD/tracker/shared_files'

# Asegúrate de que los directorios existan
if not os.path.exists(TORRENT_DIR):
    os.makedirs(TORRENT_DIR)

if not os.path.exists(SHARED_FILES_DIR):
    os.makedirs(SHARED_FILES_DIR)

# Información de archivos y piezas
files_info = {}

# Información de Nodos conectados
connected_nodes = defaultdict(lambda: {'port': None, 'torrents': [], 'status': 'peer'})  # IP => {port, torrents, status}

# Función para registrar la actividad de un nodo (como un "ping")
def log_node_activity(peer_ip, peer_port, action):
    timestamp = time.strftime("%Y-%m-%d %H:%M:%S", time.gmtime())  # Obtener la hora actual
    print(f"[{timestamp}] Nodo {peer_ip} en puerto {peer_port} realizó la acción: {action}")

@app.route('/announce', methods=['GET'])
def announce():
    file_name = request.args.get('file')
    peer_ip = request.args.get('peer_ip')  # IP del Nodo
    peer_port = request.args.get('port')  # Puerto del Nodo
    peer_status = request.args.get('status')  # Estado del Nodo: seeder, leecher, peer

    # Registrar la actividad del nodo (como un ping)
    log_node_activity(peer_ip, peer_port, f"Consulta sobre archivo: {file_name}")

    # Registrar el Nodo si no está registrado
    if peer_ip not in connected_nodes:
        print(f"\n[Nuevo Nodo conectado] IP: {peer_ip}, Puerto: {peer_port}, Estado: {peer_status}")
        connected_nodes[peer_ip] = {
            'port': peer_port,
            'torrents': [],
            'status': peer_status if peer_status else 'peer'
        }

    # Añadir el archivo compartido a la lista de torrents del nodo
    if file_name and file_name not in connected_nodes[peer_ip]['torrents']:
        connected_nodes[peer_ip]['torrents'].append(file_name)

    # Devolver los peers que tienen el archivo solicitado
    peers = {}
    for ip, data in connected_nodes.items():
        if file_name in data['torrents']:
            peers[ip] = {'port': data['port'], 'torrents': data['torrents'], 'status': data['status']}

    # Mostrar los nodos conectados y su estado en la consola del tracker
    print("\n[Estado de los nodos conectados]:")
    for ip, data in connected_nodes.items():
        print(f"  - Nodo {ip}: Puerto {data['port']}, Estado: {data['status']}, Archivos compartidos: {data['torrents']}")

    # Verificar si el archivo está en files_info
    if file_name in files_info:
        return jsonify({'peers': peers, 'file': file_name, 'file_size': files_info[file_name]['length']})
    else:
        return jsonify({'error': 'Archivo no encontrado'}), 404

@app.route('/upload_torrent', methods=['POST'])
def upload_torrent():
    if 'torrent' not in request.files:
        return jsonify({'error': 'No se ha subido un archivo torrent'}), 400
    torrent = request.files['torrent']
    torrent_name = torrent.filename
    file_path = os.path.join(TORRENT_DIR, torrent_name)

    # Leer el archivo y mostrar barra de progreso
    torrent_content = torrent.read()  # Leer todo el contenido del archivo subido
    total_size = len(torrent_content)  # Calculamos el tamaño total del archivo
    torrent.seek(0)  # Volver al inicio del archivo

    # Intenta decodificar el archivo .torrent usando bencodepy
    try:
        torrent_info = bencodepy.decode(torrent_content)
        print(f"Archivo .torrent decodificado correctamente: {torrent_info}")
    except Exception as e:
        print(f"Error al decodificar el archivo torrent: {e}")
        return jsonify({'error': 'Error al decodificar el archivo torrent'}), 400

    # Verificar que 'info' esté presente en los datos decodificados
    if b'info' not in torrent_info:
        return jsonify({'error': 'Archivo torrent inválido. Falta la clave "info".'}), 400

    # Accede correctamente a la información dentro de 'info'
    file_name = torrent_info[b'info'][b'name'].decode('utf-8')
    piece_length = torrent_info[b'info'][b'piece length']
    pieces = torrent_info[b'info'][b'pieces']
    length = torrent_info[b'info'][b'length']

    # Guardar información del archivo en files_info
    files_info[file_name] = {
        'piece_length': piece_length,
        'pieces': pieces,
        'length': length
    }

    # Guardar el archivo torrent en el sistema
    with open(file_path, 'wb') as f:
        f.write(torrent_content)

    print(f"Archivo torrent {torrent_name} subido correctamente.")
    return jsonify({'message': f'Archivo torrent {torrent_name} subido correctamente'}), 200

@app.route('/get_torrent', methods=['GET'])
def get_torrent():
    torrent_name = request.args.get('torrent')
    file_path = os.path.join(TORRENT_DIR, torrent_name)

    # Verifica si el archivo torrent existe en el directorio
    if os.path.exists(file_path):
        print(f"Archivo torrent {torrent_name} encontrado, enviando...")
        return send_from_directory(TORRENT_DIR, torrent_name, as_attachment=True)
    else:
        print(f"Archivo torrent {torrent_name} no encontrado en {file_path}")
        return jsonify({'error': 'Archivo torrent no encontrado'}), 404

@app.route('/files', methods=['GET'])
def list_files():
    # Mostrar nodos conectados y sus torrents
    print("\n[Estado de los nodos conectados y sus archivos]:")
    for ip, data in connected_nodes.items():
        print(f"  - Nodo {ip}: Puerto {data['port']}, Archivos: {data['torrents']}, Estado: {data['status']}")
    
    return jsonify({'nodes': connected_nodes})

@app.route('/nodes', methods=['GET'])
def get_connected_nodes():
    """Ruta para devolver los nodos conectados."""
    return jsonify({'nodes': connected_nodes})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)
