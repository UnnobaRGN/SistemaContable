package Controladores;

import Modelo.Cliente;
import Modelo.Cuenta_Asiento;
import Modelo.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;


public class ClientesController implements Initializable{

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private Button botonCosteo;

    @FXML
    private Button botonFacturacion;

    @FXML
    private Button botonVentas;

    @FXML
    private Button botonStock;

    @FXML
    private MenuButton menuCliente;

    @FXML
    private MenuItem itemA;

    @FXML
    private MenuItem itemB;

    @FXML
    private TextField clienteDni;

    @FXML
    private TextField clienteCuit;

    @FXML
    private TextField clienteNombre;

    @FXML
    private TextField clienteTelefono;

    @FXML
    private TextField clienteDireccion;

    @FXML
    private TextField clienteRazon;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonLimpiar;

    @FXML
    private TableView<Cliente> tablaCliente;

    @FXML
    private TableColumn<Producto, Integer> columnaDni;

    @FXML
    private TableColumn<Producto, String> columnaNombre;

    @FXML
    private TableColumn<Producto, Integer> columnaTelefono;

    @FXML
    private TableColumn<Producto, String> columnaDireccion;

    @FXML
    private TableColumn<Producto, String> columnaRazon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
            imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
            imagenDerecha.setImage(brandingIzq);

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

    public void accederFacturacion(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturacion.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturacion");
        stage.show();
    }

    public void accederStock(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Stock.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stock");
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

    public void limpiarDatos(ActionEvent e){
        clienteDni.setText(null);
        clienteNombre.setText(null);
        clienteTelefono.setText(null);
        clienteDireccion.setText(null);
        clienteRazon.setText(null);
        clienteCuit.setText(null);
        menuCliente.setText("Seleccione");
    }

    public void guardarCliente(ActionEvent e) {
        Connection conn = ConexionBD.getConnection();
        /* ACOMODAR TODO ESTO CON CLIENTE Y CREAR EN LA BASE DE DATOS
        if (clienteDni.getText() != null && clienteNombre.getText() != null &&
        clienteDireccion.getText() != null && clienteRazon.getText() != null && menuCliente.getText() != null
        && clienteCuit.getText() != null){
            try {

                String sql = "INSERT INTO CUENTA(cuenta,codigo_cuenta,recibe_saldo,saldo_actual,idtipo,habilitada_no) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, clienteDni.getText());
                ps.setInt(2, Integer.parseInt(CodigoCuenta.getText()));
                ps.setInt(3, recibeSaldoSioNo(BotonSi));
                ps.setFloat(4, 0);
                String s = comboBox.getSelectionModel().getSelectedItem().toString();
                ps.setInt(5, verificarTipoCuenta(s));
                ps.setString(6,"Si");

                ps.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operacion exitosa!");
                alert.setContentText("Se ha agregado con exito la cuenta");
                alert.showAndWait();
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Ingrese solo digitos en el codigo de la cuenta");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Complete todos los campos");
            alert.showAndWait();
        }
        }
    */
    }


}
