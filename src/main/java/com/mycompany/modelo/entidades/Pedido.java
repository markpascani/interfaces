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
    private int idPedido;              // 'id_pedido' en la BD (auto-increment)
    private Integer codigoCliente;     // NULL si es pedido de proveedor
    private Integer codigoProveedor;   // NULL si es pedido de cliente
    private int codigoArticulo;        // Not null
    private int unidades;             // Not null
    private LocalDate fechaPedido;     // Fecha del pedido
    private Integer numeroFactura;

    public Pedido() { }

    public Pedido(Integer codigoCliente, Integer codigoProveedor,
                  int codigoArticulo, int unidades, LocalDate fechaPedido, Integer factura) {
        this.codigoCliente = codigoCliente;
        this.codigoProveedor = codigoProveedor;
        this.codigoArticulo = codigoArticulo;
        this.unidades = unidades;
        this.fechaPedido = fechaPedido;
        this.numeroFactura = factura;
    }

    public Pedido(int idPedido, Integer codigoCliente, Integer codigoProveedor,
                  int codigoArticulo, int unidades, LocalDate fechaPedido, Integer factura) {
        this(codigoCliente, codigoProveedor, codigoArticulo, unidades, fechaPedido, factura);
        this.idPedido = idPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer factura) {
        this.numeroFactura = factura;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(Integer codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    
}
