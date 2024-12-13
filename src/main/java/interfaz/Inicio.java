package interfaz;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author alumno
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuMantenimientoInicial = new javax.swing.JMenu();
        menuClientes = new javax.swing.JMenuItem();
        menuProveedores = new javax.swing.JMenuItem();
        menuArticulos = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        opcionSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuMantenimientoInicial.setText("Mantenimiento");

        menuClientes.setText("Clientes");
        menuClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuClientesMousePressed(evt);
            }
        });
        menuMantenimientoInicial.add(menuClientes);

        menuProveedores.setText("Proveedores");
        menuProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuProveedoresMousePressed(evt);
            }
        });
        menuMantenimientoInicial.add(menuProveedores);

        menuArticulos.setText("Artículos");
        menuArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuArticulosMousePressed(evt);
            }
        });
        menuMantenimientoInicial.add(menuArticulos);
        menuMantenimientoInicial.add(jSeparator1);

        opcionSalir.setText("Salir");
        opcionSalir.setToolTipText("");
        opcionSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                opcionSalirMousePressed(evt);
            }
        });
        menuMantenimientoInicial.add(opcionSalir);

        jMenuBar1.add(menuMantenimientoInicial);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuClientesMousePressed
        // TODO add your handling code here:
        Clientes clientes = new Clientes();
        clientes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuClientesMousePressed

    private void menuProveedoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuProveedoresMousePressed
        // TODO add your handling code here:
        Articulos articulo = new Articulos();
        articulo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuProveedoresMousePressed

    private void menuArticulosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuArticulosMousePressed
        // TODO add your handling code here:
        Proveedor proveedor = new Proveedor();
        proveedor.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuArticulosMousePressed

    private void opcionSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcionSalirMousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_opcionSalirMousePressed

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem menuArticulos;
    private javax.swing.JMenuItem menuClientes;
    private javax.swing.JMenu menuMantenimientoInicial;
    private javax.swing.JMenuItem menuProveedores;
    private javax.swing.JMenuItem opcionSalir;
    // End of variables declaration//GEN-END:variables
}
