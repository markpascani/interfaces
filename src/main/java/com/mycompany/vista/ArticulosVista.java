package com.mycompany.vista;

import com.mycompany.controlador.ControladorArticulos;
import com.mycompany.modelo.entidades.Articulo;
import com.mycompany.modelo.utils.InformeJasper;
import com.mycompany.vista.interfaces.IVista;
import com.mycompany.vista.interfaces.PedidoListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author alumno
 */
public class ArticulosVista extends javax.swing.JFrame implements IVista<Articulo> {

    private ControladorArticulos controlador;
    private MODO modo = MODO.INACTIVO;

    @Override
    public void setPedidoListener(PedidoListener listener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public enum MODO {
        ALTA,
        BAJA,
        MODIFICACION,
        INACTIVO,
        CODIGO
    }

    public void setControlador(ControladorArticulos controlador) {
        this.controlador = controlador;
    }

    /**
     * Creates new form Articulos
     */
    public ArticulosVista(ControladorArticulos controlador) {
        this.controlador = controlador;
        initComponents();
        estadoInicial();

        // Restricciones de entrada
        limitarEntradaACifraConLongitudExacta(campoCodigo, 6);
        limitarEntradaLetrasConLongitudMinYMax(campoDescripcion, 2, 25);
        limitarEntradaACifraConLongitudExacta(campoStockMinimo, 5);
        limitarEntradaACifraConLongitudExacta(campoStock, 5);
        limitarEntradaACifraConLongitudExacta(campoPrecioCompra, 7);
        limitarEntradaACifraConLongitudExacta(campoPrecioVenta, 7);

        botonAceptar.addActionListener(e -> controlador.gestionarOperacion(modo));
        botonSalir.addActionListener(e -> estadoInicial());
        botonCancelar.addActionListener(e -> cancelarAccion());

        botonVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                controlador.volverAPaginaDeInicio();
            }
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
        campoDescripcion = new javax.swing.JTextField();
        campoStockMinimo = new javax.swing.JTextField();
        campoStock = new javax.swing.JTextField();
        campoPrecioCompra = new javax.swing.JTextField();
        campoPrecioVenta = new javax.swing.JTextField();
        Código = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMantenimiento = new javax.swing.JMenu();
        menuAlta = new javax.swing.JMenuItem();
        menuBaja = new javax.swing.JMenuItem();
        menuModificacion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        botonVolver = new javax.swing.JMenuItem();
        menuConsulta = new javax.swing.JMenu();
        consultaPorCodigo = new javax.swing.JMenuItem();
        menuListados = new javax.swing.JMenu();
        listadoPorCodigo = new javax.swing.JMenuItem();
        listadoEntreCodigos = new javax.swing.JMenuItem();
        listadoGraficos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        campoCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCodigoKeyPressed(evt);
            }
        });

        Código.setText("Código");

        jLabel2.setText("Precio de compra");

        jLabel3.setText("Precio de venta");

        jLabel4.setText("Stock");

        jLabel5.setText("Descripción");

        jLabel6.setText("Stock mínimo");

        botonAceptar.setText("Aceptar");

        botonCancelar.setText("Cancelar");

        botonSalir.setText("Salir");

        menuMantenimiento.setText("Mantenimiento");

        menuAlta.setText("Alta");
        menuAlta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuAltaMousePressed(evt);
            }
        });
        menuMantenimiento.add(menuAlta);

        menuBaja.setText("Baja");
        menuBaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuBajaMousePressed(evt);
            }
        });
        menuMantenimiento.add(menuBaja);

        menuModificacion.setText("Modificacion");
        menuModificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuModificacionMousePressed(evt);
            }
        });
        menuMantenimiento.add(menuModificacion);
        menuMantenimiento.add(jSeparator1);

        botonVolver.setText("Volver");
        menuMantenimiento.add(botonVolver);

        jMenuBar1.add(menuMantenimiento);

        menuConsulta.setText("Consulta");

        consultaPorCodigo.setText("Por Codigo");
        consultaPorCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                consultaPorCodigoMousePressed(evt);
            }
        });
        menuConsulta.add(consultaPorCodigo);

        menuListados.setText("Listado");

        listadoPorCodigo.setText("Por Codigo");
        listadoPorCodigo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listadoPorCodigoMousePressed(evt);
            }
        });
        menuListados.add(listadoPorCodigo);

        listadoEntreCodigos.setText("Entre Codigos");
        listadoEntreCodigos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listadoEntreCodigosMousePressed(evt);
            }
        });
        menuListados.add(listadoEntreCodigos);

        listadoGraficos.setText("Grafico");
        menuListados.add(listadoGraficos);

        menuConsulta.add(menuListados);

        jMenuBar1.add(menuConsulta);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(Código)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonAceptar)
                                .addGap(18, 18, 18)
                                .addComponent(botonCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(botonSalir))
                            .addComponent(campoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(campoStock, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campoStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(Código)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(campoPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 2, Short.MAX_VALUE)
                        .addComponent(botonAceptar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonSalir)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuBajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuBajaMousePressed
        // TODO add your handling code here:
        modo = MODO.BAJA;
        cancelarAccion();
    }//GEN-LAST:event_menuBajaMousePressed

    private void menuModificacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuModificacionMousePressed
        // TODO add your handling code here:
        modo = MODO.MODIFICACION;
        cancelarAccion();
    }//GEN-LAST:event_menuModificacionMousePressed

    private void campoCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodigoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !campoCodigo.getText().isEmpty()) {
            controlador.verificarCodigo(modo);
            if (modo == MODO.CODIGO) {
                // Activar solo el botón de "Salir"
                botonSalir.setEnabled(true);
                botonAceptar.setEnabled(false);
                botonCancelar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_campoCodigoKeyPressed

    private void menuAltaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAltaMousePressed
        // TODO add your handling code here:
        modo = MODO.ALTA;
        cancelarAccion();
    }//GEN-LAST:event_menuAltaMousePressed

    private void consultaPorCodigoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_consultaPorCodigoMousePressed
        // TODO add your handling code here:
        modo = MODO.CODIGO;
        cancelarAccion();
    }//GEN-LAST:event_consultaPorCodigoMousePressed

    private void listadoPorCodigoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listadoPorCodigoMousePressed
        // TODO add your handling code here:
        String urlOrigen = "src/main/java/reports/listadoPorCodigoArticulos.jasper";
        String urlDestino = "src/main/java/reports/listadoPorCodigoArticulos.pdf";
        InformeJasper.getInstance().mostrarInforme(urlOrigen, urlDestino);
    }//GEN-LAST:event_listadoPorCodigoMousePressed

    private void listadoEntreCodigosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listadoEntreCodigosMousePressed
        // TODO add your handling code here:
        try {

            int codInicio = Integer.parseInt(JOptionPane.showInputDialog(this, "Código de inicio:"));
            int codFin = Integer.parseInt(JOptionPane.showInputDialog(this, "Código de fin:"));
            String urlOrigen = "src/main/java/reports/informeEntreCodigosArticulos.jasper";
            String urlDestino = "src/main/java/reports/informeEntreCodigosArticulos.pdf";
            if (codInicio > codFin) {
                JOptionPane.showMessageDialog(this, "El codigo de incio debe ser menor o igual al del fin.");
                return;
            }

            InformeJasper.getInstance().mostrarInformeEntreCodigos(urlOrigen, urlDestino, codInicio, codFin);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "EPor favor, introduce un numero valido.");
        }
    }//GEN-LAST:event_listadoEntreCodigosMousePressed

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
            java.util.logging.Logger.getLogger(ArticulosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArticulosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArticulosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArticulosVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel Código;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonSalir;
    private javax.swing.JMenuItem botonVolver;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoDescripcion;
    private javax.swing.JTextField campoPrecioCompra;
    private javax.swing.JTextField campoPrecioVenta;
    private javax.swing.JTextField campoStock;
    private javax.swing.JTextField campoStockMinimo;
    private javax.swing.JMenuItem consultaPorCodigo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem listadoEntreCodigos;
    private javax.swing.JMenuItem listadoGraficos;
    private javax.swing.JMenuItem listadoPorCodigo;
    private javax.swing.JMenuItem menuAlta;
    private javax.swing.JMenuItem menuBaja;
    private javax.swing.JMenu menuConsulta;
    private javax.swing.JMenu menuListados;
    private javax.swing.JMenu menuMantenimiento;
    private javax.swing.JMenuItem menuModificacion;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public void limpiarCampos() {
        campoCodigo.setText("");
        campoDescripcion.setText("");
        campoStock.setText("");
        campoStockMinimo.setText("");
        campoPrecioCompra.setText("");
        campoPrecioVenta.setText("");
    }

    @Override
    public void estadoCampos(boolean estado) {
        campoDescripcion.setEnabled(estado);
        campoStock.setEnabled(estado);
        campoStockMinimo.setEnabled(estado);
        campoPrecioCompra.setEnabled(estado);
        campoPrecioVenta.setEnabled(estado);
        botonAceptar.setEnabled(estado);
        botonCancelar.setEnabled(estado);
        botonSalir.setEnabled(estado);

        if (modo == ArticulosVista.MODO.INACTIVO) {
            campoCodigo.setEnabled(estado);
        } else {
            campoCodigo.setEnabled(!estado);
        }

        if (!estado) {
            campoCodigo.grabFocus();
        } else {
            campoPrecioCompra.grabFocus();
        }
    }

    @Override
    public Articulo obtenerEntidadDelFormulario() {
        Articulo articulo = new Articulo();
        try {
            articulo.setCodigo(Integer.parseInt(campoCodigo.getText()));
        } catch (NumberFormatException e) {
            articulo.setCodigo(-1);
        }
        articulo.setDescripcion(campoDescripcion.getText());
        articulo.setStock(Float.parseFloat(campoStock.getText()));
        articulo.setStockMinimo(Float.parseFloat(campoStockMinimo.getText()));
        articulo.setPrecioCompra(Float.parseFloat(campoPrecioCompra.getText()));
        articulo.setPrecioVenta(Float.parseFloat(campoPrecioVenta.getText()));
        return articulo;
    }

    @Override
    public void mostrarEntidad(Articulo entidad) {
        if (entidad == null) {
            return;
        }
        campoCodigo.setText(String.valueOf(entidad.getCodigo()));
        campoDescripcion.setText(entidad.getDescripcion());
        campoStock.setText(String.valueOf(entidad.getStock()));
        campoStockMinimo.setText(String.valueOf(entidad.getStockMinimo()));
        campoPrecioCompra.setText(String.valueOf(entidad.getPrecioCompra()));
        campoPrecioVenta.setText(String.valueOf(entidad.getPrecioVenta()));
    }

    @Override
    public int obtenerCodigoEntidad() {
        try {
            return Integer.parseInt(campoCodigo.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public void estadoInicial() {
        modo = MODO.INACTIVO;
        cancelarAccion();
    }

    @Override
    public void cancelarAccion() {
        limpiarCampos();
        estadoCampos(false);
    }

    @Override
    public void limitarEntradaLetrasConLongitudMinYMax(JTextField campo, int min, int max) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Permitimos letras y espacios
                if (!Character.isLetter(c) && c != ' ') {
                    e.consume(); // Bloquea cualquier otro carácter
                    return;
                }

                // Bloquear si supera el tamaño máximo
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
                if (!Character.isDigit(e.getKeyChar()) || campo.getText().length() >= longitud) {
                    e.consume();
                }
            }
        });
    }

    @Override
    public void comprobarEmail(JTextField campo) {
        //TODO
    }

    @Override
    public void limitarEntradaALongitudMinYMax(JTextField campo, int min, int max) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (campo.getText().length() >= max) {
                    e.consume();
                }
            }
        });
    }

    @Override
    public void irAPaginaDeInicio() {
        this.dispose();
        new Inicio().setVisible(true);
    }

    @Override
    public void enfocarCampo(String campo) {
        switch (campo) {
            case "campoCodigo":
                campoCodigo.grabFocus();
                break;
            case "campoDescripcion":
                campoDescripcion.grabFocus();
                break;
            case "campoStock":
                campoStock.grabFocus();
                break;
            case "campoStockMinimo":
                campoStockMinimo.grabFocus();
                break;
            case "campoPrecioCompra":
                campoPrecioCompra.grabFocus();
                break;
            case "campoPrecioVenta":
                campoPrecioVenta.grabFocus();
                break;
            default:
                break;
        }
    }
}
