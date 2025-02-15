/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entidades;

import java.time.LocalDate;

/**
 *
 * @author Mihai Stinga
 */
public class Pedido {
    private int codigoPedido;
    private int codigoCliente;
    private int codigoProveedor;
    private int codigoArticulo;
    private int unidadesPedidas;
    private LocalDate fechaPedido;
    
    
    public Pedido(int codigoCliente, int codigoProveedor, int codigoArticulo, int unidadesPedidas, LocalDate fechaPedido) {
        this.codigoCliente = codigoCliente;
        this.codigoProveedor = codigoProveedor;
        this.codigoArticulo = codigoArticulo;
        this.unidadesPedidas = unidadesPedidas;
        this.fechaPedido = fechaPedido;
    }

    public Pedido(int codigoPedido, int codigoCliente, int codigoProveedor, int codigoArticulo, int unidadesPedidas, LocalDate fechaPedido) {
        this(codigoCliente,codigoProveedor, codigoArticulo, unidadesPedidas, fechaPedido);
        this.codigoPedido = codigoPedido;
    }
    
    
    
    

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public int getUnidadesPedidas() {
        return unidadesPedidas;
    }

    public void setUnidadesPedidas(int unidadesPedidas) {
        this.unidadesPedidas = unidadesPedidas;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    
    
}
