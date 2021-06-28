package Controladores;

import Modelo.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;


public class ClientesController implements Initializable{

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private ComboBox seleccionTipoPersona;


    @FXML
    private Button botonCosteo;

    @FXML
    private TextField BuscarCliente;

    @FXML
    private Button botonFacturacion;

    @FXML
    private Button botonVentas;

    @FXML
    private Button botonStock;

    @FXML
    private ComboBox condicionIva;

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
    private TextField clienteApellido;

    @FXML
    private TextField clienteEmail;

    @FXML
    private Button botonGuardar;

    @FXML
    private Button botonLimpiar;

    @FXML
    private Button botonEliminar;

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonActualizarDatos;



    @FXML
    private TableView<Cliente> tablaCliente;

    @FXML
    private TableColumn<Cliente, String> columnaDni;

    @FXML
    private TableColumn<Cliente, String> columnaNombre;

    @FXML
    private TableColumn<Cliente, String> columnaTelefono;

    @FXML
    private TableColumn<Cliente, String> columnaDireccion;

    @FXML
    private TableColumn<Cliente, String> columnaRazon;

    @FXML
    private TableColumn<Cliente, String> columnaApellido;

    @FXML
    private TableColumn<Cliente, String> columnaEmail;

    @FXML
    private TextField idCliente;

    @FXML
    private TableColumn<Cliente, String> columnaCuit;



    ObservableList<Cliente> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);

        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);

        deshabilitarCampos();
        typedEnNumeros();
        traerCondicionIvaAcomboBox();
        traerTipoPersonaAcomboBox();
        //llenarClientes();

    }

    /*
    public void llenarClientes(){

        columnaDireccion.setCellValueFactory(new PropertyValueFactory<Cliente, String>("direccion"));
        columnaDni.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dni"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        columnaRazon.setCellValueFactory(new PropertyValueFactory<Cliente, String>("razon"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));

        list = mostrarClientes();
        tablaCliente.setItems(list);
    }

    public ObservableList<Cliente> mostrarClientes(){
        Connection conn = ConexionBD.getConnection();
        ObservableList<Cliente> list = FXCollections.observableArrayList();

        try {

            String SQL = "SELECT c.nombre as nombre, c.razon_social as rs FROM cliente c";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                Cliente c = new Cliente();
                c.getNombre(rs.getString("nombre"));
                c.getRazonSocial(rs.getString("rs"));
                list.add(c);
            }

        } catch (Exception e) {

        }
        return list;
    }
    */

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
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturas");
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

    public void limpiarDatos1(){
        clienteDni.setText("");
        clienteNombre.setText("");
        clienteTelefono.setText("");
        clienteDireccion.setText("");
        clienteRazon.setText("");
        clienteCuit.setText("");
        clienteEmail.setText("");
        clienteApellido.setText("");
        seleccionTipoPersona.getSelectionModel().clearSelection();
        condicionIva.getSelectionModel().clearSelection();
        setearPrompTextDeVuelta();


    }


    public void limpiarDatos(ActionEvent event){
        limpiarDatos1();

    }

    public void limpiarDatos2(){
        clienteDni.setText("");
        clienteNombre.setText("");
        clienteTelefono.setText("");
        clienteDireccion.setText("");
        clienteRazon.setText("");
        clienteCuit.setText("");
        clienteEmail.setText("");
        clienteApellido.setText("");
    }


    public void seleccionarTipoPersona(ActionEvent event){
        try {
            String s = seleccionTipoPersona.getSelectionModel().getSelectedItem().toString();
            if(s.equals("Juridica")) {
                limpiarDatos2();
                deshabilitarCampos();
                siEsJuridica();


            }else{
                limpiarDatos2();
                deshabilitarCampos();
                siEsFisica();

            }
        }catch (Exception e){

        }


    }

    public void siEsJuridica(){
        clienteNombre.setDisable(false);
        clienteTelefono.setDisable(false);
        clienteDireccion.setDisable(false);
        clienteEmail.setDisable(false);
        clienteRazon.setDisable(false);
        clienteCuit.setDisable(false);
    }


    public void siEsFisica(){
        clienteNombre.setDisable(false);
        clienteTelefono.setDisable(false);
        clienteDireccion.setDisable(false);
        clienteEmail.setDisable(false);
        clienteDni.setDisable(false);
        clienteTelefono.setDisable(false);
        clienteApellido.setDisable(false);
        clienteCuit.setDisable(false);
    }

    public void traerTipoPersonaAcomboBox(){
        ObservableList<Object> list = FXCollections.observableArrayList();
        try {
            Connection conn = ConexionBD.getConnection();
            Statement s = conn.createStatement();
            String SQL = "SELECT tipoPersona FROM tipo_persona";
            ResultSet rs = s.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("tipoPersona"));
            }

            seleccionTipoPersona.setItems(list);
        } catch (Exception e) {

        }

    }


    public void traerCondicionIvaAcomboBox(){

        ObservableList<Object> list = FXCollections.observableArrayList();
        try {
            Connection conn = ConexionBD.getConnection();
            Statement s = conn.createStatement();
            String SQL = "SELECT nombre FROM condicion_iva";
            ResultSet rs = s.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("nombre"));
            }

            condicionIva.setItems(list);
        } catch (Exception e) {

        }


    }


    public void guardarCliente(ActionEvent event){

        TipoPersona t = verificarTipoPersona();
        if(t.getTipopersona().equals("Juridica")){
            guardarPersonaJuridica();
        }else {
            guardarPersonaFisica();
        }

    }


    public void guardarPersonaJuridica(){
        Connection conn = ConexionBD.getConnection();

        if (!clienteRazon.getText().isBlank() && !clienteNombre.getText().isBlank() &&
            !clienteDireccion.getText().isBlank() && !clienteTelefono.getText().isBlank()
            && !clienteCuit.getText().isBlank() && !clienteEmail.getText().isBlank() &&
                !condicionIva.getSelectionModel().isEmpty() && !seleccionTipoPersona.getSelectionModel().isEmpty()) {

            try {
                String sql = "INSERT INTO CLIENTE(razonsocial,nombre,direccion,cuit,telefono,id_condicioniva,email,idtipopersona,apellido) VALUES (?,?,?,?,?,?,?,?,?)";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setString(1,clienteRazon.getText());
               ps.setString(2,clienteNombre.getText().toLowerCase());
               ps.setString(3,clienteDireccion.getText());
               ps.setString(4,clienteCuit.getText());
               ps.setString(5,clienteTelefono.getText());
               ps.setInt(6,verificarCondicionIva().getIdcondicioniva());
               ps.setString(7,clienteEmail.getText());
               ps.setInt(8,verificarTipoPersona().getId_tipopersona());
               ps.setString(9,"0");
                ps.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operacion exitosa!");
                alert.setContentText("Se ha agregado con exito el cliente");
                alert.showAndWait();
                limpiarDatos1();
                deshabilitarCampos();





            }
                catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Complete todos los campos");
            alert.showAndWait();
        }
    }



    public void guardarPersonaFisica(){
        Connection conn = ConexionBD.getConnection();

        if (!clienteDni.getText().isBlank() && !clienteNombre.getText().isBlank() &&
                !clienteDireccion.getText().isBlank()&& !clienteTelefono.getText().isBlank()
                && !clienteCuit.getText().isBlank() && !clienteEmail.getText().isBlank() && !clienteApellido.getText().isBlank() && !condicionIva.getSelectionModel().isEmpty() && !seleccionTipoPersona.getSelectionModel().isEmpty()) {

            try {
                String sql = "INSERT INTO CLIENTE(nombre,direccion,cuit,telefono,id_condicioniva,email,idtipopersona,dni,apellido) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,clienteNombre.getText().toLowerCase());
                ps.setString(2,clienteDireccion.getText());
                ps.setString(3,clienteCuit.getText());
                ps.setString(4,clienteTelefono.getText());
                ps.setInt(5,verificarCondicionIva().getIdcondicioniva());
                ps.setString(6,clienteEmail.getText());
                ps.setInt(7,verificarTipoPersona().getId_tipopersona());
                ps.setString(8,clienteDni.getText());
                ps.setString(9,clienteApellido.getText());
                ps.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operacion exitosa!");
                alert.setContentText("Se ha agregado con exito el cliente");
                alert.showAndWait();
                limpiarDatos1();

                deshabilitarCampos();


            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Complete todos los campos");
            alert.showAndWait();
        }
    }







    public TipoPersona verificarTipoPersona(){
        String s = seleccionTipoPersona.getSelectionModel().getSelectedItem().toString();

        TipoPersona t = new TipoPersona();
        try {
            Connection conn = ConexionBD.getConnection();
            Statement stat = conn.createStatement();
            String SQL = "SELECT * FROM tipo_persona WHERE tipopersona=" + "'"+ s +"'";
            ResultSet rs = stat.executeQuery(SQL);

            if (rs.next()) {
               t.setId_tipopersona(rs.getInt("idtipo_persona"));
               t.setTipopersona(rs.getString("tipopersona"));
            }

        } catch (Exception e) {

        }
        return t;

    }


    public CondicionIva verificarCondicionIva(){
        String s1 = condicionIva.getSelectionModel().getSelectedItem().toString();
        CondicionIva c = new CondicionIva();
        try {
            Connection conn = ConexionBD.getConnection();
            Statement stat = conn.createStatement();
            String SQL = "SELECT * FROM condicion_iva WHERE nombre ="+ "'" + s1 + "'";
            ResultSet rs = stat.executeQuery(SQL);

            if (rs.next()) {
                c.setIdcondicioniva(rs.getInt("idcondicioniva"));
                c.setCodigo(rs.getInt("codigo"));
                c.setNombre(rs.getString("nombre"));
            }

        } catch (Exception e) {

        }
        return c;

    }


    public void typedEnNumeros(){
        clienteDni.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if(newValue==null){
                    clienteDni.setText(null);
                }else {
                    if (!newValue.matches("\\d*")) {
                        clienteDni.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });
        clienteTelefono.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    clienteTelefono.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        clienteTelefono.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            }
        });

    }


    public void deshabilitarCampos(){

        clienteDni.setDisable(true);
        clienteNombre.setDisable(true);
        clienteTelefono.setDisable(true);
        clienteDireccion.setDisable(true);
        clienteRazon.setDisable(true);
        clienteCuit.setDisable(true);
        clienteEmail.setDisable(true);
        clienteApellido.setDisable(true);
    }


    public void setearPrompTextDeVuelta(){
        condicionIva.setPromptText("Seleccione");
        condicionIva.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty) ;
                if (empty || item == null) {
                    setText("Seleccione");
                } else {
                    setText(item);
                }
            }
        });
        seleccionTipoPersona.setPromptText("Seleccione");
        seleccionTipoPersona.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty) ;
                if (empty || item == null) {
                    setText("Seleccione");
                } else {
                    setText(item);
                }
            }
        });
    }

    public void busquedaCliente(ActionEvent event){
        Cliente c;
        c=retornarCliente(BuscarCliente.getText());
        if(c.getNombre()!=null){
            mostrarClienteEnTabla(BuscarCliente.getText(),c);





        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Error,");
            alert.setContentText("No se ha encontrado el cliente, por favor, realize una nueva busqueda");
            alert.showAndWait();
        }
    }


    public ObservableList<Cliente> buscarCliente(String s){
        Connection conn = ConexionBD.getConnection();
        ObservableList<Cliente> lista = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT c.razonsocial as razonsocial, c.dni as dni, c.direccion as direccion, c.nombre as nombre, c.apellido as apellido, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva FROM cliente c WHERE c.dni="+ "'" + s + "'"+" OR c.nombre="+ "'" + s.toLowerCase() +"'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while(rs.next()){
                Cliente c = new Cliente();
                c.setDni(rs.getString("dni"));
                c.setDireccion(rs.getString("direccion"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCuit(rs.getString("cuit"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                c.setRazonSocial(rs.getString("razonsocial"));
                c.setTipoPersona(buscarTipoPersona(rs.getInt("idtipopersona")));
                c.setCondicionIVA(buscarCondicionIva(rs.getInt("idcondicioniva")));
                lista.add(c);
            }


        } catch (SQLException throwables) {

        }

        return lista;
    }

    public Cliente retornarCliente(String s){
        Connection conn = ConexionBD.getConnection();
        Cliente c = new Cliente();
        try {
            String SQL = "SELECT c.razonsocial as razonsocial, c.dni as dni, c.direccion as direccion, c.nombre as nombre, c.apellido as apellido, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva FROM cliente c WHERE c.dni="+ "'" + s + "'"+" OR c.nombre="+ "'" + s.toLowerCase() +"'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while(rs.next()){
                c.setDni(rs.getString("dni"));
                c.setDireccion(rs.getString("direccion"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCuit(rs.getString("cuit"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                c.setRazonSocial(rs.getString("razonsocial"));
                c.setTipoPersona(buscarTipoPersona(rs.getInt("idtipopersona")));
                c.setCondicionIVA(buscarCondicionIva(rs.getInt("idcondicioniva")));

            }


        } catch (SQLException throwables) {

        }
        return c;
    }

    public TipoPersona buscarTipoPersona(int i){
        Connection conn = ConexionBD.getConnection();
        TipoPersona t = new TipoPersona();
        try {
            String SQL = "SELECT idtipo_persona as idpersona, tipopersona FROM tipo_persona WHERE idtipo_persona="+ "'" + i + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            if(rs.next()){
                t.setId_tipopersona(rs.getInt("idpersona"));
                t.setTipopersona(rs.getString("tipopersona"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }


    public CondicionIva buscarCondicionIva(int i){
        Connection conn = ConexionBD.getConnection();
        CondicionIva c = new CondicionIva();
        try {
            String SQL = "SELECT idcondicioniva,codigo,nombre FROM condicion_iva WHERE idcondicioniva="+ "'" + i + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            if(rs.next()){
              c.setIdcondicioniva(rs.getInt("idcondicioniva"));
              c.setNombre(rs.getString("nombre"));
              c.setCodigo(rs.getInt("codigo"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }




    public void mostrarClienteEnTabla(String s,Cliente c){
        if(c.getTipoPersona().getTipopersona().equals("Juridica")){
            columnaDni.setVisible(false);
            columnaApellido.setVisible(false);
            columnaRazon.setCellValueFactory(new PropertyValueFactory<Cliente,String>("RazonSocial"));
            columnaCuit.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cuit"));
            columnaDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
            columnaEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
            tablaCliente.setItems(buscarCliente(s));
        }else {
            columnaRazon.setVisible(false);
            columnaDni.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dni"));
            columnaApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
            columnaCuit.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cuit"));
            columnaDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
            columnaEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
            tablaCliente.setItems(buscarCliente(s));
        }

    }


//    public void guardarCliente(ActionEvent e) {
//        Connection conn = ConexionBD.getConnection();
//        /* ACOMODAR TODO ESTO CON CLIENTE Y CREAR EN LA BASE DE DATOS
//        if (clienteDni.getText() != null && clienteNombre.getText() != null &&
//        clienteDireccion.getText() != null && clienteRazon.getText() != null && menuCliente.getText() != null
//        && clienteCuit.getText() != null){
//            try {
//
//                String sql = "INSERT INTO CUENTA(cuenta,codigo_cuenta,recibe_saldo,saldo_actual,idtipo,habilitada_no) VALUES (?,?,?,?,?,?)";
//                PreparedStatement ps = conn.prepareStatement(sql);
//
//                ps.setString(1, clienteDni.getText());
//                ps.setInt(2, Integer.parseInt(CodigoCuenta.getText()));
//                ps.setInt(3, recibeSaldoSioNo(BotonSi));
//                ps.setFloat(4, 0);
//                String s = comboBox.getSelectionModel().getSelectedItem().toString();
//                ps.setInt(5, verificarTipoCuenta(s));
//                ps.setString(6,"Si");
//
//                ps.execute();
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Atencion");
//                alert.setHeaderText("Operacion exitosa!");
//                alert.setContentText("Se ha agregado con exito la cuenta");
//                alert.showAndWait();
//                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
//
//            } catch (Exception e) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Error");
//                alert.setHeaderText("Por favor,");
//                alert.setContentText("Ingrese solo digitos en el codigo de la cuenta");
//                alert.showAndWait();
//            }
//        }else{
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Atencion!");
//            alert.setHeaderText("Por favor,");
//            alert.setContentText("Complete todos los campos");
//            alert.showAndWait();
//        }
//        }
//    */
//    }


}
