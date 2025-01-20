
//Librerias utilizadas:
import jBittorrentAPI.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author edwin
 */
public class Ventana_Crear_Torrent extends JFrame {

    /**
     * Creates new form Ventana_Crear_Torrent
     */
    public Ventana_Crear_Torrent() {
        //Agregamos el titulo a la venta
        setTitle("Crear Arcivo Torrent");
        initComponents();
        jPanel1.setBackground(new Color(255, 223, 186)); // Color de fondo (un tono suave de naranja)

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tf_Nombre_Archivo_Torrent = new javax.swing.JTextField();
        tf_Archivo_Seleccionado = new javax.swing.JTextField();
        tf_Ruta_Archivo_Seleccionado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tf_Numero_Piezas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_Nombre_Autor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_Descripccion_Archivo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tf_Direccion_IP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_Puerto = new javax.swing.JTextField();
        btn_Crear_Torrent = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JToggleButton();
        btn_Limpiar_Ventana = new javax.swing.JButton();
        btn_Seleccionar_Archivo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        tf_Ruta_guardado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Crear Torrent");

        tf_Archivo_Seleccionado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tf_Archivo_Seleccionado.setEnabled(false);

        tf_Ruta_Archivo_Seleccionado.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tf_Ruta_Archivo_Seleccionado.setEnabled(false);

        jLabel2.setText("N�mero de particiones:");

        jLabel3.setText("Autor:");
        jLabel3.setToolTipText("");

        jLabel4.setText("Descripci�n del archivo:");

        jLabel5.setText("Direcci�n IP:");

        jLabel6.setText("Puerto:");

        btn_Crear_Torrent.setText("Crear Torrent");
        btn_Crear_Torrent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clic_Crear_Torrent(evt);
            }
        });

        btn_Cancelar.setText("Cancelar");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clic_Cancelar(evt);
            }
        });

        btn_Limpiar_Ventana.setText("Limpiar");
        btn_Limpiar_Ventana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clic_Limpiar(evt);
            }
        });

        btn_Seleccionar_Archivo.setText("Seleccionar archivo:");
        btn_Seleccionar_Archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clic_Seleccionar_Archivo(evt);
            }
        });

        jLabel7.setText("Nombre del archivo:");

        jLabel8.setText("Ruta:");

        jButton1.setText("Ruta guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clic_ruta_guardar(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_Nombre_Archivo_Torrent, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(btn_Seleccionar_Archivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_Archivo_Seleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_Numero_Piezas, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_Nombre_Autor))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_Descripccion_Archivo))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_Ruta_Archivo_Seleccionado))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tf_Direccion_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(btn_Cancelar)
                                                .addGap(164, 164, 164)
                                                .addComponent(btn_Limpiar_Ventana)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btn_Crear_Torrent)
                                                .addContainerGap())
                                        .addComponent(tf_Puerto)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_Ruta_guardado))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tf_Nombre_Archivo_Torrent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tf_Archivo_Seleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btn_Seleccionar_Archivo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tf_Ruta_Archivo_Seleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(tf_Numero_Piezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(tf_Nombre_Autor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(tf_Descripccion_Archivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(tf_Direccion_IP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addComponent(tf_Puerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(tf_Ruta_guardado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btn_Cancelar)
                                        .addComponent(btn_Limpiar_Ventana)
                                        .addComponent(btn_Crear_Torrent))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Clic_Crear_Torrent(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Clic_Crear_Torrent
        //Empleamos un arrya list, con el cual vamos a manipular el teto de los tf
        ArrayList<String> atributos_torrent = new ArrayList<>();
        //Validemos que las cajas de texto no esten vacias
        if (tf_Archivo_Seleccionado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No seleccion ningun archuvo", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (tf_Nombre_Archivo_Torrent.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nombre del archivo .torrent no ingresado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (tf_Numero_Piezas.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No menciono el numero de piezas", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (tf_Puerto.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No ingreso el puerto de conexion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (tf_Direccion_IP.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No ingreso la direccion IP", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (tf_Ruta_guardado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No especifico la ruta de guardado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Agregamos lo atributos al ArrayList
        atributos_torrent.add(tf_Ruta_guardado.getText() + tf_Nombre_Archivo_Torrent.getText() + ".torrent");
        atributos_torrent.add("https://" + tf_Direccion_IP.getText() + ":" + tf_Puerto.getText() + "/announce");
        atributos_torrent.add(tf_Numero_Piezas.getText());
        atributos_torrent.add(tf_Archivo_Seleccionado.getText());
        atributos_torrent.add("");
        atributos_torrent.add(tf_Nombre_Autor.getText());
        atributos_torrent.add("");
        atributos_torrent.add(tf_Descripccion_Archivo.getText());
        //Declaramos un objeto para poder procesar el torrent
        TorrentProcessor creador_torrent = new TorrentProcessor();
        //Agregamos la URL
        creador_torrent.setAnnounceURL(atributos_torrent.get(1));
        //Agregamos el numero de piezas
        try {
            creador_torrent.setPieceLength(Integer.parseInt(atributos_torrent.get(2)));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El numero de piezas debe ser >= 1", "Error", JOptionPane.ERROR_MESSAGE);
        }
        int bandera = 3;
        //Agregamos los archivos a un ArrayList
        ArrayList<String> archivos = new ArrayList<String>();
        //Ingnoramos los atributos nulos y no esenciales
        if (!atributos_torrent.get(bandera + 1).equalsIgnoreCase("")) {
            creador_torrent.setName(atributos_torrent.get(3));
            bandera++;
        }
        //Agregamos los archivos al ArryList archivos
        while (bandera < atributos_torrent.size()) {
            if (atributos_torrent.get(bandera).equalsIgnoreCase("")) {
                break;
            }
            archivos.add(atributos_torrent.get(bandera));
            bandera++;
        }
        try {
            //Validamos que los archivos agregados y asigamos al creador tracker
            creador_torrent.addFiles(archivos);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar archivos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        bandera++;
        String creador = null;
        while (bandera < atributos_torrent.size()) {
            if (atributos_torrent.get(bandera).equalsIgnoreCase("")) {
                break;
            }
            creador += atributos_torrent.get(bandera);
            bandera++;
        }
        creador_torrent.setCreator(creador);
        bandera++;
        String comment = "";
        while (bandera < atributos_torrent.size()) {
            if (atributos_torrent.get(bandera).equalsIgnoreCase("")) {
                break;
            }
            comment += atributos_torrent.get(bandera);
            bandera++;
        }
        creador_torrent.setComment(comment);
        try {
            System.out.println("Hash en proceso...");
            creador_torrent.generatePieceHashes();
            System.out.println("Hash finalizado!");
            FileOutputStream fos = new FileOutputStream(atributos_torrent.get(0));
            fos.write(creador_torrent.generateTorrent());
            JOptionPane.showConfirmDialog(null, "Archivo Torrent Creado", "Archivo Torrent", JOptionPane.OK_OPTION);
            fos.close();
        } catch (Exception ex) {
            System.err.println(ex);
            JOptionPane.showMessageDialog(null, "Error al crear archivos torrent", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Clic_Crear_Torrent

    private void clic_Seleccionar_Archivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clic_Seleccionar_Archivo
        //Declaramos un objeto de la clase JFileChooser
        JFileChooser seleccionar = new JFileChooser();
        //Establecemos el direcctorio
        seleccionar.setCurrentDirectory(new java.io.File("."));
        seleccionar.setDialogTitle("Seleccione la ruta del archivo");
        //Empleamos una sentencia if-else, con la finalidad de validar la seleccion del usuario:
        if (seleccionar.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            //Mostramos el archivo seleccionado:
            tf_Archivo_Seleccionado.setText(String.valueOf(seleccionar.getName(seleccionar.getSelectedFile())));
            //Agregamos la ruta a la caja de texto:
            tf_Ruta_Archivo_Seleccionado.setText(String.valueOf(seleccionar.getSelectedFile()));
            tf_Ruta_Archivo_Seleccionado.setText(tf_Ruta_Archivo_Seleccionado.getText().replaceAll(tf_Archivo_Seleccionado.getText(), ""));
        } else {
            //Si el usuario no seleeciono una ruta, mostraremos un mensaje de dialogo:
            JOptionPane.showMessageDialog(null, "No ha seleccionado un archivo","Seleecione un archivo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_clic_Seleccionar_Archivo

    private void clic_ruta_guardar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clic_ruta_guardar
        // Evento que asigna la ruta donde se guardar�n los archivos torrent
        //Declaramos un objeto de la clase JFileChooser
        JFileChooser seleccionar = new JFileChooser();
        //Establecemos el direcctorio
        seleccionar.setCurrentDirectory(new java.io.File("."));
        //Establecemos que solo se puedan seleccionar directorios
        seleccionar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //Con un filtro quitamos la opcion de ver archivos
        seleccionar.setAcceptAllFileFilterUsed(false);
        seleccionar.setDialogTitle("Seleccione la ruta a guardar torrent");
        //Empleamos una sentencia if-else, con la finalidad de validar la seleccion del usuario:
        if (seleccionar.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            tf_Ruta_guardado.setText(String.valueOf(seleccionar.getSelectedFile() + "\\"));
        } else {
            JOptionPane.showMessageDialog(null, "Error, seleccione una ruta", "Error al seleccionar suta", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_clic_ruta_guardar

    private void clic_Limpiar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clic_Limpiar
        //Limpia las cajas de texto
        tf_Ruta_guardado.setText(null);
        tf_Archivo_Seleccionado.setText(null);
        tf_Descripccion_Archivo.setText(null);
        tf_Direccion_IP.setText(null);
        tf_Nombre_Archivo_Torrent.setText(null);
        tf_Nombre_Autor.setText(null);
        tf_Numero_Piezas.setText(null);
        tf_Puerto.setText(null);
        tf_Ruta_Archivo_Seleccionado.setText(null);
        JOptionPane.showMessageDialog(null, "Ventana limpia", "Limpieza en proceso", JOptionPane.OK_OPTION);
    }//GEN-LAST:event_clic_Limpiar

    private void clic_Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clic_Cancelar
        System.exit(0);
    }//GEN-LAST:event_clic_Cancelar

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana_Crear_Torrent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Crear_Torrent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Crear_Torrent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Crear_Torrent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Crear_Torrent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_Cancelar;
    private javax.swing.JButton btn_Crear_Torrent;
    private javax.swing.JButton btn_Limpiar_Ventana;
    private javax.swing.JButton btn_Seleccionar_Archivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tf_Archivo_Seleccionado;
    private javax.swing.JTextField tf_Descripccion_Archivo;
    private javax.swing.JTextField tf_Direccion_IP;
    private javax.swing.JTextField tf_Nombre_Archivo_Torrent;
    private javax.swing.JTextField tf_Nombre_Autor;
    private javax.swing.JTextField tf_Numero_Piezas;
    private javax.swing.JTextField tf_Puerto;
    private javax.swing.JTextField tf_Ruta_Archivo_Seleccionado;
    private javax.swing.JTextField tf_Ruta_guardado;
    // End of variables declaration//GEN-END:variables
}