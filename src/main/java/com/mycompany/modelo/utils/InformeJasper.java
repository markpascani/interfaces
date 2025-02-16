/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.utils;

import com.mycompany.utils.JDBC;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
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

            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jasperViewer.setVisible(true);
        } catch (JRException e) {
            System.out.println("No se ha podido conectar a la bbdd." + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(InformeJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera un informe Jasper filtrado entre dos códigos.
     */
    public void mostrarInformeEntreCodigos(String urlOrigen, String urlDestino, int codigoInicio, int codigoFin) {
        try (Connection connection = JDBC.getConnection()) {
            System.out.println("Conexión correcta.");

            // Parámetros para JasperReports
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("codigoInicio", codigoInicio);
            parametros.put("codigoFin", codigoFin);

            JasperPrint jasperPrint = JasperFillManager.fillReport(urlOrigen, parametros, connection);

            JasperExportManager.exportReportToPdfFile(jasperPrint, urlDestino);

            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jasperViewer.setVisible(true);
        } catch (JRException | SQLException ex) {
            Logger.getLogger(InformeJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Genera un informe gráfico usando JasperReports.
     */
    public void mostrarGrafico(String urlOrigen, String urlDestino) {
        try (Connection connection = JDBC.getConnection()) {
            System.out.println("Conexión correcta.");

            JasperPrint jasperPrint = JasperFillManager.fillReport(urlOrigen, null, connection);

            JasperExportManager.exportReportToPdfFile(jasperPrint, urlDestino);

            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jasperViewer.setVisible(true);
        } catch (JRException | SQLException ex) {
            Logger.getLogger(InformeJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarInformeConParametros(String urlOrigen, Map<String, Object> parametros) {
        try (Connection connection = JDBC.getConnection()) {
            System.out.println("Conexión correcta para informe.");

            JasperPrint jasperPrint = JasperFillManager.fillReport(urlOrigen, parametros, connection);

            // Puedes exportarlo a PDF o mostrarlo directamente
            // Por ejemplo, exportarlo a PDF:
            String urlDestino = "src/main/java/reports/factura.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, urlDestino);

            // Mostrar el informe en un visor:
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            jasperViewer.setVisible(true);
        } catch (JRException | SQLException ex) {
            Logger.getLogger(InformeJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
