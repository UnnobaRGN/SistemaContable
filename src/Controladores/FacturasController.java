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

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class FacturasController implements Initializable {

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private TableView tablaFactura;

    @FXML
    private TableColumn<FacturaVentas, String> columnaNumero;

    @FXML
    private TableColumn<FacturaVentas, String> columnaDeuda;


    @FXML
    private TableColumn<FacturaVentas, String> columnaCliente;

    @FXML
    private TableColumn<FacturaVentas, String> columnaFecha;

    @FXML
    private TableColumn<FacturaVentas, String> columnaSubtotal;

    ObservableList<FacturaVentas> lista;

    Connection conn = ConexionBD.getConnection();

    @FXML
    private Label facturaLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);
        mostrarFacturasPagas();

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


    public ObservableList<FacturaVentas> traerDatosAtabla(boolean t) {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        ObservableList<FacturaVentas> lista = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT f.letra_factura as letra,f.total_debe as deuda, f.numero_factura,c.nombre,c.apellido,c.razonsocial,v.total_iva as total,f.fecha_emision FROM venta v INNER JOIN cliente c ON c.idcliente = v.idcliente INNER JOIN factura f ON f.idventa=v.idventa WHERE f.facturada="+"'"+t+"'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                if (rs.getString("nombre") == null) {
                    FacturaVentas f = new FacturaVentas();
                    f.setCliente(rs.getString("razonsocial"));
                    f.setNumero(rs.getString("letra")+"-"+rs.getString("numero_factura"));
                    f.setFecha(df.format(rs.getDate("fecha_emision")));
                    f.setTotal("$"+ rs.getFloat("total"));
                    f.setDeuda("$"+ rs.getFloat("deuda"));
                    lista.add(f);
                } else {
                    FacturaVentas f = new FacturaVentas();
                    f.setCliente(rs.getString("nombre") + " " + rs.getString("apellido"));
                    f.setNumero(rs.getString("letra")+"-"+rs.getString("numero_factura"));
                    f.setFecha(df.format(rs.getDate("fecha_emision")));
                    f.setTotal("$"+rs.getFloat("total"));
                    f.setDeuda("$"+ rs.getFloat("deuda"));
                    lista.add(f);
                }
            }

        } catch (Exception e) {

        }
        return lista;
    }



    public void mostrarFacturasPagas(){
            facturaLabel.setText("FACTURAS PAGADAS");
            columnaSubtotal.setMinWidth(200);
            columnaSubtotal.setVisible(true);
            columnaDeuda.setVisible(false);
            columnaNumero.setCellValueFactory(new PropertyValueFactory<FacturaVentas,String>("numero"));
            columnaCliente.setCellValueFactory(new PropertyValueFactory<FacturaVentas,String>("cliente"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<FacturaVentas,String>("fecha"));
            columnaSubtotal.setCellValueFactory(new PropertyValueFactory<FacturaVentas,String>("total"));
            lista=traerDatosAtabla(true);
            tablaFactura.setItems(lista);

        }


        public void mostrarFacturasImpagas(){
            facturaLabel.setText("FACTURAS NO PAGADAS");
            columnaDeuda.setMinWidth(200);
            columnaSubtotal.setVisible(false);
            columnaDeuda.setVisible(true);
            columnaNumero.setCellValueFactory(new PropertyValueFactory<FacturaVentas,String>("numero"));
            columnaCliente.setCellValueFactory(new PropertyValueFactory<FacturaVentas,String>("cliente"));
            columnaFecha.setCellValueFactory(new PropertyValueFactory<FacturaVentas,String>("fecha"));
            columnaDeuda.setCellValueFactory(new PropertyValueFactory<FacturaVentas,String>("deuda"));
            lista=traerDatosAtabla(false);
            tablaFactura.setItems(lista);

        }


        public void facturasSinPagar(ActionEvent e){
            tablaFactura.getItems().clear();
            mostrarFacturasImpagas();


        }


        public void facturasPagadas(ActionEvent e){
            tablaFactura.getItems().clear();
            mostrarFacturasPagas();


        }


        public void verDetalle(ActionEvent e){

        }


}