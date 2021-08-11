package Controladores;

import Modelo.*;
import Reporte.Reporte;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import org.postgresql.core.Utils;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class VentasController implements Initializable {

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private Button accederCosteo;

    @FXML
    private Button accederClientes;

    @FXML
    private Button accederFacturacion;

    @FXML
    private Button accederStock;

    @FXML
    private TextField cantidad;

    @FXML
    private TextField stock;

    @FXML
    private TextField codigo;

    @FXML
    private TextField precio;

    @FXML
    private ComboBox producto;

    @FXML
    private ComboBox cliente;

    @FXML
    private DatePicker fecha;

    @FXML
    private TableView<Producto> tabla;

    @FXML
    private TableColumn<Producto, Integer> columnaCodigo;

    @FXML
    private TableColumn<Producto, Integer> columnaCantidad;

    @FXML
    private TableColumn<Producto, String> columnaD;

    @FXML
    private TableColumn<Producto, Float> columnaPrecio;

    @FXML
    private TableColumn<Producto, Float> columnaTotal;

    @FXML
    private TextField totalPagar;

    @FXML
    private TextField totalIva;

    @FXML
    private TextField cuotas;

    @FXML
    private Button confirmarVenta;

    @FXML
    private Button cancelarVenta;

    @FXML
    private Button agregarP;

    @FXML
    private Button modificarP;

    @FXML
    private Button eliminarP;

    @FXML
    private Button aceptarCambios;

    @FXML
    private Button cancelarCambios;

    @FXML
    private ComboBox seleccionProductos;

    @FXML
    private ComboBox seleccionClientes;

    @FXML
    private ComboBox seleccionMetodoDePago;

    @FXML
    private Label labelCuota;

    @FXML
    private Label labelIva;

    @FXML
    private ProgressBar progressBar;

    List<Producto> listaProductos = new ArrayList<>();

    private ObservableList<Producto> list;

    private int posicionEnTabla;
    private UsuarioLogeado u = UsuarioLogeado.getInstance();
    Connection conn = ConexionBD.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaProductos = new ArrayList<>();
        list = FXCollections.observableArrayList();
        this.columnaTotal.setCellValueFactory(new PropertyValueFactory("total"));
        this.columnaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        columnaD.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));

        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);

        seleccionProductos.setItems(tomarProductos());
        seleccionClientes.setItems(tomarClientes());
        seleccionMetodoDePago.setItems(tomarMetodosDePago());

        aceptarCambios.setVisible(false);
        aceptarCambios.setDisable(true);
        cancelarCambios.setVisible(false);
        cancelarCambios.setDisable(true);

        fecha.setValue(LocalDate.now());
        condicionFecha();
        cuotas.setVisible(false);
        cuotas.setDisable(true);
        labelCuota.setVisible(false);
        totalIva.setVisible(false);
        labelIva.setVisible(false);

        codigo.setDisable(true);
        precio.setDisable(true);
        stock.setDisable(true);

        typedEnNumeros();

        progressBar.setVisible(false);

        /*try {
            confirmarVenta();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cancelarVenta();
        */
//        final ObservableList<Producto> proSel = tabla.getSelectionModel().getSelectedItems();
//        proSel.addListener(selectorTablaProducto);

    }

    public void accederFacturacion(ActionEvent e) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturas");
        stage.show();
        cerrarVentana(e);
    }

    public void accederClientes(ActionEvent e) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Clientes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clientes");
        stage.show();
        cerrarVentana(e);
    }

    public void accederStock(ActionEvent e) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Stock.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stock");
        stage.show();
        cerrarVentana(e);
    }

    public void accederCosteo(ActionEvent e) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/MetodoCosteo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Metodo de Costeo");
        stage.show();
        cerrarVentana(e);
    }

    public void condicionFecha() {
        if (fecha.getValue().isAfter(LocalDate.now())) {
            fecha.setValue(LocalDate.now());
        }
    }

    public boolean avisoStock() {
        if (Integer.parseInt(cantidad.getText()) > Integer.parseInt(stock.getText())) {
            return true;
        }
        return false;
    }

    public void confirmarVenta(ActionEvent event) throws SQLException, JRException {
        //Persistir datos
        if(!seleccionClientes.getSelectionModel().isEmpty() && !seleccionMetodoDePago.getSelectionModel().isEmpty() && listaProductos.size()>0) {
            int idcliente = buscarCliente(seleccionClientes.getSelectionModel().getSelectedItem().toString());
            int idmediopago = buscarMedioPago(seleccionMetodoDePago.getSelectionModel().getSelectedItem().toString());

            //CREO LA VENTA
            double importeIva = isRealizarIvaCliente() ? Double.valueOf(totalIva.getText().replace(",", ".")) : Double.valueOf(totalPagar.getText().replace(",", "."));
            crearVenta(importeIva, Double.valueOf(totalPagar.getText().replace(",", ".")), Date.valueOf(fecha.getValue()), u.getId(), idcliente, idmediopago);

            //OBTENGO EL ID DE LA VENTA
            int idventa = ultimaVenta();
            //CREO LA RELACION VENTA PRODUCTO Y ACTUALIZO STOCK
            for (Producto p : listaProductos) {
                crearVentaProducto(idventa, p.getIdProducto(), p.getCantidad(), isRealizarIvaCliente()?calcularPrecioFinalIva(calcularIvaProducto(p.getCantidad(), p.getPrecio(), p.getAlicuota()),p.getPrecio(),p.getCantidad()):p.getPrecio()*p.getCantidad(), isRealizarIvaCliente()?p.getAlicuota():0);
                actualizarStock(p.getIdProducto(), p.getCantidad(), Integer.parseInt(p.getStock()));
            }

            //OBTENER LETRA DE FACTURA
            String letraFactura = crearLetraFactura(idcliente);

            //CREO LA FACTURA Y MANDO MENSAJE
            Date hoy = Date.valueOf(fecha.getValue());
            String numF = crearNumeroFactura();
            if (idmediopago == 2) {
                crearFactura(idventa, true, hoy, 1, 1, 0, importeIva, importeIva, hoy, numF, letraFactura);
                cargaProgressBar();
                avisoCompraConcretada("Efectivo");
                Reporte r = new Reporte();
                r.crearPDF(numF);
            } else {
                int cantidadCuotas = Integer.parseInt(cuotas.getText());
                double valorCuota = importeIva / cantidadCuotas;
                crearFactura(idventa, false, null, cantidadCuotas, 0, importeIva, 0, valorCuota, hoy, numF, letraFactura);
                cargaProgressBar();
                avisoCompraConcretada("Credito");
            }

            //Cerrar ventana
            cerrarVentana(event);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Complete todos los campos requeridos");
            alert.showAndWait();
        }

    }

    public void cargaProgressBar() {
        progressBar.setVisible(true);
    }

    public double calcularPrecioFinalIva(double precioAli, double precio, int cantidad){
        double p = precio*cantidad;
        return p + precioAli;
    }


    @FXML
    void cantidadCuotasMaximo(){
        try{
            int c = cuotas.getText().isBlank()?0:Integer.parseInt(cuotas.getText());
            if(c < 1 || c > 12 ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Ingrese cuotas desde 1 a 12.");
                alert.showAndWait();
            }
        }
        catch (Exception exception){
            System.out.println("Excepcion no conocida (mentira es en cuotas)");
        }
    }


    private String crearLetraFactura(int id) throws SQLException {
        return buscarIvaCliente(id)==1?"A":"B";
    }

    public int buscarIvaCliente(int idcliente) throws SQLException {
        String SQL = "SELECT c.id_condicioniva as id FROM cliente AS c WHERE c.idcliente = " + "'" + idcliente + "'";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        int idiva = 0;
        if (rs.next()) {
            idiva = rs.getInt("id");
        }
        return idiva + 1;
    }

    private void cerrarVentana(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void avisoCompraConcretada(String metodoPago){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atencion!");
        alert.setHeaderText("Venta Concretada");
        alert.setContentText("La venta a " + metodoPago + " fue realizada con exito.");
        alert.showAndWait();
    }

    public int ultimaFactura() throws SQLException {
        String SQL = "select MAX(idfactura) as idfactura from factura";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        int idventa = 0;
        while (rs.next()) {
            idventa = rs.getInt("idfactura");
        }
        return idventa;
    }

    public String crearNumeroFactura() throws SQLException {
        int numFactura = ultimaFactura();
        String numB = String.format("%08d", numFactura);
        return "0002-" + numB;
    }

    private void crearFactura(int idventa, boolean facturada, Date fecha_pago, int cuotas_totales, int cuotas_pagadas, double total_debe, double total_pagado, double valor_cuota, Date fecha_emision, String numFactura, String letra_factura) throws SQLException {
        String sql = "INSERT INTO factura(idventa, facturada, fecha_pago, cuotas_totales, cuotas_pagadas, total_debe, total_pagado, valor_cuota, fecha_emision, numero_factura, letra_factura) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, idventa);
        ps.setBoolean(2, facturada);
        ps.setDate(3, fecha_pago);
        ps.setInt(4, cuotas_totales);
        ps.setInt(5, cuotas_pagadas);
        ps.setDouble(6, total_debe);
        ps.setDouble(7, total_pagado);
        ps.setDouble(8, valor_cuota);
        ps.setDate(9, fecha_emision);
        ps.setString(10, numFactura);
        ps.setString(11, letra_factura);
        ps.execute();
    }

    @FXML
    void mostrarCuotas(ActionEvent e) throws SQLException {
        medioPagoCuota();
    }

    public void medioPagoCuota() throws SQLException {
        try {
            int mp = buscarMedioPago(seleccionMetodoDePago.getSelectionModel().getSelectedItem().toString());
            if (mp == 2) {
                ocultarCuotas();
            } else {
                mostrarCuotas();
            }
        } catch (Exception e) {
            System.out.println("Error en el medio de pago.");
        }
    }

    public void ocultarCuotas() {
        labelCuota.setVisible(false);
        cuotas.setVisible(false);
        cuotas.setDisable(true);
    }

    public void mostrarCuotas() {
        labelCuota.setVisible(true);
        cuotas.setVisible(true);
        cuotas.setDisable(false);
    }

    public int ultimaVenta() throws SQLException {
        String SQL = "select MAX(idventa) as idventa from venta";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        int idventa = 0;
        while (rs.next()) {
            idventa = rs.getInt("idventa");
        }
        return idventa;
    }

    public int buscarCliente(String c) throws SQLException {
        String[] parts = c.split("-");
        String cuit = parts[1].replace(" ", "");
        String SQL = "SELECT c.idcliente as id FROM cliente AS c WHERE c.cuit LIKE " + "'" + cuit + "'";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(SQL);
        int idcliente = 0;
        if (rs.next()) {
            idcliente = rs.getInt("id");
        }
        return idcliente;
    }

    public int buscarMedioPago(String m) throws SQLException {
        String SQLPago = "SELECT c.idmediopago as id FROM medio_pago AS c WHERE c.descripcion LIKE " + "'" + m + "'";
        Statement statement = conn.createStatement();
        ResultSet rsPago = statement.executeQuery(SQLPago);
        int idmedio = 0;
        if (rsPago.next()) {
            idmedio = rsPago.getInt("id");
        }
        return idmedio;
    }

    public void crearVenta(double totaliva, double total, Date fecha, int idusuario, int idcliente, int idmediopago) throws SQLException {
        String sql = "INSERT INTO venta(total_iva,total, fecha, idusuario, idcliente, idmediopago) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, totaliva);
        ps.setDouble(2, total);
        ps.setDate(3, fecha);
        ps.setInt(4, idusuario);
        ps.setInt(5, idcliente);
        ps.setInt(6, idmediopago);
        ps.execute();
    }

    public void crearVentaProducto(int idventa, int idproducto, int cantidad, double total, double producto_alicuota) throws SQLException {
        String sql = "INSERT INTO venta_producto(idventa,idproducto, cantidad, precio_calc, producto_alicuota) VALUES (?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idventa);
        ps.setInt(2, idproducto);
        ps.setInt(3, cantidad);
        ps.setDouble(4,total);
        ps.setDouble(5,producto_alicuota);
        ps.execute();
    }

    public void actualizarStock(int idproducto, int cantidad, int stock) throws SQLException {
        int stockFinal = stock-cantidad;
        String sql = "UPDATE producto SET stock=? WHERE idproducto=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, stockFinal);
        ps.setInt(2, idproducto);
        ps.execute();

    }

    public void cancelarVenta(ActionEvent event) {
//        listaProductos.clear();
//        list.clear();
//        tabla.getItems().clear();
//        codigo.setText("");
////        seleccionProductos.getSelectionModel().clearSelection(); //setValue("Seleccione");
////        seleccionProductos.setValue("Seleccione");
//        cantidad.setText("");
//        stock.setText("");
//        precio.setText("");
//        totalPagar.setText("");
//        codigo.setDisable(false);
//        precio.setDisable(false);
//        stock.setDisable(false);
        cerrarVentana(event);
    }

    public ObservableList<String> tomarMetodosDePago() {
        ObservableList<String> list = FXCollections.observableArrayList();

        try {
            String SQL = "select mp.descripcion as descripcion from medio_pago as mp ";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("descripcion"));
            }

        } catch (Exception e) {

        }

        return list;
    }

    public boolean comprobarProducto(List<Producto> listaProductos, Producto p) {
        List<String> listaNombresProductos = new ArrayList<>();
        for (Producto pr : listaProductos) {
            listaNombresProductos.add(pr.getNombreProducto());
        }

        if (listaNombresProductos.contains(p.getNombreProducto())) {
            return true;
        }
        return false;

    }

    public boolean existenciaProductoPorCodigo(String c) {
        boolean r = false;
        try {

            String SQL = "SELECT p.codigo as codigo FROM producto AS p WHERE p.codigo LIKE " + "'" + c + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            if (rs.next()) {
                r = true;
            } else {
                r = false;
            }

        } catch (Exception e) {

        }
        return r;
    }

    public void agregarProductoPorCodigo() {
        String c = codigo.getText();
        if (existenciaProductoPorCodigo(c)) {
            if (avisoAgregarCantidad()) {
                if (!avisoStock()) {
                    try {

                        String SQL = "SELECT p.idproducto as idproducto, p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock, p.descripcion as descripcion, p.activo as activo, p.proveedor as proveedor, p.alicuota as alicuota FROM producto AS p WHERE p.codigo LIKE " + "'" + c + "'";
                        Statement statement = conn.createStatement();
                        ResultSet rs = statement.executeQuery(SQL);

                        Producto producto = new Producto();

                        while (rs.next()) {
                            int idProducto = rs.getInt("idproducto");
                            int codigo = rs.getInt("codigo");
                            String nombre = rs.getString("nombre");
                            float precio = rs.getFloat("precio");
                            String stock = rs.getString("stock");
                            String descripcion = rs.getString("descripcion");
                            boolean activo = rs.getBoolean("activo");
                            String proveedor = rs.getString("proveedor");
                            float alicuota = rs.getFloat("alicuota");

                            producto.setIdProducto(idProducto);
                            producto.setCodigo(codigo);
                            producto.setNombreProducto(nombre);
                            producto.setPrecio(precio);
                            producto.setStock(stock);
                            producto.setDescripcion(descripcion);
                            producto.setActivo(activo);
                            producto.setProveedor(proveedor);
                            producto.setCantidad(Integer.parseInt(cantidad.getText()));
                            float total = precio * Integer.parseInt(cantidad.getText());
                            producto.setTotal(total);
                            producto.setAlicuota(alicuota);

                            Boolean existe = comprobarProducto(listaProductos, producto);

                            if (!existe) {

                                listaProductos.add(producto);

                                columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
                                columnaD.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
                                columnaCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
                                columnaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
                                columnaTotal.setCellValueFactory(new PropertyValueFactory("total"));

                                list.add(producto);
                                tabla.setItems(list);
                                totalPagar.setText(String.valueOf(decimalFormat.format(calcularPrecioTotal())));
                                totalIva.setText(String.valueOf(decimalFormat.format(calcularPrecioTotalIva())));
                                if (!isRealizarIvaCliente()) {
                                    labelIva.setVisible(false);
                                    totalIva.setVisible(false);
                                }else {
                                    labelIva.setVisible(false);
                                    totalIva.setVisible(true);
                                }
                                limpiarProducto();

                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Atencion!");
                                alert.setHeaderText("Por favor,");
                                alert.setContentText("El producto ya se encuentra en la lista.");
                                alert.showAndWait();
                                limpiarProducto();
                                break;
                            }
                        }

                    } catch (Exception e) {

                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencion!");
                    alert.setHeaderText("Por favor,");
                    alert.setContentText("La cantidad solicitada excede el stock disponible.");
                    alert.showAndWait();
                    cantidad.setText("");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Ingrese la cantidad deseada del producto.");
                alert.showAndWait();
                cantidad.setText("");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Seleccione un codigo correcto.");
            alert.showAndWait();
        }
    }

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public void agregarProducto() {
        String s = seleccionProductos.getSelectionModel().getSelectedItem().toString();
        if (avisoAgregarCantidad()) {
            if (!avisoStock()) {
                try {

                    String SQL = "SELECT p.idproducto as idproducto, p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock, p.descripcion as descripcion, p.activo as activo, p.proveedor as proveedor, p.alicuota as alicuota FROM producto AS p WHERE p.nombre LIKE " + "'" + s + "'";
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(SQL);

                    Producto producto = new Producto();

                    while (rs.next()) {
                        int idProducto = rs.getInt("idproducto");
                        int codigo = rs.getInt("codigo");
                        String nombre = rs.getString("nombre");
                        float precio = rs.getFloat("precio");
                        String stock = rs.getString("stock");
                        String descripcion = rs.getString("descripcion");
                        boolean activo = rs.getBoolean("activo");
                        String proveedor = rs.getString("proveedor");
                        float alicuota = rs.getFloat("alicuota");

                        producto.setIdProducto(idProducto);
                        producto.setCodigo(codigo);
                        producto.setNombreProducto(nombre);
                        producto.setPrecio(precio);
                        producto.setStock(stock);
                        producto.setDescripcion(descripcion);
                        producto.setActivo(activo);
                        producto.setProveedor(proveedor);
                        producto.setCantidad(Integer.parseInt(cantidad.getText()));
                        float total = precio * Integer.parseInt(cantidad.getText());
                        producto.setTotal(total);
                        producto.setAlicuota(alicuota);

                        Boolean existe = comprobarProducto(listaProductos, producto);

                        if (!existe) {

                            listaProductos.add(producto);

                            columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
                            columnaD.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
                            columnaCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
                            columnaPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
                            columnaTotal.setCellValueFactory(new PropertyValueFactory("total"));

                            list.add(producto);
                            tabla.setItems(list);
                            totalPagar.setText(String.valueOf(decimalFormat.format(calcularPrecioTotal())));
                            totalIva.setText(String.valueOf(decimalFormat.format(calcularPrecioTotalIva())));
                            if (!isRealizarIvaCliente()) {
                                labelIva.setVisible(false);
                                totalIva.setVisible(false);
                            }else {
                                labelIva.setVisible(true);
                                totalIva.setVisible(true);
                            }
                            limpiarProducto();

                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Atencion!");
                            alert.setHeaderText("Por favor,");
                            alert.setContentText("El producto ya se encuentra en la lista.");
                            alert.showAndWait();
                            limpiarProducto();

                        }
                    }

                } catch (Exception e) {

                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("La cantidad solicitada excede el stock disponible.");
                alert.showAndWait();
                cantidad.setText("");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Ingrese la cantidad deseada del producto.");
            alert.showAndWait();
            cantidad.setText("");
        }
        /*}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Ingrese la cantidad deseada del producto.");
            alert.showAndWait();
            //agregarProductoPorCodigo();
        }*/

    }

    public void agregarProductoGeneral() {
        try {
            if (!seleccionProductos.getSelectionModel().isEmpty()) {// || !seleccionProductos.getSelectionModel().getSelectedItem().toString().isEmpty()) {
                agregarProducto();
            } else if (!codigo.getText().isEmpty() || !codigo.getText().isBlank()) {
                agregarProductoPorCodigo();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Seleccione un producto para agregar al carrito.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("La busqueda genero una excepcion");
        }
    }

    private boolean avisoAgregarCantidad() {
        if (cantidad.getText().isBlank()) {
            return false;
        } else if (cantidad.getText().equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    public void limpiarProducto() {
        codigo.setDisable(false);
        precio.setDisable(false);
        stock.setDisable(false);
        codigo.setText("");
        cantidad.setText("");
        precio.setText("");
        stock.setText("");
        seleccionProductos.setValue("Seleccione");
    }

    public void typedEnNumeros() {
        codigo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    codigo.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        codigo.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });
        cantidad.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    cantidad.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        cantidad.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });
        cuotas.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    cuotas.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        cuotas.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });
    }

//    public Proveedor buscarProveedor(int idproveedor) {
//
//        Proveedor proveedor1 = new Proveedor();
//        try {
//
//            String SQL = "select p.idproveedor as idproveedor, p.nombre as nombre, p.razon_social as razonSocial, p.sector_comercial as sectorComercial, p.direccion as direccion, p.ciudad as ciudad, p.provincia as provincia, p.telefono as telefono, p.url as url, p.observaciones as observaciones from proveedor as p where p.idproveedor =" + "'" + idproveedor + "'";
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(SQL);
//
//            while (rs.next()) {
//                int proveedor = rs.getInt("idproveedor");
//                String nombre = rs.getString("nombre");
//                String razonsocial = rs.getString("razonSocial");
//                String sectorcomercial = rs.getString("sectorComercial");
//                String direccion = rs.getString("direccion");
//                String ciudad = rs.getString("ciudad");
//                String provincia = rs.getString("provincia");
//                int telefono = rs.getInt("telefono");
//                String url = rs.getString("url");
//                String observaciones = rs.getString("observaciones");
//
//                proveedor1.setIdProveedor(proveedor);
//                proveedor1.setNombre(nombre);
//                proveedor1.setRazonSocial(razonsocial);
//                proveedor1.setSectorComercial(sectorcomercial);
//                proveedor1.setDireccion(direccion);
//                proveedor1.setCiudad(ciudad);
//                proveedor1.setProvincia(provincia);
//                proveedor1.setTelefono(telefono);
//                proveedor1.setUrl(url);
//                proveedor1.setObservaciones(observaciones);
//
//            }
//
//        } catch (Exception e) {
//
//        }
//        return proveedor1;
//    }

    public void eliminarProducto() {
        final Producto producto = getTablaProductoSeleccionada();
        try {
            if(producto != null) {
                posicionEnTabla = list.indexOf(producto);

                list.remove(posicionEnTabla);
                listaProductos.remove(posicionEnTabla);
                totalPagar.setText(String.valueOf(decimalFormat.format(calcularPrecioTotal())));
                totalIva.setText(String.valueOf(decimalFormat.format(calcularPrecioTotalIva())));
                if (!isRealizarIvaCliente()) {
                    labelIva.setVisible(false);
                    totalIva.setVisible(false);
                } else {
                    labelIva.setVisible(false);
                    totalIva.setVisible(true);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Seleccione un producto para eliminar del carrito.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("Eliminar Genero una Excepcion");
        }
    }

    @FXML
    void seleccionarProducto(ActionEvent event) {
        String s = seleccionProductos.getSelectionModel().getSelectedItem().toString();

        try {

            String SQL = "SELECT p.codigo as codigo, p.precio as precio, p.stock as stock FROM producto AS p WHERE p.nombre LIKE " + "'" + s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                codigo.setText(rs.getString("codigo"));
                precio.setText(rs.getString("precio"));
                stock.setText(rs.getString("stock"));

            }
            codigo.setDisable(true);
            precio.setDisable(true);
            stock.setDisable(true);

        } catch (Exception e) {

        }
    }

    public ObservableList<String> tomarProductos() {
        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            String SQL = "SELECT p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock FROM producto AS p WHERE p.activo = true AND p.stock > 0";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("nombre"));
            }

        } catch (Exception e) {

        }
        codigo.setDisable(false);
        precio.setDisable(false);
        stock.setDisable(false);
        return list;
    }

    @FXML
    void seleccionarClientes() {
        String s = seleccionClientes.getSelectionModel().getSelectedItem().toString();
        String[] parts = s.split("-");
        String cuit = parts[1].replace(" ", "");
        int iva = 0;
        try {

            String SQL = "SELECT c.id_condicioniva as idiva FROM cliente AS c WHERE c.cuit LIKE '" +cuit + "' AND c.estado = true ";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);


            if (rs.next()) {
                iva = rs.getInt("idiva");
            }
            if (iva == 2) {
                ocultarIva(false, false);
                setRealizarIvaCliente(false);
            } else {
                ocultarIva(true, true);
                setRealizarIvaCliente(true);
            }

        } catch (Exception e) {

        }
    }

    private boolean realizarIvaCliente;

    public boolean isRealizarIvaCliente() {
        return realizarIvaCliente;
    }

    public void setRealizarIvaCliente(boolean realizarIvaCliente) {
        this.realizarIvaCliente = realizarIvaCliente;
    }

    private void ocultarIva(boolean b1, boolean b2) {
        labelIva.setVisible(b1);
        totalIva.setVisible(b2);
    }

    public static String upperCaseFirst(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        return new String(arr);
    }

    public ObservableList<String> tomarClientes() {
        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            String SQL = "select cl.razonsocial as nombre, cl.cuit as cuit from cliente as cl where idtipopersona=1  AND cl.estado = true";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(upperCaseFirst(rs.getString("nombre")) + " - " + rs.getString("cuit"));
            }

        } catch (Exception e) {

        }

        ObservableList<String> listJuridica = FXCollections.observableArrayList();
        listJuridica = tomarClientesFisico();

        list.addAll(listJuridica);

        return list;
    }

    public ObservableList<String> tomarClientesFisico() {
        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            String SQL = "select concat(cl.nombre, ' ', cl.apellido) as nombre, cl.cuit as cuit from cliente as cl where idtipopersona=2  AND cl.estado = true";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(upperCaseFirst(rs.getString("nombre")) + " - " + rs.getString("cuit"));
            }

        } catch (Exception e) {

        }
        return list;
    }

    public float calcularPrecioTotal() {
        if (listaProductos.size() != 0) {
            float total = 0;
            for (Producto p : listaProductos) {
                total = total + p.getTotal();
            }
            return total;
        } else {
            return 0;
        }
    }

    private float calcularPrecioTotalIva() {
        float total = 0;

        for (Producto p : listaProductos) {
            if (p.getAlicuota() != 0) {
                total += p.getCantidad() * p.getPrecio() + calcularIvaProducto(p.getCantidad(), p.getPrecio(), p.getAlicuota());
            }
        }
        return total;
    }

    public double calcularIvaProducto(int cantidad, double precio, double alicuota){
        double total = cantidad*precio;
        total = total*alicuota;
        return total/100;
    }

    //
//    //METODOS PARA SELECCIONAR FILA EN TABLA
//    private final ListChangeListener<Producto> selectorTablaProducto =
//            new ListChangeListener<Producto>() {
//                @Override
//                public void onChanged(ListChangeListener.Change<? extends Producto> p) {
//                    ponerProductoSeleccionado();
//                }
//            };
    public Producto getTablaProductoSeleccionada() {
        if (tabla != null) {
            List<Producto> t = tabla.getSelectionModel().getSelectedItems();
            if (t.size() == 1) {
                final Producto codigoSeleccionado = t.get(0);
                return codigoSeleccionado;
            }
        }
        return null;
    }

    private void ponerProductoSeleccionado() {
        final Producto producto = getTablaProductoSeleccionada();
        posicionEnTabla = list.indexOf(producto);

        if (producto != null) {
            codigo.setText(String.valueOf(producto.getCodigo()));
            seleccionProductos.setValue(producto.getNombreProducto());
            cantidad.setText(String.valueOf(producto.getCantidad()));
            precio.setText(String.valueOf(producto.getPrecio()));
            stock.setText(String.valueOf(producto.getStock()));

            codigo.setDisable(true);
            precio.setDisable(true);
            stock.setDisable(true);
        }
    }

    public void modificarProducto() {
        final Producto producto = getTablaProductoSeleccionada();
        try {
            if(producto != null) {
                posicionEnTabla = list.indexOf(producto);
                aceptarCambios.setVisible(true);
                aceptarCambios.setDisable(false);
                cancelarCambios.setVisible(true);
                cancelarCambios.setDisable(false);
                confirmarVenta.setVisible(false);
                cancelarVenta.setVisible(false);

                datosAceptarCambios(producto);

                agregarP.setVisible(false);
                modificarP.setVisible(false);
                eliminarP.setVisible(false);
                confirmarVenta.setVisible(false);
                cancelarVenta.setVisible(false);
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Seleccione un producto para modificar.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            System.out.println("Modificar Genero una Excepcion");
        }
    }

    public void datosAceptarCambios(Producto producto) {

        codigo.setText(producto.getCodigo().toString());
        seleccionProductos.setValue(producto.getNombreProducto());
        cantidad.setText(String.valueOf(producto.getCantidad()));
        precio.setText(String.valueOf(producto.getPrecio()));
        stock.setText(String.valueOf(producto.getStock()));
        setProductoEstatico(producto);

    }

    public void aceptarCambios() {

        getProductoEstatico().setCantidad(Integer.parseInt(cantidad.getText()));
        getProductoEstatico().setTotal(getProductoEstatico().getCantidad() * getProductoEstatico().getPrecio());

        list.set(posicionEnTabla, getProductoEstatico());
        listaProductos.set(posicionEnTabla, getProductoEstatico());
        totalPagar.setText(String.valueOf(decimalFormat.format(calcularPrecioTotal())));
        totalIva.setText(String.valueOf(decimalFormat.format(calcularPrecioTotalIva())));
        if (!isRealizarIvaCliente()) {
            labelIva.setVisible(false);
            totalIva.setVisible(false);
        }else {
            labelIva.setVisible(true);
            totalIva.setVisible(true);
        }

        codigo.setText("");
        seleccionProductos.setValue("");
        cantidad.setText("");
        precio.setText("");
        stock.setText("");

        aceptarCambios.setDisable(true);
        aceptarCambios.setVisible(false);
        cancelarCambios.setVisible(false);
        cancelarCambios.setDisable(true);

        agregarP.setVisible(true);
        modificarP.setVisible(true);
        eliminarP.setVisible(true);
        confirmarVenta.setVisible(true);
        cancelarVenta.setVisible(true);

    }

    public void cancelarCambios() {
        codigo.setText("");
        seleccionProductos.setValue("");
        cantidad.setText("");
        precio.setText("");
        stock.setText("");

        aceptarCambios.setDisable(true);
        aceptarCambios.setVisible(false);
        cancelarCambios.setVisible(false);
        cancelarCambios.setDisable(true);

        agregarP.setVisible(true);
        modificarP.setVisible(true);
        eliminarP.setVisible(true);
        confirmarVenta.setVisible(true);
        cancelarVenta.setVisible(true);
    }

    private Producto productoEstatico;

    private int indiceEstatico;

    private Producto getProductoEstatico() {
        return productoEstatico;
    }

    private void setProductoEstatico(Producto productoEstatico) {
        this.productoEstatico = productoEstatico;
    }

    public int getIndiceEstatico() {
        return indiceEstatico;
    }

    public void setIndiceEstatico(int indiceEstatico) {
        this.indiceEstatico = indiceEstatico;
    }
}