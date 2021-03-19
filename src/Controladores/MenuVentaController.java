package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class MenuVentaController implements Initializable {

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private ImageView imagenCentro = new ImageView();

    @FXML
    private Button botonCosteo;

    @FXML
    private Button botonClientes;

    @FXML
    private Button botonVentas;

    @FXML
    private Button botonStock;

    @FXML
    private Button botonFacturacion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingFile = new File("Imagenes/imagenMenu.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        imagenCentro.setImage(brandingImage);

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

    public void accederClientes(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Clientes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clienes");
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

    public void accederFacturacion(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturacion.fxml"));
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

}
