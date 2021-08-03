package Controladores;

import Modelo.FacturaVentas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
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
import java.util.Date;
import java.util.ResourceBundle;

public class FacturasController implements Initializable {

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private TableView tablaFactura;

    @FXML
    private TableColumn<FacturaVentas, Integer> columnaNumero;

    @FXML
    private TableColumn<FacturaVentas, Double> columnaDeuda;


    @FXML
    private TableColumn<FacturaVentas, String> columnaCliente;

    @FXML
    private TableColumn<FacturaVentas, Date> columnaFecha;

    @FXML
    private TableColumn<FacturaVentas, Double> columnaTotal;


    Connection conn = ConexionBD.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);
        ObservableList<FacturaVentas> facturaVentas= traerDatosAtabla();

    }

    public void accederCliente(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Clientes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Clientes");
        stage.show();
    }

    public void accederStock(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Stock.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stock");
        stage.show();
    }

    public void accederCosteo(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/MetodoCosteo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Metodo de Costeo");
        stage.show();
    }

    public void accederVentas(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Ventas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ventas");
        stage.show();
    }


    public ObservableList<FacturaVentas> traerDatosAtabla() {
        ObservableList<FacturaVentas> lista = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT f.numero_factura,c.nombre,c.apellido,c.razonsocial,v.total,v.fecha FROM venta v INNER JOIN cliente c ON c.idcliente = v.idcliente INNER JOIN factura f ON f.idventa=v.idventa WHERE f.facturada=true";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                if (rs.getString("nombre") == null) {
                    FacturaVentas f = new FacturaVentas();
                    f.setCliente(rs.getString("razonsocial"));
                    f.setNumero(rs.getString("numero_factura"));
                    f.setFecha(rs.getDate("fecha"));
                    f.setTotal(rs.getFloat("total"));
                    lista.add(f);
                } else {
                    FacturaVentas f = new FacturaVentas();
                    f.setCliente(rs.getString("nombre") + " " + rs.getString("apellido"));
                    f.setNumero(rs.getString("numero_factura"));
                    f.setFecha(rs.getDate("fecha"));
                    f.setTotal(rs.getFloat("total"));
                    lista.add(f);
                }
            }

        } catch (Exception e) {

        }
        return lista;
    }

}