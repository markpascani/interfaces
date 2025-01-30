/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.entidades;

/**
 *
 * @author mihai
 */
public class Proveedor {
    
    //Atributos
    private int codigo;
    private String nif;
    private String apellidos;
    private String nombre;
    private String domicilio;
    private String codigoPostal;
    private String localidad;
    private String telefono;
    private String movil;
    private String fax;
    private String email;
    private float totalCompras;

    public Proveedor() {
    }

    public Proveedor(int codigo, String nif, String apellidos, String nombre, String domicilio, String codigoPostal, String localidad, String telefono, String movil, String fax, String email, float totalCompras) {
        this.codigo = codigo;
        this.nif = nif;
        this.apellidos = apellidos;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.telefono = telefono;
        this.movil = movil;
        this.fax = fax;
        this.email = email;
        this.totalCompras = totalCompras;
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(float totalCompras) {
        this.totalCompras = totalCompras;
    }
    
    
    
    
    
}
