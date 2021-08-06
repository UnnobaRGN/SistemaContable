package Controladores;

import Modelo.Asiento;
import Modelo.Producto;
import Modelo.UsuarioLogeado;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    @FXML
    private Button botonFacturacion;

    @FXML
    private Button botonClientes;

    @FXML
    private Button botonCosteo;

    @FXML
    private Button botonVentas;

    @FXML
    private TextField textoCodigo;

    @FXML
    private TextField textoNombre;

    @FXML
    private TextField textoProveedor;

    @FXML
    private TextField textoPrecio;

    @FXML
    private TextField textoCantidad;

    @FXML
    private Button botonNuevoProducto;

    @FXML
    private Button deshabilitarP;

    @FXML
    private Button habilitarP;

    @FXML
    private TextField textoDescripcion;

    @FXML
    private TextField textoAlicuota;

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonQuitarFiltro;

    @FXML
    private TableView tablaStock;

    @FXML
    private TableColumn<Producto, Integer> columnaCodigo;

    @FXML
    private TableColumn<Producto, String> columnaNombre;

    @FXML
    private TableColumn<Producto, String> columnaProveedor;

    @FXML
    private TableColumn<Producto, String> columnaPrecio;

    @FXML
    private TableColumn<Producto, Integer> columnaStock;

    @FXML
    private TableColumn<Producto, Integer> columnaAlicuota;

    @FXML
    private TableColumn<Producto, Integer> columnaDescripcion;

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private ImageView imagenDerecha = new ImageView();

    private UsuarioLogeado u = UsuarioLogeado.getInstance();
    Connection conn = ConexionBD.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);

        typedEnNumeros();

        if(u.getId()!=1) {
            habilitarP.setVisible(false);
            deshabilitarP.setVisible(false);
        }

    }

    public void accerderFacturacion(ActionEvent e) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturas");
        stage.show();
    }

    public void accederClientes(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Clientes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clientes");
        stage.show();
    }

    public void accederCosteo(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/MetodoCosteo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Metodo de Costeo");
        stage.show();
    }

    public void accederVentas(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Ventas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ventas");
        stage.show();
    }

    public void crearProducto(String codigo, String nombre, String proveedor, String precio, String cantidad){



    }

    public boolean noVacios(){

        if (textoCodigo.getText().isEmpty() && textoProveedor.getText().isEmpty() &&
                textoNombre.getText().isEmpty() && textoCantidad.getText().isEmpty() &&
                textoPrecio.getText().isEmpty()){
            return false;
        }
        return true;
    }

    @FXML
    void alicuotaCorrecta(){
        try{
            float a = textoAlicuota.getText().isBlank()?0:Float.parseFloat(textoAlicuota.getText());
            if(a != 10.5 || a != 21 ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Ingrese alicuotas de 10.5% o 21%.");
                alert.showAndWait();
            }
        }
        catch (Exception exception){
            System.out.println("Excepcion no conocida (mentira es en cuotas)");
        }
    }

    public void accederQuitarFiltro(ActionEvent e) throws IOException{
        //Quita los filtros
        textoCodigo.setText(null);
        textoNombre.setText(null);
        textoProveedor.setText(null);
        textoPrecio.setText(null);
        textoCantidad.setText(null);
        mostrarDatos(); //Muestra los datos predefinidos
    }

    public void nuevoActualizarProducto(){
        if(!textoCodigo.getText().isBlank()){
            String codP = textoCodigo.getText();
            if(codigoExiste(codP)){
                //Modificar producto
            }else {
                //Crearlo
                //Corroborrar todos los campos
                if(!algunCampoVacio()){
                        crearProducto();
                }else {
                    alerta("Ingresar todos los campos para crear un producto.");
                }
            }
        }else{
            alerta("Ingrese un codigo para registrar un nuevo producto o modificarlo.");
        }
    }

    private boolean algunCampoVacio() {
        if(textoCodigo.getText().isBlank() || textoNombre.getText().isBlank() || textoDescripcion.getText().isBlank() || textoProveedor.getText().isBlank() || textoPrecio.getText().isBlank() || textoCantidad.getText().isBlank() || textoAlicuota.getText().isBlank()){
            return true;
        } else{
            return false;
        }
    }

    public void mostrarDatos(){
        try {
            String SQL = "SELECT p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock, p.descripcion as descripcion, p.proveedor as proveedor, p.alicuota as alicuota FROM producto AS p WHERE p.estado = true";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
                columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
                columnaStock.setCellValueFactory(new PropertyValueFactory("stock"));
                String precio = String.valueOf(rs.getDouble("precio"));
                columnaPrecio.setCellValueFactory(new PropertyValueFactory("$"+precio));
                columnaAlicuota.setCellValueFactory(new PropertyValueFactory("alicuota"));
                columnaProveedor.setCellValueFactory(new PropertyValueFactory("proveedor"));
                columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
            }
        }catch (Exception e){
            System.out.println("Error al generar la busqueda del producto.");
        }
    }

    public void buscarProductoCodigo(){
        if(!textoCodigo.getText().isEmpty()){
            //Busca
            String codP = textoCodigo.getText();
            if(codigoExiste(codP)){
                try {
                    String SQL = "SELECT p.codigo as codigo, p.nombre as nombre, p.precio as precio, p.stock as stock, p.descripcion as descripcion, p.proveedor as proveedor, p.alicuota as alicuota FROM producto AS p WHERE p.codigo LIKE " + "'" + codP + "'";
                    Statement statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(SQL);

                    while (rs.next()) {
                        columnaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
                        columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
                        textoNombre.setText(rs.getString("nombre"));
                        columnaStock.setCellValueFactory(new PropertyValueFactory("stock"));
                        textoCantidad.setText(String.valueOf(rs.getInt("stock")));
                        String precio = String.valueOf(rs.getDouble("precio"));
                        columnaPrecio.setCellValueFactory(new PropertyValueFactory("$"+precio));
                        textoPrecio.setText(precio);
                        columnaAlicuota.setCellValueFactory(new PropertyValueFactory("alicuota"));
                        textoAlicuota.setText(String.valueOf(rs.getDouble("alicuota")));
                        columnaProveedor.setCellValueFactory(new PropertyValueFactory("proveedor"));
                        textoProveedor.setText(rs.getString("proveedor"));
                        columnaDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
                        textoDescripcion.setText(rs.getString("descripcion"));
                    }
                }catch (Exception e){
                    System.out.println("Error al generar la busqueda del producto.");
                }
            }else {
                alerta("Ingrese un codigo correcto.");
            }
        }else{
            alerta("Ingrese un codigo para la busqueda del producto.");
        }
    }

    public void alerta(String a){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Atencion!");
        alert.setHeaderText("Por favor,");
        alert.setContentText(a);
        alert.showAndWait();
    }

    private boolean codigoExiste(String codigoP) {
        boolean r = false;
        try {
            String SQL = "SELECT p.codigo as codigo FROM producto AS p WHERE p.codigo LIKE " + "'" + codigoP + "'";
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

    public void typedEnNumeros() {
        textoCodigo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    textoCodigo.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        textoCodigo.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });
        textoAlicuota.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    textoAlicuota.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        textoAlicuota.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });
        textoCantidad.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    textoCantidad.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        textoCantidad.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });
        textoPrecio.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    textoPrecio.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        textoPrecio.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });
    }

}
