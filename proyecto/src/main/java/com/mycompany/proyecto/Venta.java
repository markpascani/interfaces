/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

/**
 *
 * @author alumno
 */
public class Venta {
    private String nombreCliente;
    private String localidad;
    private boolean[] seleccionCheckBox;
    private javax.swing.ButtonModel[] listaCompra;

    public Venta(String nombreCliente, String localidad, boolean[] seleccionesCheck, javax.swing.ButtonModel[] listaCompra) {
        this.nombreCliente = nombreCliente;
        this.localidad = localidad;
        this.seleccionCheckBox = seleccionesCheck;
        this.listaCompra = listaCompra;
        
    }

    @Override
    public String toString() {

        return "Venta{" + "nombreCliente=" + nombreCliente + ", localidad=" + localidad + ", seleccionCheckBox=" + seleccionCheckBox + ", listaCompra=" + listaCompra.length + '}';
    }
    
    
   
    
    
    
    
    
}
