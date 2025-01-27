/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.vista;

import com.mycompany.controlador.Controlador;
import com.mycompany.modelo.entidades.Cliente;

/**
 *
 * @author mihai
 */
public interface IVista<T> {

    // Mostrar mensajes al usuario
    void mostrarMensaje(String mensaje);

    // Limpiar todos los campos
    void limpiarCampos();

    // Habilitar o deshabilitar campos (o podría usarse un enum con modos)
    void estadoCampos(boolean estado);

    // Tomar datos del formulario y convertirlos a un objeto Cliente
    T obtenerClienteDeFormulario();

    // Mostrar los datos de un objeto Cliente en el formulario
    void mostrarCliente(T entidad);

    // Retornar el código (int) escrito en el campo correspondiente
    int obtenerCodigoCliente();

    // Estado inicial
    void estadoInicial();

    //Cancelar accion
    void cancelarAccion();

    //Comprobar campos vacios antes de hacer una consulta
    boolean comprobarCampos();

    // Limitar entrada a solo letras, con longitud min y max, en un campo específico
    void limitarEntradaLetrasConLongitudMinYMax(javax.swing.JTextField campo, int min, int max);

    // Limitar entrada a solo cifras, con longitud exacta, en un campo específico
    void limitarEntradaACifraConLongitudExacta(javax.swing.JTextField campo, int longitud);

    // Comprobar email en un campo concreto
    void comprobarEmail(javax.swing.JTextField campo);

    // Limitar entrada a longitud min y max
    void limitarEntradaALongitudMinYMax(javax.swing.JTextField campo, int min, int max);
    
    
    // Obtener cifra NIF cliente
    int obtenerCifrasNIFCliente();
    
    // Establecer un campo con un texto
    void establecerLetraNIF(String letra);
    

}
