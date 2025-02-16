/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.modelo.dao.clases.PedidoDAO;
import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.modelo.entidades.Articulo;
import com.mycompany.modelo.entidades.Cliente;
import com.mycompany.modelo.entidades.Pedido;
import com.mycompany.modelo.entidades.Proveedor;
import com.mycompany.modelo.utils.InformeJasper;
import com.mycompany.vista.PedidosVista;
import com.mycompany.vista.interfaces.PedidoListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mihai Stinga
 */
public class PedidoControlador extends ControladorBase<Pedido> implements PedidoListener {

    private Integer numeroFacturaActual = null;
    private final PedidosVista vista;
    private final IGenericDAO<Cliente, Integer> clienteDAO;
    private final IGenericDAO<Proveedor, Integer> proveedorDAO;
    private final IGenericDAO<Articulo, Integer> articuloDAO;

    // Conexión para manejar transacción manual
    private final Connection connection;

    public PedidoControlador(
            PedidosVista vista,
            IGenericDAO<Pedido, Integer> pedidoDAO,
            IGenericDAO<Cliente, Integer> clienteDAO,
            IGenericDAO<Proveedor, Integer> proveedorDAO,
            IGenericDAO<Articulo, Integer> articuloDAO,
            Connection connection
    ) {
        super(pedidoDAO);
        this.clienteDAO = clienteDAO;
        this.proveedorDAO = proveedorDAO;
        this.articuloDAO = articuloDAO;
        this.connection = connection;
        this.vista = vista;

        // Iniciar transacción manual
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Asociamos este controlador como listener de la vista
        this.vista.setPedidoListener(this);
    }
    // ------------------------------------------------------------------------
    // Métodos de cambio de modo
    // ------------------------------------------------------------------------

    @Override
    public void seleccionarModoCliente() {
        iniciarPedidoCliente();
    }

    @Override
    public void seleccionarModoProveedor() {
        iniciarPedidoProveedor();
    }

    /**
     * Se llama cuando ya se ha validado el cliente/proveedor y pasamos a meter
     * artículos.
     */
    @Override
    public void seleccionarModoPedido() {
        vista.setPedido(true);
        vista.estadoCampos(true);
        vista.mostrarMensaje("Introduce el código de artículo y pulsa Enter para validar.");
    }

    public void iniciarPedidoCliente() {
        vista.setModo(PedidosVista.MODO.CLIENTE);
        vista.limpiarCampos();
        vista.estadoCampos(true);
        vista.mostrarMensaje("Modo: Pedido de Cliente. Introduce el código del cliente.");
        vista.enfocarCampo("codigo");
        vista.getBotonFactura().setText("Factura");

    }

    public void iniciarPedidoProveedor() {
        vista.setModo(PedidosVista.MODO.PROVEEDOR);
        vista.limpiarCampos();
        vista.estadoCampos(true);
        vista.mostrarMensaje("Modo: Pedido de Proveedor. Introduce el código del proveedor.");
        vista.enfocarCampo("codigo");
        // Cambia el texto del botón Factura a "Finalizar"
        vista.getBotonFactura().setText("Finalizar");

    }

    // ------------------------------------------------------------------------
    // VALIDACIÓN DE CÓDIGOS
    // ------------------------------------------------------------------------
    @Override
    public void validarCodigoEntidad() {
        int codigo = vista.obtenerCodigoEntidad();
        if (codigo <= 0) {
            vista.mostrarMensaje("Código inválido. Introduce un número mayor que 0.");
            return;
        }

        // Según el modo, buscamos en clienteDAO o proveedorDAO
        if (vista.getModo() == PedidosVista.MODO.CLIENTE) {
            Cliente c = clienteDAO.obtenerPorID(codigo);
            if (c == null) {
                vista.mostrarMensaje("No existe el cliente con código: " + codigo);
                return;
            }
            // Mostramos datos del cliente y pasamos a modo PEDIDO
            vista.mostrarMensaje("Cliente encontrado: " + c.getNombre());
            vista.mostrarDatosCliente(c);
            seleccionarModoPedido();

        } else if (vista.getModo() == PedidosVista.MODO.PROVEEDOR) {
            Proveedor p = proveedorDAO.obtenerPorID(codigo);
            if (p == null) {
                vista.mostrarMensaje("No existe el proveedor con código: " + codigo);
                return;
            }
            // Mostramos datos del proveedor y pasamos a modo PEDIDO
            vista.mostrarMensaje("Proveedor encontrado: " + p.getNombre());
            vista.mostrarDatosProveedor(p);
            seleccionarModoPedido();
        }

    }

    @Override
    public void validarArticulo() {
        int codigoArt = vista.obtenerCodigoArticulo();
        if (codigoArt <= 0) {
            vista.mostrarMensaje("Código de artículo inválido.");
            return;
        }
        Articulo a = articuloDAO.obtenerPorID(codigoArt);
        if (a == null) {
            vista.mostrarMensaje("No existe el artículo con código: " + codigoArt);
            return;
        }
        // Mostramos datos del artículo
        vista.mostrarDatosArticulo(a);

        // Deshabilitamos el campoArtículo y habilitamos campoUnidades
        vista.getCampoArticulo().setEnabled(false);
        vista.getCampoUnidades().setEnabled(true);
        vista.enfocarCampo("unidades");

        // Habilitamos el botón "Cancelar Pedido" para anular sólo este artículo
        vista.getBotonCancelarPedido().setEnabled(true);
    }

    // ------------------------------------------------------------------------
    // Cálculo de importe
    // ------------------------------------------------------------------------
    @Override
    public void calcularImporte() {
        try {
            int unidades = Integer.parseInt(vista.getCampoUnidades().getText().trim());
            float precio = Float.parseFloat(vista.getCampoPrecio().getText().trim());
            float importe = unidades * precio;

            // Si es cliente, comprobar que las unidades no superen el stock
            if (vista.isPedido() && vista.getModo() == PedidosVista.MODO.CLIENTE) {
                float stock = Float.parseFloat(vista.getCampoStock().getText().trim());
                if (unidades > stock) {
                    vista.mostrarMensaje("No hay suficiente stock. Disponibles: " + stock);
                    return;
                }
            }

            // Mostramos importe en la vista
            vista.getCampoImporte().setText(String.valueOf(importe));

            // deshabilitamos campoUnidades y habilitamos botón Aceptar
            vista.getBotonAceptar().setEnabled(true);
            vista.getBotonAceptar().requestFocus();

        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Error al calcular el importe: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------------
    // PROCESAR PEDIDO (ACTUALIZACIÓN DE STOCK, TOTALES, PEDIDOS_HISTORICO)
    // ------------------------------------------------------------------------
    @Override
    public void procesarPedido() {
        try {
            int codigoEntidad = vista.obtenerCodigoEntidad();
            int codigoArticulo = vista.obtenerCodigoArticulo();
            int unidades = Integer.parseInt(vista.getCampoUnidades().getText().trim());

            // Obtenemos el artículo
            Articulo art = articuloDAO.obtenerPorID(codigoArticulo);
            if (art == null) {
                vista.mostrarMensaje("Artículo inexistente.");
                return;
            }

            // Procesamos la lógica de actualizar stock, total y grabar en histórico
            boolean ok = procesarPedidoEnBD(codigoEntidad, art, unidades);
            if (!ok) {
                return;
            }

            // Si todo fue bien:
            vista.mostrarMensaje("Pedido procesado correctamente.");

            // Habilitamos Factura y deshabilitamos Aceptar/Cancelar Pedido
            vista.getBotonFactura().setEnabled(true);
            vista.getBotonAceptar().setEnabled(false);
            vista.getBotonCancelarPedido().setEnabled(false);
            vista.getCampoUnidades().setEnabled(false);

            // Limpiamos sólo la parte de artículo, para poder meter otro
            vista.limpiarCampos(); // llama sólo a campos de artículo en modo PEDIDO
            vista.enfocarCampo("articulo");
            // Rehabilitamos campoArticulo
            vista.getCampoArticulo().setEnabled(true);

        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Unidades inválidas: " + e.getMessage());
        }
    }

    /**
     * Lógica interna para actualizar stock y total ventas/compras, e insertar
     * el pedido en la tabla histórica. No hace commit (se hará al pulsar
     * Factura).
     *
     * @param codigoEntidad cliente/proveedor
     * @param articulo artículo validado
     * @param unidades unidades pedidas
     * @return true si todo OK, false si hay fallo
     */
    private boolean procesarPedidoEnBD(int codigoEntidad, Articulo articulo, int unidades) {
        try {
            if (vista.getModo() == PedidosVista.MODO.CLIENTE && vista.isPedido()) {
                if (numeroFacturaActual == null) {
                    numeroFacturaActual = obtenerNumeroFactura();
                }

                // Comprobamos si hay stock suficiente
                if (unidades > articulo.getStock()) {
                    vista.mostrarMensaje("Stock insuficiente. Disponible: " + articulo.getStock());
                    return false;
                }
                // Actualizamos stock en el artículo
                articulo.setStock(articulo.getStock() - unidades);
                articuloDAO.modificar(articulo);

                // Actualizamos total ventas del cliente
                Cliente cli = clienteDAO.obtenerPorID(codigoEntidad);
                if (cli == null) {
                    vista.mostrarMensaje("Cliente no encontrado (inconsistente).");
                    return false;
                }
                float importe = unidades * articulo.getPrecioVenta();
                cli.setTotalVentas(cli.getTotalVentas() + importe);
                clienteDAO.modificar(cli);

                // Grabamos en pedidos_historico
                Pedido pedido = new Pedido(codigoEntidad, null, articulo.getCodigo(), unidades, LocalDate.now(), numeroFacturaActual);
                dao.darDeAlta(pedido);

            } else if (vista.getModo() == PedidosVista.MODO.PROVEEDOR && vista.isPedido()) {
                // MODO.PROVEEDOR
                // Actualizamos stock (se añade)
                articulo.setStock(articulo.getStock() + unidades);
                articuloDAO.modificar(articulo);

                // Actualizamos total compras del proveedor
                Proveedor prov = proveedorDAO.obtenerPorID(codigoEntidad);
                if (prov == null) {
                    vista.mostrarMensaje("Proveedor no encontrado (inconsistente).");
                    return false;
                }
                float importe = unidades * articulo.getPrecioCompra();
                prov.setTotalCompras(prov.getTotalCompras() + importe);
                proveedorDAO.modificar(prov);

                // Grabamos en pedidos_historico
                Pedido pedido = new Pedido(null, codigoEntidad, articulo.getCodigo(), unidades, LocalDate.now(), numeroFacturaActual);
                dao.darDeAlta(pedido);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            vista.mostrarMensaje("Error al procesar en la BD: " + e.getMessage());
            return false;
        }
    }

    // ------------------------------------------------------------------------
    // Cancelar un pedido parcial, cancelar todo y salir
    // ------------------------------------------------------------------------
    /**
     * Cancelar Pedido: limpia sólo los datos de artículo/unidades en la vista
     * (no hace rollback), para volver a introducir otro artículo o corregir.
     */
    @Override
    public void cancelarPedido() {
        // Borramos sólo la parte de artículo
        vista.getCampoArticulo().setText("");
        vista.getCampoDescripcion().setText("");
        vista.getCampoUnidades().setText("");
        vista.getCampoStock().setText("");
        vista.getCampoPrecio().setText("");
        vista.getCampoImporte().setText("");

        //deshabilitamos unidades
        vista.getCampoUnidades().setEnabled(false);
        // Deshabilitamos Aceptar
        vista.getBotonAceptar().setEnabled(false);

        // Rehabilitamos el campoArticulo
        vista.getCampoArticulo().setEnabled(true);
        vista.enfocarCampo("articulo");

        vista.mostrarMensaje("Se ha cancelado el pedido del artículo actual. Introduce nuevo artículo.");
    }

    /**
     * Cancelar Todo: rollback total y limpia el formulario para volver a
     * introducir pedido sin perder que estamos en modo PEDIDO (o, si prefieres,
     * volver a estado inicial).
     */
    @Override
    public void cancelarTodo() {
        try {
            connection.rollback();
            vista.mostrarMensaje("Se han anulado todos los pedidos (rollback).");

            // Limpiamos todos los campos, tanto de cliente/proveedor como de artículo
            // para forzar a revalidar el código de cliente/proveedor otra vez si se desea
            vista.setPedido(false);
            vista.limpiarCampos();

            // Habilitamos la vista en modo pedido (campoArticulo, etc.)
            // o la dejamos en "inicial" si quisiéramos forzar a volver al menú:
            vista.estadoCampos(true);

            // Enfocamos directamente en el artículo (según lo solicitado)
            vista.enfocarCampo("codigo");

        } catch (SQLException e) {
            e.printStackTrace();
            vista.mostrarMensaje("Error al hacer rollback: " + e.getMessage());
        }
    }

    /**
     * Salir: rollback y cierre de la ventana, volviendo a la principal.
     */
    @Override
    public void salir() {
        try {
            connection.rollback();
            vista.mostrarMensaje("Operaciones anuladas. Saliendo...");
            vista.estadoInicial();
        } catch (SQLException e) {
            e.printStackTrace();
            vista.mostrarMensaje("Error al hacer rollback al salir: " + e.getMessage());
        }
    }

    // ------------------------------------------------------------------------
    // Método abstracto (de ControladorBase) para validar Pedido
    // ------------------------------------------------------------------------
    @Override
    public String validarEntidad(Pedido entidad) {
        // Valida campos básicos
        if (entidad.getCodigoArticulo() <= 0) {
            return "codigoArticulo";
        }
        if (entidad.getUnidades() <= 0) {
            return "unidades";
        }
        return "";
    }

    /**
     * Opción del menú "Volver", regresa a la ventana principal (sin guardar
     * nada y haciendo rollback).
     */
    @Override
    public void volver() {
        vista.irAPaginaDeInicio();
    }

    @Override
    public void generarFactura() {
        try {
            // Si es proveedor, solo se confirma la transacción
            if (vista.getModo() == PedidosVista.MODO.PROVEEDOR) {
                connection.commit();
                vista.mostrarMensaje("Pedido de proveedor finalizado (commit realizado).");
                return;
            }

            // Verificar si hay un número de factura generado
            if (numeroFacturaActual == null) {
                vista.mostrarMensaje("No se ha generado número de factura.");
                return;
            }

            // Prepara parámetros para el informe
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("empresa", "IES LAZARO CARDENAS");
            parametros.put("nifEmpresa", "123456789");
            parametros.put("fecha", java.time.LocalDate.now().toString());
            parametros.put("numeroFactura", numeroFacturaActual); // ✅ Usar el mismo número ya asignado
            parametros.put("clienteNombre", vista.getCampoNombre().getText().trim());
            parametros.put("clienteNIF", vista.getCampoNIF().getText().trim());
            parametros.put("clienteCodigo", Integer.valueOf(vista.getCampoCodigo().getText().trim()));

            // Ruta del informe Jasper
            String rutaInforme = "src/main/java/reports/factura.jasper";

            System.out.println(numeroFacturaActual + "param" + parametros.get("clienteCodigo"));
            // Confirmar transacción
            connection.commit();

            // Generar el informe Jasper
            InformeJasper.getInstance().mostrarInformeConParametros(rutaInforme, parametros);

            vista.mostrarMensaje("Factura generada correctamente (commit realizado).");

            // Restablecer número de factura actual
            numeroFacturaActual = null;

            // Limpiar la vista
            vista.limpiarCampos();
            vista.estadoInicial();

        } catch (SQLException ex) {
            ex.printStackTrace();
            vista.mostrarMensaje("Error al hacer commit: " + ex.getMessage());
        }
    }

    private static final String RUTA_FICHERO_FACTURA = "numero_factura.txt";

    private int obtenerNumeroFactura() {
        int numeroFactura = 1; // Si no hay archivo, comenzamos en 1
        File archivo = new File(RUTA_FICHERO_FACTURA);

        // Leer el número actual del archivo
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea = br.readLine();
                if (linea != null) {
                    numeroFactura = Integer.parseInt(linea.trim());
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
            
            numeroFactura++;
            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, false))){
                bw.write(String.valueOf(numeroFactura));
                
            }catch(IOException | NumberFormatException e){
                e.printStackTrace();
            }
        
        
        }
       

        // 📌 IMPORTANTE: NO INCREMENTAR AQUÍ. SE INCREMENTARÁ SOLO AL GENERAR LA FACTURA.
        return numeroFactura;
    }

// 📌 Método para incrementar el número de factura solo cuando se genera la factura
    private void incrementarNumeroFactura() {
        int nuevoNumeroFactura = numeroFacturaActual + 1;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_FICHERO_FACTURA, false))) {
            bw.write(String.valueOf(nuevoNumeroFactura));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
