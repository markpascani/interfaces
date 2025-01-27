package com.mycompany.vista;

import com.mycompany.controlador.Controlador;
import com.mycompany.modelo.dao.clases.ClienteDAOImpl;
import com.mycompany.modelo.entidades.Cliente;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author alumno
 */
public class Clientes extends javax.swing.JFrame implements IVista<Cliente> {

    private final Controlador controlador;
    private MODO modo = MODO.INACTIVO;

    public enum MODO {
        ALTA,
        BAJA,
        MODIFICACION,
        INACTIVO
    }

    public Clientes(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
        estadoInicial();

        //Restricciones
        limitarEntradaACifraConLongitudExacta(campoCP, 5);
        limitarEntradaACifraConLongitudExacta(campoNIF, 8);
        limitarEntradaACifraConLongitudExacta(campoTelefono, 9);
        limitarEntradaACifraConLongitudExacta(campoFax, 9);
        limitarEntradaACifraConLongitudExacta(campoMovil, 9);
        limitarEntradaLetrasConLongitudMinYMax(campoNombre, 2, 15);
        limitarEntradaLetrasConLongitudMinYMax(campoApellidos, 2, 35);
        limitarEntradaLetrasConLongitudMinYMax(campoLocalidad, 2, 20);
        limitarEntradaALongitudMinYMax(campoDomicilio, 5, 40);
        comprobarEmail(campoEmail);

        // Listener de Aceptar
        botonAceptar.addActionListener(e -> {
            switch (modo) {
                case ALTA:
                    if (comprobarCampos()) {

                        controlador.altaCliente();
                    }

                    break;
                case BAJA:
                    if (comprobarCampos()) {

                        controlador.bajaCliente();
                    }
                    break;
                case MODIFICACION:
                    if (comprobarCampos()) {

                        controlador.modificarCliente();
                    }
                    break;
                default:
                    mostrarMensaje("No se ha seleccionado una operación.");
            }
            modo = MODO.INACTIVO;
            cancelarAccion();
        });

        botonSalir.addActionListener(e -> {
            estadoInicial();
        });

        botonCancelar.addActionListener(e -> {
            cancelarAccion();
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoCodigo = new javax.swing.JTextField();
        campoNIF = new javax.swing.JTextField();
        letraNif = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoApellidos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoDomicilio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        campoCP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        campoLocalidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        campoTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        campoMovil = new javax.swing.JTextField();
        campoTotalVentas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        campoFax = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMantenimiento = new javax.swing.JMenu();
        botonAlta = new javax.swing.JMenuItem();
        botonBaja = new javax.swing.JMenuItem();
        botonEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        botonVolver = new javax.swing.JMenuItem();
        menuConsultas = new javax.swing.JMenu();
        menuPorCodigo = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        campoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodigoKeyPressed(evt);
            }
        });

        jLabel1.setText("Código");

        jLabel2.setText("NIF");

        jLabel3.setText("Nombre");

        jLabel4.setText("Apellidos");

        jLabel5.setText("Domicilio");

        jLabel6.setText("Teléfono");

        jLabel7.setText("Localidad");

        jLabel8.setText("Código Postal");

        jLabel9.setText("Movil");

        jLabel10.setText("Fax");

        jLabel11.setText("e-mail");

        jLabel12.setText("Total ventas");

        botonAceptar.setText("Aceptar");
        botonAceptar.setEnabled(false);

        botonSalir.setText("Salir");
        botonSalir.setEnabled(false);

        botonCancelar.setText("Cancelar");
        botonCancelar.setEnabled(false);

        menuMantenimiento.setText("Mantenimiento");
        menuMantenimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuMantenimientoMousePressed(evt);
            }
        });

        botonAlta.setText("Altas");
        botonAlta.setVerifyInputWhenFocusTarget(false);
        botonAlta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonAltaMousePressed(evt);
            }
        });
        menuMantenimiento.add(botonAlta);

        botonBaja.setText("Bajas");
        botonBaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonBajaMousePressed(evt);
            }
        });
        menuMantenimiento.add(botonBaja);

        botonEditar.setText("Modificaciones");
        menuMantenimiento.add(botonEditar);
        menuMantenimiento.add(jSeparator1);

        botonVolver.setText("Volver");
        menuMantenimiento.add(botonVolver);

        jMenuBar1.add(menuMantenimiento);

        menuConsultas.setText("Consultas");

        menuPorCodigo.setText("Por código");
        menuConsultas.add(menuPorCodigo);

        jMenu1.setText("Listados");

        jMenuItem3.setText("Por código");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Entre códigos");
        jMenu1.add(jMenuItem4);

        jMenuItem2.setText("Gráficos");
        jMenu1.add(jMenuItem2);

        menuConsultas.add(jMenu1);

        jMenuBar1.add(menuConsultas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(campoDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(campoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(letraNif, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campoCP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campoTelefono, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(campoLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoMovil, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(campoFax, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(campoTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(letraNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoMovil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoTotalVentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar)
                    .addComponent(botonSalir)
                    .addComponent(botonCancelar))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAltaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAltaMousePressed
        // TODO add your handling code here:

        //Se limpian los campos y se deja habilitado solo el campo codigo
        modo = MODO.ALTA;
        cancelarAccion();
    }//GEN-LAST:event_botonAltaMousePressed

    private void botonBajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBajaMousePressed
        // TODO add your handling code here:
        //Se limpian los campos y se deja habilitado solo el campo codigo
        modo = MODO.BAJA;
        cancelarAccion();
    }//GEN-LAST:event_botonBajaMousePressed

    private void campoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodigoKeyPressed
        // TODO add your handling code here:
        boolean existeCodigo;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !campoCodigo.getText().isEmpty()) {
            switch (modo) {
                case BAJA:
                    if (controlador.comprobarExistenciaDelCliente()) {
                        controlador.activarFormulario(true);
                    }
                    break;
                case MODIFICACION:
                    if (controlador.comprobarExistenciaDelCliente()) {
                        controlador.activarFormulario(true);
                    }
                    break;

                case ALTA:
                    if (!controlador.comprobarExistenciaDelCliente()) {
                        controlador.activarFormulario(true);
                    }
                    break;
                // INACTIVO no hace nada en Enter.
                default:
                    break;
            }
        }
    }//GEN-LAST:event_campoCodigoKeyPressed

    private void menuMantenimientoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMantenimientoMousePressed
        // TODO add your handling code here:
        modo = MODO.MODIFICACION;
        cancelarAccion();
    }//GEN-LAST:event_menuMantenimientoMousePressed

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
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JMenuItem botonAlta;
    private javax.swing.JMenuItem botonBaja;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JMenuItem botonEditar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JMenuItem botonVolver;
    private javax.swing.JTextField campoApellidos;
    private javax.swing.JTextField campoCP;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoDomicilio;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoFax;
    private javax.swing.JTextField campoLocalidad;
    private javax.swing.JTextField campoMovil;
    private javax.swing.JTextField campoNIF;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JTextField campoTelefono;
    private javax.swing.JTextField campoTotalVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField letraNif;
    private javax.swing.JMenu menuConsultas;
    private javax.swing.JMenu menuMantenimiento;
    private javax.swing.JMenuItem menuPorCodigo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void limpiarCampos() {
        campoApellidos.setText("");
        campoNombre.setText("");
        letraNif.setText("");
        campoNIF.setText("");
        campoCodigo.setText("");
        campoDomicilio.setText("");
        campoLocalidad.setText("");
        campoCP.setText("");
        campoTelefono.setText("");
        campoMovil.setText("");
        campoFax.setText("");
        campoTotalVentas.setText("");
        campoEmail.setText("");
    }

    @Override
    public void estadoCampos(boolean estado) {
        campoApellidos.setEnabled(estado);
        campoNombre.setEnabled(estado);
        letraNif.setEnabled(estado);
        campoDomicilio.setEnabled(estado);
        campoLocalidad.setEnabled(estado);
        campoCP.setEnabled(estado);
        campoTelefono.setEnabled(estado);
        campoMovil.setEnabled(estado);
        campoFax.setEnabled(estado);

        botonAceptar.setEnabled(estado);
        botonCancelar.setEnabled(estado);
        botonSalir.setEnabled(estado);
        campoEmail.setEnabled(estado);
        campoNIF.setEnabled(estado);

        if (modo == MODO.INACTIVO) {
            campoCodigo.setEnabled(estado);
        } else {
            campoCodigo.setEnabled(!estado);
        }

        if (estado = false) {
            campoCodigo.grabFocus();
        } else {
            campoApellidos.grabFocus();
        }

    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public Cliente obtenerClienteDeFormulario() {
        // Construyes un Cliente con los datos actuales de la vista
        Cliente c = new Cliente();
        // Ojo con el parseo de campos numéricos
        try {
            c.setCodigo(Integer.parseInt(campoCodigo.getText()));
        } catch (NumberFormatException e) {
            // Manejo de error si no es número
            c.setCodigo(-1);
        }

        c.setNif(campoNIF.getText() + letraNif.getText());
        c.setNombre(campoNombre.getText());
        c.setApellidos(campoApellidos.getText());
        c.setDomicilio(campoDomicilio.getText());
        c.setLocalidad(campoLocalidad.getText());
        c.setCodigoPostal(campoCP.getText());
        c.setTelefono(campoTelefono.getText());
        c.setMovil(campoMovil.getText());
        c.setFax(campoFax.getText());
        c.setEmail(campoEmail.getText());

        try {
            c.setTotalVentas(Float.parseFloat(campoTotalVentas.getText()));
        } catch (NumberFormatException e) {
            c.setTotalVentas(0);
        }
        return c;
    }

    @Override
    public void mostrarCliente(Cliente entidad) {
        if (entidad == null) {
            return;
        }
        // Rellena los campos con los datos del cliente
        campoCodigo.setText(String.valueOf(entidad.getCodigo()));

        String nifCompleto = entidad.getNif();
        campoNIF.setText(nifCompleto.substring(0, 8));   // las 8 cifras
        letraNif.setText(nifCompleto.substring(8));      // la letra
        campoNombre.setText(entidad.getNombre());
        campoApellidos.setText(entidad.getApellidos());
        campoDomicilio.setText(entidad.getDomicilio());
        campoLocalidad.setText(entidad.getLocalidad());
        campoCP.setText(entidad.getCodigoPostal());
        campoTelefono.setText(entidad.getTelefono());
        campoMovil.setText(entidad.getMovil());
        campoFax.setText(entidad.getFax());
        campoEmail.setText(entidad.getEmail());
        campoTotalVentas.setText(String.valueOf(entidad.getTotalVentas()));
    }

    @Override
    public int obtenerCodigoCliente() {
        // Lee el campo y devuelve un entero
        try {
            return Integer.parseInt(campoCodigo.getText());
        } catch (NumberFormatException e) {
            return -1; // valor para indicar error
        }
    }

    @Override
    public void estadoInicial() {
        modo = MODO.INACTIVO;
        cancelarAccion();
        campoTotalVentas.setEnabled(false);
    }

    @Override
    public void cancelarAccion() {
        limpiarCampos();
        estadoCampos(false);
    }

    @Override
    public boolean comprobarCampos() {
        // 1) NIF: exactamente 8 caracteres
        if (campoNIF.getText().length() != 8) {
            mostrarMensaje("El NIF debe tener exactamente 8 dígitos.");
            return false;
        }

        // 2) Código Postal: 5 exactas
        if (campoCP.getText().length() != 5) {
            mostrarMensaje("El código postal debe tener 5 cifras.");
            return false;
        }

        // 3) Teléfono, Móvil y Fax: 0 (vacío) o 9 dígitos
        //    si quieres permitir que estén en blanco, asume length = 0 es válido
        int telLength = campoTelefono.getText().length();
        if (telLength != 0 && telLength != 9) {
            mostrarMensaje("El teléfono debe tener 9 dígitos o estar vacío.");
            return false;
        }

        int movilLength = campoMovil.getText().length();
        if (movilLength != 0 && movilLength != 9) {
            mostrarMensaje("El móvil debe tener 9 dígitos o estar vacío.");
            return false;
        }

        int faxLength = campoFax.getText().length();
        if (faxLength != 0 && faxLength != 9) {
            mostrarMensaje("El fax debe tener 9 dígitos o estar vacío.");
            return false;
        }

        // 4) Email: no más de 40
        if (campoEmail.getText().length() > 40) {
            mostrarMensaje("El correo electrónico no puede tener más de 40 caracteres.");
            return false;
        }

        // 5) Localidad: no más de 20
        if (campoLocalidad.getText().length() > 20) {
            mostrarMensaje("La localidad no puede tener más de 20 caracteres.");
            return false;
        }

        // 6) Domicilio: no más de 40
        if (campoDomicilio.getText().length() > 40) {
            mostrarMensaje("El domicilio no puede tener más de 40 caracteres.");
            return false;
        }

        // 7) Nombre: no más de 15
        if (campoNombre.getText().length() > 15) {
            mostrarMensaje("El nombre no puede tener más de 15 caracteres.");
            return false;
        }

        // 8) Apellidos: no más de 35
        if (campoApellidos.getText().length() > 35) {
            mostrarMensaje("Los apellidos no pueden tener más de 35 caracteres.");
            return false;
        }

        // Si llegamos hasta aquí, todo cumple
        return true;
    }

    @Override
    public void limitarEntradaLetrasConLongitudMinYMax(JTextField campo, int min, int max) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Si no es letra, lo bloqueamos
                if (!Character.isLetter(c)) {
                    e.consume();
                    return;
                }
                // Si ya alcanzamos la longitud máxima, bloqueamos
                if (campo.getText().length() >= max) {
                    e.consume();
                }
            }
        });
    }

    @Override
    public void limitarEntradaACifraConLongitudExacta(JTextField campo, int longitud) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Bloqueamos si no es dígito
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                    return;
                }
                // Bloqueamos si alcanzamos la longitud exacta
                if (campo.getText().length() >= longitud) {
                    e.consume();
                }
            }
        });
    }

    @Override
    public void comprobarEmail(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = campo.getText().trim();
                // Patrón mínimo de email (muy básico)
                String patron = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                if (!texto.matches(patron)) {
                    // Muestra alerta, colorea el campo, etc.
                    // o se lo dejas al final, depende de tu necesidad
                    campo.setBackground(Color.PINK);
                } else {
                    campo.setBackground(Color.WHITE);
                }
            }
        });
    }

    @Override
    public void limitarEntradaALongitudMinYMax(JTextField campo, int min, int max) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Si llegamos a la longitud máxima, bloqueamos
                if (campo.getText().length() >= max) {
                    e.consume();
                }
            }
        });
    }

    @Override
    public int obtenerCifrasNIFCliente() {
        int cifras = 0;
        try {
            cifras = Integer.parseInt(campoNIF.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return cifras;
    }

    @Override
    public void establecerLetraNIF(String letra) {
        letraNif.setText(letra);
    }
}
