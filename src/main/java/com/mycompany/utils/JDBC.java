/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase se encarga de gestionar la conexión con una base de datos MySQL.
 * Proporciona métodos para abrir y cerrar conexiones de manera segura.
 *
 * @author alumno
 */
public class JDBC {

    //Variable para la conexion a la bbdd
    private static final String DB_URL = "jdbc:mysql://37.187.37.143:3306/Interfaces?useSSL=false";
    private static final String DB_USER = "markpascani";
    private static final String DB_PASS = "markpascani";

    /**
     * Metodo para obtener una conexino a la base de datos
     * 
     * @return Connection - objeto de conexino a la bbdd
     * @throws SQLException - si ocurre un error al intentar conectar
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    
    
    /**
     * Metodo para cerrar una conexino de forma segura
     * @param connection - la conexion a cerrar
     */
    public static void closeConnection(Connection connection){
        if(connection != null){
            try{
                connection.close();;
            }catch(SQLException e){
                System.err.println("Error al cerrar la conexion: "+e.getMessage());
            }
        }
    }
    
    
}
