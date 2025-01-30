/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.vista;

/**
 *
 * @author mihai
 */
public interface IVistaConNIF<T> extends IVista<T> {

    // Obtener la parte numérica del NIF
    int obtenerCifrasNIF();

    // Establecer la letra del NIF en el formulario
    void establecerLetraNIF(String letra);
}
