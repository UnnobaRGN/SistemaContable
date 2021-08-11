package Controladores;

import Modelo.FacturaVentas;
import Modelo.cuentaCorrienteCliente;
import Reporte.Reporte;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class CuentaCorrienteController implements Initializable {
    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    Connection conn = ConexionBD.getConnection();

    @FXML
    private Button botonCobrar;

    @FXML
    private TableView<cuentaCorrienteCliente> tablaCuentaCorriente;

    @FXML
    private TableColumn<cuentaCorrienteCliente,String> columnaNumero;

    @FXML
    private TableColumn<cuentaCorrienteCliente,String> columnaFechaPago;

    @FXML
    private TableColumn<cuentaCorrienteCliente,String> columnaFechaEmi;

    @FXML
    private TableColumn<cuentaCorrienteCliente,String> columnaCobrado;


    @FXML
    private TableColumn<cuentaCorrienteCliente,String> columnaDeuda;

    @FXML
    private TableColumn<cuentaCorrienteCliente,Integer> columnaCuotasTotales;

    @FXML
    private TableColumn<cuentaCorrienteCliente,Integer> columnaCuotasPagadas;

    @FXML
    private Text lblNombreYapellido;


    @FXML
    private TextField CUITcliente;
    ClientesController clientesController1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File brandingDerecha = new File("Imagenes/pep.png");
//        Image brandingDer = new Image(brandingDerecha.toURI().toString());
//        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);


    }


//    public void accederCliente(ActionEvent event) throws IOException {
//        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Clientes.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(asiento);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.setTitle("Clientes");
//        stage.show();
//    }
//
//    public void accederVentas(ActionEvent e) throws IOException {
//        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Ventas.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(asiento);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.setTitle("Ventas");
//        stage.show();
//    }
//
//    public void accederFacturacion(ActionEvent e) throws IOException{
//        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturas.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(asiento);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.setTitle("Facturas");
//        stage.show();
//    }
//
//    public void accederStock(ActionEvent e) throws IOException{
//        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Stock.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(asiento);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.setTitle("Stock");
//        stage.show();
//    }
//
//    public void accederCosteo(ActionEvent e) throws IOException{
//        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/MetodoCosteo.fxml"));
//        Stage stage = new Stage();
//        Scene scene = new Scene(asiento);
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.setTitle("Metodo de Costeo");
//        stage.show();
//    }

    public void recibeCuit(ClientesController clientesController,String cuit,String nombreOrazon){
        CUITcliente.setText(cuit);
        lblNombreYapellido.setText(nombreOrazon);
        clientesController1=clientesController;

    }


    public ObservableList<cuentaCorrienteCliente> BuscarFacturas(){



        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        ObservableList<cuentaCorrienteCliente> lista = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT DISTINCT f.letra_factura, f.numero_factura,f.fecha_emision,f.fecha_pago,f.total_pagado,f.total_debe,f.cuotas_totales,f.cuotas_pagadas " +
                    "FROM venta v " +
                    " INNER JOIN cliente c ON c.idcliente = v.idcliente " +
                    " INNER JOIN factura f ON f.idventa=v.idventa " +
                    "WHERE c.cuit="+"'"+CUITcliente.getText()+"'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                cuentaCorrienteCliente cuentaCorrienteCliente = new cuentaCorrienteCliente();
                if(rs.getDate("fecha_pago")==null){
                    cuentaCorrienteCliente.setFechaDePago(" ");
                }else{
                    cuentaCorrienteCliente.setFechaDePago(df.format(rs.getDate("fecha_pago")));
                }
                cuentaCorrienteCliente.setNumero(rs.getString("letra_factura")+"-"+rs.getString("numero_factura"));
                cuentaCorrienteCliente.setFechaEmision(df.format(rs.getDate("fecha_emision")));
                cuentaCorrienteCliente.setCobrado("$"+rs.getFloat("total_pagado"));
                cuentaCorrienteCliente.setDeuda("$"+rs.getFloat("total_debe"));
                cuentaCorrienteCliente.setCuotasTotales(rs.getInt("cuotas_totales"));
                cuentaCorrienteCliente.setCuotasPagadas(rs.getInt("cuotas_pagadas"));
                lista.add(cuentaCorrienteCliente);
            }

        } catch (Exception e) {

        }
        return lista;
    }


    public void Buscar(ActionEvent event){
        mostrarDatosEnTabla();
    }


    public void mostrarDatosEnTabla() {

        columnaNumero.setCellValueFactory(new PropertyValueFactory<cuentaCorrienteCliente, String>("numero"));
        columnaFechaEmi.setCellValueFactory(new PropertyValueFactory<cuentaCorrienteCliente, String>("fechaEmision"));
        columnaFechaPago.setCellValueFactory(new PropertyValueFactory<cuentaCorrienteCliente, String>("fechaDePago"));
        columnaCobrado.setCellValueFactory(new PropertyValueFactory<cuentaCorrienteCliente, String>("cobrado"));
        columnaDeuda.setCellValueFactory(new PropertyValueFactory<cuentaCorrienteCliente, String>("deuda"));
        columnaCuotasTotales.setCellValueFactory(new PropertyValueFactory<cuentaCorrienteCliente, Integer>("cuotasTotales"));
        columnaCuotasPagadas.setCellValueFactory(new PropertyValueFactory<cuentaCorrienteCliente, Integer>("cuotasPagadas"));
        tablaCuentaCorriente.setItems(BuscarFacturas());

    }

    public cuentaCorrienteCliente retornarCuentaDeCliente(String numFactura) {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        cuentaCorrienteCliente cuentaCorrienteCliente = new cuentaCorrienteCliente();
        try {
            String SQL = "SELECT f.letra_factura, v.total_iva,v.idmediopago, f.numero_factura,f.fecha_emision,f.fecha_pago,f.total_pagado,f.total_debe,f.cuotas_totales,f.cuotas_pagadas,f.valor_cuota " +
                    "FROM venta v " +
                    " INNER JOIN cliente c ON c.idcliente = v.idcliente " +
                    " INNER JOIN factura f ON f.idventa=v.idventa " +
                    "WHERE f.numero_factura=" + "'" + numFactura + "'";

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()) {
                if(rs.getDate("fecha_pago")==null){
                    cuentaCorrienteCliente.setFechaDePago(" ");
                }else{
                    cuentaCorrienteCliente.setFechaDePago(df.format(rs.getDate("fecha_pago")));
                }
                cuentaCorrienteCliente.setLetra_factura(rs.getString("letra_factura"));
                cuentaCorrienteCliente.setNumero(rs.getString("numero_factura"));
                cuentaCorrienteCliente.setFechaEmision(df.format(rs.getDate("fecha_emision")));
                cuentaCorrienteCliente.setCobrado(String.valueOf(rs.getFloat("total_pagado")));
                cuentaCorrienteCliente.setDeuda(String.valueOf(rs.getFloat("total_debe")));
                cuentaCorrienteCliente.setCuotasTotales(rs.getInt("cuotas_totales"));
                cuentaCorrienteCliente.setCuotasPagadas(rs.getInt("cuotas_pagadas"));
                cuentaCorrienteCliente.setTotal_IVA(rs.getFloat("total_iva"));
                cuentaCorrienteCliente.setMedioDePago(rs.getInt("idmediopago"));
                cuentaCorrienteCliente.setTotalcuotas(rs.getFloat("valor_cuota"));

            }

        } catch (Exception e) {

        }

        return cuentaCorrienteCliente;

    }


    @FXML
    private void cobrarFact(ActionEvent event){

        if(!tablaCuentaCorriente.getSelectionModel().isEmpty()) {
            String s;
            s = tablaCuentaCorriente.getSelectionModel().getSelectedItem().getNumero().substring(2);
            cuentaCorrienteCliente cuentaCorrienteCliente = retornarCuentaDeCliente(s);
            if(cuentaCorrienteCliente.getCuotasTotales() == cuentaCorrienteCliente.getCuotasPagadas()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Error!");
                alert.setContentText("La factura ya se encuentra en estado cobrada!");
                alert.showAndWait();
            }else{
                realizarCalculos(cuentaCorrienteCliente);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operación exitosa!");
                int var = cuentaCorrienteCliente.getCuotasPagadas()+1;
                alert.setContentText("Se ha realizado el pago de la cuota "+var+" correspondiente a la factura "+cuentaCorrienteCliente.getLetra_factura()+"-"+cuentaCorrienteCliente.getNumero());
                alert.showAndWait();
                mostrarDatosEnTabla();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("Error!");
            alert.setContentText("Por favor, seleccione una factura");
            alert.showAndWait();
        }


    }




    public void realizarCalculos(cuentaCorrienteCliente cliente) {

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        int VarAux=cliente.getCuotasPagadas()+1;
        if(VarAux==cliente.getCuotasTotales()) {
            try {

                String SQL = "  UPDATE factura SET cuotas_pagadas=?,total_debe=?,total_pagado=?,fecha_pago=?,facturada=? WHERE numero_factura="+"'"+cliente.getNumero()+"'";
                PreparedStatement ps = conn.prepareStatement(SQL);
                ps.setInt(1,cliente.getCuotasTotales());
                ps.setFloat(2,0);
                ps.setFloat(3,cliente.getTotal_IVA());
                ps.setDate(4,sqlDate);
                ps.setBoolean(5,true);
                ps.execute();

                Reporte r = new Reporte();
                r.crearPDF(cliente.getNumero());

                }catch (Exception e){

            }
        }else{
            try {
                String SQL = "UPDATE factura SET cuotas_pagadas=?,total_debe=?,total_pagado=?,fecha_pago=? WHERE numero_factura=" + "'" + cliente.getNumero() + "'";
                PreparedStatement ps = conn.prepareStatement(SQL);
                ps.setInt(1,cliente.getCuotasPagadas()+1);
                ps.setFloat(2,Float.parseFloat(cliente.getDeuda())-cliente.getTotalcuotas());
                ps.setFloat(3,Float.parseFloat(cliente.getCobrado())+cliente.getTotalcuotas());
                ps.setDate(4,sqlDate);
                ps.execute();

            }catch (Exception e){




            }
        }

    }

}



