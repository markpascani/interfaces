/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entidades;

/**
 *
 * @author mihai
 */
public class Articulo {
    private int codigo;
    private String descripcion;
    private float stock, stockMinimo, precioDeCompra, precioDeVenta;

    public Articulo() {
    }

    public Articulo(int codigo, String descripcion, float stock, float stockMinimo, float precioDeCompra, float precioDeVenta) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.precioDeCompra = precioDeCompra;
        this.precioDeVenta = precioDeVenta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    public float getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(float stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public float getPrecioCompra() {
        return precioDeCompra;
    }

    public void setPrecioCompra(float precioDeCompra) {
        this.precioDeCompra = precioDeCompra;
    }

    public float getPrecioVenta() {
        return precioDeVenta;
    }

    public void setPrecioVenta(float precioDeVenta) {
        this.precioDeVenta = precioDeVenta;
    }
    
    
}
