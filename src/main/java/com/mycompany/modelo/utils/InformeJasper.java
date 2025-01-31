/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.utils;

import com.mycompany.utils.JDBC;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mihai Stinga
 */
public class InformeJasper {

    private static InformeJasper instance;

    static {
        instance = new InformeJasper();
    }

    public static InformeJasper getInstance() {
        return instance;

    }
   

    public void mostrarInforme(String urlOrigen, String urlDestino) {

        try (Connection connection = JDBC.getConnection()) {
            System.out.println("Conexion correcta.");

            JasperPrint jasperPrint = JasperFillManager.fillReport(urlOrigen, null, connection);

            // Exportar a HTML usando el objeto JasperPrint
            JasperExportManager.exportReportToPdfFile(jasperPrint, urlDestino);

            JasperViewer.viewReport(jasperPrint);

        } catch (JRException e) {
            System.out.println("No se ha podido conectar a la bbdd." + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(InformeJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
