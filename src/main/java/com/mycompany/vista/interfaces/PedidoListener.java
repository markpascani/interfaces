/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.vista.interfaces;

/**
 *
 * @author mihai
 */
public interface PedidoListener {
    void seleccionarModoCliente();
    void seleccionarModoProveedor();
    void seleccionarModoPedido();
    void volver();
    void validarCodigoEntidad();
    void validarArticulo();
    void calcularImporte();
    void procesarPedido();
    void cancelarPedido();
    void cancelarTodo();
    void generarFactura();
    void salir();
}

