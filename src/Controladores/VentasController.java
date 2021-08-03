package Controladores;

import Modelo.*;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.*;
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

        typedEnNumeros();

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
    }

    public void accederClientes(ActionEvent e) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Clientes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clienes");
        stage.show();
    }

    public void accederStock(ActionEvent e) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Stock.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stock");
        stage.show();
    }

    public void accederCosteo(ActionEvent e) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/MetodoCosteo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Metodo de Costeo");
        stage.show();
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

    public void confirmarVenta() throws SQLException {
        //Persistir datos
        int idcliente = buscarCliente(seleccionClientes.getSelectionModel().getSelectedItem().toString());
        int idmediopago = buscarMedioPago(seleccionMetodoDePago.getSelectionModel().getSelectedItem().toString());

        //CREO LA VENTA
        crearVenta(Double.valueOf(totalIva.getText()), Double.valueOf(totalPagar.getText()), Date.valueOf(fecha.getValue()), u.getId(), idcliente, idmediopago);

        //OBTENGO EL ID DE LA VENTA
        int idventa = ultimaVenta();

        for (Producto p : listaProductos) {
            crearVentaProducto(idventa, p.getIdProducto());
            actualizarStock(p.getIdProducto(), p.getCantidad());
        }
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
    }

    public void mostrarCuotas() {
        labelCuota.setVisible(true);
        cuotas.setVisible(true);
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

    public void crearVentaProducto(int idventa, int idproducto) throws SQLException {
        String sql = "INSERT INTO usuario(idventa,idproducto) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idventa);
        ps.setInt(2, idproducto);
        ps.execute();
    }

    public void actualizarStock(int idproducto, int cantidad) throws SQLException {

        String sql = "UPDATE cliente SET stock=? WHERE idproducto=? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, cantidad);
        ps.setInt(2, idproducto);
        ps.execute();

    }

    public void cancelarVenta() {
        listaProductos.clear();
        list.clear();
        tabla.getItems().clear();
        codigo.setText("");
//        seleccionProductos.getSelectionModel().clearSelection(); //setValue("Seleccione");
//        seleccionProductos.setValue("Seleccione");
        cantidad.setText("");
        stock.setText("");
        precio.setText("");
        totalPagar.setText("");
        codigo.setDisable(false);
        precio.setDisable(false);
        stock.setDisable(false);
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

                        String SQL = "SELECT p.idproducto as idproducto, p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock, p.descripcion as descripcion, p.activo as activo, p.proveedor as proveedor FROM producto AS p WHERE p.codigo LIKE " + "'" + c + "'";
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
                                totalPagar.setText(String.valueOf(calcularPrecioTotal()));
                                if (isRealizarIvaCliente()) {
                                    totalIva.setText(String.valueOf(calcularPrecioTotalIva()));
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

    public void agregarProducto() {
        String s = seleccionProductos.getSelectionModel().getSelectedItem().toString();
        if (avisoAgregarCantidad()) {
            if (!avisoStock()) {
                try {

                    String SQL = "SELECT p.idproducto as idproducto, p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock, p.descripcion as descripcion, p.activo as activo, p.proveedor as proveedor FROM producto AS p WHERE p.nombre LIKE " + "'" + s + "'";
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
                            totalPagar.setText(String.valueOf(calcularPrecioTotal()));
                            if (isRealizarIvaCliente()) {
                                totalIva.setText(String.valueOf(calcularPrecioTotalIva()));
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
                alert.setContentText("Seleccione un producto o ingrese un codigo para la busqueda.");
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
        try {
            final Producto producto = getTablaProductoSeleccionada();
            posicionEnTabla = list.indexOf(producto);

            list.remove(posicionEnTabla);
            listaProductos.remove(posicionEnTabla);
            totalPagar.setText(String.valueOf(calcularPrecioTotal()));
            if (isRealizarIvaCliente()) {
                totalIva.setText(String.valueOf(calcularPrecioTotalIva()));
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

            String SQL = "SELECT p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock FROM producto AS p WHERE p.activo = true ";
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
                //seleccionClientes.setPromptText(rs.getString("nombre"));
                iva = rs.getInt("idiva");
            }
            if (iva == 3) {
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

    public ObservableList<String> tomarClientes() {
        ObservableList<String> list = FXCollections.observableArrayList();

        try {

            String SQL = "select cl.razonsocial as nombre, cl.cuit as cuit from cliente as cl where idtipopersona=1  AND cl.estado = true";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("nombre") + " - " + rs.getString("cuit"));
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
                list.add(rs.getString("nombre") + " - " + rs.getString("cuit"));
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
                total += p.getCantidad() * p.getPrecio() + calcularIvaProducto(p.getCantidad(), p.getPrecio(), p.getAlicuota());//(p.getCantidad() * p.getPrecio()) * p.getAlicuota()) / 100;
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
        try {
            final Producto producto = getTablaProductoSeleccionada();
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
        totalPagar.setText(String.valueOf(calcularPrecioTotal()));
        if (isRealizarIvaCliente()) {
            totalIva.setText(String.valueOf(calcularPrecioTotalIva()));
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
