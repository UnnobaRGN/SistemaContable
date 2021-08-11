package Controladores;

import Modelo.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static Controladores.EmailValidatorMain.esValid;




public class ClientesController implements Initializable{

    @FXML
    private ImageView imagenDerecha = new ImageView();

    @FXML
    private ImageView imagenIzquierda = new ImageView();

    @FXML
    private ComboBox seleccionTipoPersona;

    Connection conn = ConexionBD.getConnection();

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
    private Button botonBaja;

    @FXML
    private Button botonBuscar;

    @FXML
    private Button botonActualizarDatos;

    @FXML
    private TextField textoIdCliente;

    @FXML
    private TableView<Cliente> tablaCliente;

    @FXML
    private TableColumn<Cliente,String> columnaCondicionIva;

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
    private Button habilitarCliente;

    @FXML
    private TextField textoHabilitar;


    private UsuarioLogeado u = UsuarioLogeado.getInstance();

    ClientesController clientesController;

    ObservableList<Cliente> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientesController=this;
        File brandingDerecha = new File("Imagenes/pep.png");
        Image brandingDer = new Image(brandingDerecha.toURI().toString());
        imagenIzquierda.setImage(brandingDer);
        File brandingIzquierda = new File("Imagenes/asd.png");
        Image brandingIzq = new Image(brandingIzquierda.toURI().toString());
        imagenDerecha.setImage(brandingIzq);
        mostrarDatos();
        deshabilitarCampos();
        typedEnNumeros();
        typedEnLetras();
        traerCondicionIvaAcomboBox();
        traerTipoPersonaAcomboBox();
        fijarTamanoMaximo(clienteDni,8);
        fijarTamanoMaximo(clienteCuit,11);
        fijarTamanoMaximo(clienteTelefono,11);
        //llenarClientes();
    }

    private void cerrarVentana(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }


    public void accederVentas(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Ventas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ventas");
        stage.show();
        cerrarVentana(e);
    }

    public void accederFacturacion(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Facturas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Facturas");
        stage.show();
        cerrarVentana(e);
    }

    public void accederStock(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Stock.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Stock");
        stage.show();
        cerrarVentana(e);
    }

    public void accederCosteo(ActionEvent e) throws IOException{
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/MetodoCosteo.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Metodo de Costeo");
        stage.show();
        cerrarVentana(e);
    }

    public void accederACuentaCorriente(ActionEvent e) throws IOException {
        if(tablaCliente.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("Error!");
            alert.setContentText("Por favor, seleccione un cliente");
            alert.showAndWait();
        }else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Vista/CuentaCorrienteCliente.fxml"));
            Parent asiento = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(asiento);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Cuenta corriente");
            CuentaCorrienteController cuentaCorrienteController= loader.getController();
            cuentaCorrienteController.recibeCuit(clientesController,traerCuit(),retornarNombreYapCliente());
            stage.show();
        }
    }

    public String traerCuit(){
        return tablaCliente.getSelectionModel().getSelectedItem().getCuit();

    }

    public String retornarNombreYapCliente(){
        TipoPersona tp;
        tp=verificarTipoPersona();
            if (tp.getId_tipopersona() == 1) {
                return tablaCliente.getSelectionModel().getSelectedItem().getRazonSocial().toUpperCase();
            } else {
                return tablaCliente.getSelectionModel().getSelectedItem().getNombre().toUpperCase() + " " + tablaCliente.getSelectionModel().getSelectedItem().getApellido().toUpperCase();
            }

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
        textoIdCliente.setText("");
        tablaCliente.getItems().clear();
        seleccionTipoPersona.getSelectionModel().clearSelection();
        condicionIva.getSelectionModel().clearSelection();
        setearPrompTextDeVuelta();


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
        BuscarCliente.setText("");
        textoIdCliente.setText("");
        condicionIva.getSelectionModel().clearSelection();
        setearPrompTextDeVuelta();
    }


    public void siEsJuridica(){
        //textoIdCliente.setText("");
        BuscarCliente.setText("");
        clienteTelefono.setDisable(false);
        clienteDireccion.setDisable(false);
        clienteEmail.setDisable(false);
        clienteRazon.setDisable(false);
        clienteCuit.setDisable(false);
        condicionIva.setDisable(false);
    }


    public void siEsFisica(){
        //textoIdCliente.setText("");
        BuscarCliente.setText("");
        clienteNombre.setDisable(false);
        clienteTelefono.setDisable(false);
        clienteDireccion.setDisable(false);
        clienteEmail.setDisable(false);
        clienteDni.setDisable(false);
        clienteTelefono.setDisable(false);
        clienteApellido.setDisable(false);
        clienteCuit.setDisable(false);
        condicionIva.setDisable(false);
    }

    public void traerTipoPersonaAcomboBox(){
        ObservableList<Object> list = FXCollections.observableArrayList();
        try {
            //Connection conn = ConexionBD.getConnection();
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
    public static void fijarTamanoMaximo(final TextField campoTexto, final int tamanoMaximo) {

        campoTexto.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable,
                                Number valorAnterior, Number valorActual) {
                if (valorActual.intValue() > valorAnterior.intValue()) {
                    if (campoTexto.getText().length() >= tamanoMaximo) {
                        campoTexto.setText(campoTexto.getText().substring(0, tamanoMaximo));
                    }
                }
            }
        });

    }



    public void traerCondicionIvaAcomboBox(){

        ObservableList<Object> list = FXCollections.observableArrayList();
        try {
           // Connection conn = ConexionBD.getConnection();
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

    public void guardarPersonaJuridica(){
        //Connection conn = ConexionBD.getConnection();

        if (!clienteRazon.getText().isBlank() &&
            !clienteDireccion.getText().isBlank() && !clienteTelefono.getText().isBlank()
            && !clienteCuit.getText().isBlank() && !clienteEmail.getText().isBlank() &&
                !condicionIva.getSelectionModel().isEmpty() && !seleccionTipoPersona.getSelectionModel().isEmpty()) {
            if(verificarEmail(clienteEmail.getText())) {
                try {
                    String sql = "INSERT INTO CLIENTE(razonsocial,direccion,cuit,telefono,email,id_condicioniva,idtipopersona) VALUES (?,?,?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, clienteRazon.getText().toLowerCase());
                    ps.setString(2, clienteDireccion.getText());
                    ps.setString(3, clienteCuit.getText());
                    ps.setString(4, clienteTelefono.getText());
                    ps.setString(5, clienteEmail.getText());
                    ps.setInt(6, verificarCondicionIva().getIdcondicioniva());
                    ps.setInt(7, verificarTipoPersona().getId_tipopersona());
                    //ps.setString(9,"0");
                    ps.execute();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("Operacion exitosa!");
                    alert.setContentText("Se ha agregado con exito el cliente");
                    alert.showAndWait();
                    limpiarDatos1();
                    deshabilitarCampos();

                } catch (SQLException throwables) {
                   throwables.printStackTrace();
                }

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Ingrese un email valido");
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



    public void guardarPersonaFisica(){
        //Connection conn = ConexionBD.getConnection();

        if (!clienteDni.getText().isBlank() && !clienteNombre.getText().isBlank() &&
                !clienteDireccion.getText().isBlank()&& !clienteTelefono.getText().isBlank()
                && !clienteCuit.getText().isBlank() && !clienteEmail.getText().isBlank() && !clienteApellido.getText().isBlank() && !condicionIva.getSelectionModel().isEmpty() && !seleccionTipoPersona.getSelectionModel().isEmpty()) {
            if(verificarEmail(clienteEmail.getText())) {
                try {
                    String sql = "INSERT INTO CLIENTE(nombre,direccion,cuit,telefono,id_condicioniva,email,idtipopersona,dni,apellido) VALUES (?,?,?,?,?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, clienteNombre.getText().toLowerCase());
                    ps.setString(2, clienteDireccion.getText());
                    ps.setString(3, clienteCuit.getText());
                    ps.setString(4, clienteTelefono.getText());
                    ps.setInt(5, verificarCondicionIva().getIdcondicioniva());
                    ps.setString(6, clienteEmail.getText());
                    ps.setInt(7, verificarTipoPersona().getId_tipopersona());
                    ps.setString(8, clienteDni.getText());
                    ps.setString(9, clienteApellido.getText());
                    ps.execute();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("Operacion exitosa!");
                    alert.setContentText("Se ha agregado con exito el cliente");
                    alert.showAndWait();
                    limpiarDatos1();

                    deshabilitarCampos();


                } catch (SQLException throwables) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Atencion");
                    alert.setHeaderText("Error!");
                    alert.setContentText("Este cliente ya se encuentra registrado en el sistema");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion!");
                alert.setHeaderText("Por favor,");
                alert.setContentText("Ingrese un email valido");
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







    public TipoPersona verificarTipoPersona(){
        TipoPersona t = new TipoPersona();
        try {
            String s = seleccionTipoPersona.getSelectionModel().getSelectedItem().toString();
            //Connection conn = ConexionBD.getConnection();
            Statement stat = conn.createStatement();
            String SQL = "SELECT * FROM tipo_persona WHERE tipopersona=" + "'"+ s +"'";
            ResultSet rs = stat.executeQuery(SQL);

            if (rs.next()) {
               t.setId_tipopersona(rs.getInt("idtipo_persona"));
               t.setTipopersona(rs.getString("tipopersona"));
            }

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Complete todos los campos");
            alert.showAndWait();
        }
        return t;

    }

    public boolean verificarEmail(String email){
        if (esValid(email)){
            return true;
        }else{
            return false;
        }
    }


    public CondicionIva verificarCondicionIva(){
        String s1 = condicionIva.getSelectionModel().getSelectedItem().toString();
        CondicionIva c = new CondicionIva();
        try {
            //Connection conn = ConexionBD.getConnection();
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

    public void typedEnLetras(){
        clienteApellido.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                clienteApellido.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        clienteNombre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                clienteNombre.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
        clienteRazon.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                clienteRazon.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
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
        clienteCuit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (newValue == null) {
                    clienteCuit.setText(null);
                } else {
                    if (!newValue.matches("\\d*")) {
                        clienteCuit.setText(newValue.replaceAll("[^\\d]", ""));
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
        condicionIva.setDisable(true);
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




    public ObservableList<Cliente> traerClienteAtabla(String s){
        //Connection conn = ConexionBD.getConnection();
        ObservableList<Cliente> lista = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT c.idcliente as idcliente, c.razonsocial as razonsocial, c.dni as dni, c.direccion as direccion, c.nombre as nombre, c.apellido as apellido, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva FROM cliente c WHERE c.dni="+ "'" + s + "'"+" OR c.razonsocial="+ "'" + s.toLowerCase() +"'"+" OR c.nombre="+"'" + s.toLowerCase() +"'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while(rs.next()){
                    Cliente c = new Cliente();
                    c.setIdcliente(rs.getInt("idcliente"));
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
                    c.setNombreDeLaCondicionIVA(buscarCondicionIva(rs.getInt("idcondicioniva")).getNombre());
                    setearMayusculaParaTabla(c);
                    lista.add(c);

            }


        } catch (SQLException throwables) {

        }

        return lista;
    }


    public void setearMayusculaParaTabla(Cliente c){
        if(c.getTipoPersona().getTipopersona().equals("Juridica")){
            String output = c.getRazonSocial().substring(0, 1).toUpperCase() + c.getRazonSocial().substring(1);
            c.setRazonSocial(output);
        }else{
            String output = c.getNombre().substring(0, 1).toUpperCase() + c.getNombre().substring(1);
            c.setNombre(output);
            String output2 = c.getApellido().substring(0, 1).toUpperCase() + c.getApellido().substring(1);
            c.setApellido(output2);
        }
    }







    public Cliente retornarCliente(String s){
        //Connection conn = ConexionBD.getConnection();
        Cliente c = new Cliente();
        try {
            String SQL = "SELECT c.idcliente as idcliente,c.razonsocial as razonsocial,c.estado as estado, c.dni as dni, c.direccion as direccion, c.nombre as nombre, c.apellido as apellido, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva FROM cliente c WHERE c.dni="+ "'" + s + "'"+" OR c.razonsocial="+ "'" + s.toLowerCase() +"'" +" OR c.nombre="+"'" + s.toLowerCase() +"'"+" OR c.cuit="+"'" + s +"'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while(rs.next()){
                if(rs.getBoolean("estado")) {
                    c.setIdcliente(rs.getInt("idcliente"));
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
            }


        } catch (SQLException throwables) {

        }
        return c;
    }

    public TipoPersona buscarTipoPersona(int i) throws SQLException {
        //Connection conn = ConexionBD.getConnection();
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


    public CondicionIva buscarCondicionIva(int i) throws SQLException {
        //Connection conn = ConexionBD.getConnection();
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



    public void agregarClienteSeleccionadoAcampos(){
        limpiarDatos2();
        if(!tablaCliente.getSelectionModel().isEmpty()){
            if (tablaCliente.getSelectionModel().getSelectedItem().getTipoPersona().getTipopersona().equals("Juridica")) {
                siEsJuridica();
                traerDatosDePersonaJuridica(tablaCliente.getSelectionModel().getSelectedItem().getRazonSocial());
            }else{
                siEsFisica();
                traerDatosDePersonaFisica(tablaCliente.getSelectionModel().getSelectedItem().getDni());
        }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Seleccione un cliente");
            alert.showAndWait();
        }
    }


    public void traerDatosDePersonaFisica(String dni){
        //Connection conn = ConexionBD.getConnection();
        try {
            String SQL = "SELECT c.idcliente, c.direccion as direccion, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva,c.dni,c.apellido,c.dni,c.nombre FROM cliente c WHERE c.dni="+ "'" + dni.trim() + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            if(rs.next()){
                textoIdCliente.setText(rs.getString("idcliente"));
                clienteEmail.setText(rs.getString("email"));
                clienteTelefono.setText(rs.getString("telefono"));
                clienteDireccion.setText(rs.getString("direccion"));
                clienteCuit.setText(rs.getString("cuit"));
                clienteDni.setText(rs.getString("dni"));
                String output1 = rs.getString("apellido").substring(0, 1).toUpperCase() + rs.getString("apellido").substring(1);
                clienteApellido.setText(output1);
                String output2 = rs.getString("nombre").substring(0, 1).toUpperCase() + rs.getString("nombre").substring(1);
                clienteNombre.setText(output2);
                seleccionTipoPersona.setValue(buscarTipoPersona(rs.getInt("idtipopersona")).getTipopersona());
                condicionIva.setValue(buscarCondicionIva(rs.getInt("idcondicioniva")).getNombre());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }





    }

    public void traerDatosDePersonaJuridica(String razon){
        //Connection conn = ConexionBD.getConnection();

        try {
            String SQL = "SELECT c.idcliente,c.razonsocial as razonsocial, c.direccion as direccion, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva FROM cliente c WHERE c.razonsocial="+ "'" + razon.toLowerCase() + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            if(rs.next()){

                textoIdCliente.setText(rs.getString("idcliente"));
                clienteEmail.setText(rs.getString("email"));
                clienteTelefono.setText(rs.getString("telefono"));
                clienteDireccion.setText(rs.getString("direccion"));
                clienteCuit.setText(rs.getString("cuit"));
                String output = rs.getString("razonsocial").substring(0, 1).toUpperCase() + rs.getString("razonsocial").substring(1);
                clienteRazon.setText(output);
                seleccionTipoPersona.setValue(buscarTipoPersona(rs.getInt("idtipopersona")).getTipopersona());
                condicionIva.setValue(buscarCondicionIva(rs.getInt("idcondicioniva")).getNombre());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void actualizarClienteFisico(int id){

        //Connection conn = ConexionBD.getConnection();

        if (!clienteDni.getText().isBlank() && !clienteNombre.getText().isBlank() &&
                !clienteDireccion.getText().isBlank()&& !clienteTelefono.getText().isBlank()
                && !clienteCuit.getText().isBlank() && !clienteEmail.getText().isBlank() && !clienteApellido.getText().isBlank() && !condicionIva.getSelectionModel().isEmpty() && !seleccionTipoPersona.getSelectionModel().isEmpty()) {

            try {
                String sql = "UPDATE cliente SET nombre=?,direccion=?,cuit=?,telefono=?,id_condicioniva=?,email=?,idtipopersona=?,dni=?,apellido=? WHERE idcliente=? ";
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
                ps.setInt(10,id);
                ps.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operacion exitosa!");
                alert.setContentText("Se han actualizado los datos del cliente");
                alert.showAndWait();
                limpiarDatos1();

                deshabilitarCampos();


            }
            catch (SQLException throwables) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Error!");
                alert.setContentText("Este cliente ya se encuentra registrado en el sistema");
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


    public void actualizarClienteJuridico(int id){
        //Connection conn = ConexionBD.getConnection();

        if (!clienteRazon.getText().isBlank() &&
                !clienteDireccion.getText().isBlank() && !clienteTelefono.getText().isBlank()
                && !clienteCuit.getText().isBlank() && !clienteEmail.getText().isBlank() &&
                !condicionIva.getSelectionModel().isEmpty() && !seleccionTipoPersona.getSelectionModel().isEmpty()) {

            try {
                String sql = "UPDATE cliente SET razonsocial=?,direccion=?,cuit=?,telefono=?,id_condicioniva=?,email=?,idtipopersona=? WHERE idcliente=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,clienteRazon.getText().toLowerCase());
                //ps.setString(2,"0");
                ps.setString(2,clienteDireccion.getText());
                ps.setString(3,clienteCuit.getText());
                ps.setString(4,clienteTelefono.getText());
                ps.setInt(5,verificarCondicionIva().getIdcondicioniva());
                ps.setString(6,clienteEmail.getText());
                ps.setInt(7,verificarTipoPersona().getId_tipopersona());
                ps.setInt(8,id);
                //ps.setString(9,"0");
                ps.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operacion exitosa!");
                alert.setContentText("Se han actualizado los datos del cliente");
                alert.showAndWait();
                limpiarDatos1();
                deshabilitarCampos();

            }
            catch (SQLException throwables) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Error!");
                alert.setContentText("Este cliente ya se encuentra registrado en el sistema");
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

    public void elimincliente(String cuit) {
        //Connection conn = ConexionBD.getConnection();
        String nombreoRazon=nombreOrazonSocial(cuit).toUpperCase();
        ButtonType Confirmar = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        ButtonType Cancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"",Confirmar,Cancelar);
        alert2.setTitle("Atencion");
        alert2.setContentText("Está seguro que desea dar de baja al cliente "+nombreoRazon+"?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get()==Confirmar) {
            try {
                String sql = "UPDATE cliente SET estado=false WHERE cuit=" + "'" + cuit + "'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operacion exitosa!");
                alert.setContentText("Se ha dado de baja al cliente "+nombreoRazon);
                alert.showAndWait();
                limpiarDatos1();
                deshabilitarCampos();

            } catch (SQLException throwables) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Error!");
                alert.setContentText("No se pudo dar de baja a el cliente...");
                alert.showAndWait();
            }

        }


    }


    public String nombreOrazonSocial(String s){
        if(retornarCliente(s).getTipoPersona().getId_tipopersona()==1){
           return retornarCliente(s).getRazonSocial();
        }else{
            return retornarCliente(s).getNombre()+" "+retornarCliente(s).getApellido();
        }
    }

    public String nombreOrazonSocialParaClientesDadosDeBaja(String s){
        if(retornarClienteDadosDeBaja(s).getTipoPersona().getId_tipopersona()==1){
            return retornarClienteDadosDeBaja(s).getRazonSocial();
        }else{
            return retornarClienteDadosDeBaja(s).getNombre()+" "+retornarClienteDadosDeBaja(s).getApellido();
        }
    }


    public void actualizarDatosCliente(String tipoPersona,int id){

        if(tipoPersona.equals("Juridica")){
            actualizarClienteJuridico(id);
            tablaCliente.getItems().clear();
        }else{
            actualizarClienteFisico(id);
            tablaCliente.getItems().clear();
        }

    }


    public void darDeAltaCliente(Cliente c,String s){
        String nombreoRazon=nombreOrazonSocialParaClientesDadosDeBaja(s).toUpperCase();
        ButtonType Confirmar = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        ButtonType Cancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"",Confirmar,Cancelar);
        alert2.setTitle("Atencion");
        alert2.setContentText("Está seguro que desea dar de alta al cliente "+nombreoRazon+"?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get()==Confirmar) {
            try {
                String sql = "UPDATE cliente SET estado=true WHERE idcliente=" + "'" + c.getIdcliente() + "'";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operacion exitosa!");
                alert.setContentText("Se ha dado de alta al cliente "+nombreoRazon);
                alert.showAndWait();
                limpiarDatos1();
                deshabilitarCampos();
                textoHabilitar.setText("");

            } catch (SQLException throwables) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Error!");
                alert.setContentText("No se pudo dar de alta al cliente...");
                alert.showAndWait();
            }

        }

}
    public boolean retornarEstadoCliente(String s){
        boolean estado = false;
        try {
            String SQL = "SELECT c.estado FROM cliente c WHERE c.dni=" + "'" + s + "'" + " OR c.razonsocial=" + "'" + s.toLowerCase() +  "'" + " OR c.cuit=" + "'" + s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            if(rs.next()){
                estado=rs.getBoolean("estado");
            }
        }catch (Exception e){

        }
        return estado;
    }

    public Cliente retornarClienteDadosDeBaja(String s){
        //Connection conn = ConexionBD.getConnection();
        Cliente c = new Cliente();
        try {
            String SQL = "SELECT c.idcliente as idcliente,c.razonsocial as razonsocial,c.estado as estado, c.dni as dni, c.direccion as direccion, c.nombre as nombre, c.apellido as apellido, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva FROM cliente c WHERE c.dni="+ "'" + s + "'"+" OR c.razonsocial="+ "'" + s.toLowerCase()  +"'"+" OR c.cuit="+"'" + s +"'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while(rs.next()){
                if(!rs.getBoolean("estado")) {
                    c.setIdcliente(rs.getInt("idcliente"));
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
            }


        } catch (SQLException throwables) {

        }
        return c;
    }


    public void altaCliente(String s){
        if(retornarClienteDadosDeBaja(s).getIdcliente()==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("Error!");
            alert.setContentText("No se ha encontrado el cliente especificado, por favor, realize una nueva busqueda");
            alert.showAndWait();
        }else{
            darDeAltaCliente(retornarClienteDadosDeBaja(textoHabilitar.getText()), textoHabilitar.getText());
        }
    }

    public void habilitaCliente(ActionEvent e){

        if(!textoHabilitar.getText().isEmpty()) {
            if(!retornarEstadoCliente(textoHabilitar.getText())) {
                altaCliente(textoHabilitar.getText());
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Error!");
                alert.setContentText("Este cliente ya se encuentra habilitado en el sistema");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setHeaderText("Error!");
            alert.setContentText("Por favor, ingrese al cliente que desea habilitar");
            alert.showAndWait();
        }
    }




    public void traerParaActualizarDatos(ActionEvent event){
        agregarClienteSeleccionadoAcampos();//SOLUCIONADO
        BuscarCliente.setText("");
    }

    public void limpiarDatos(ActionEvent event){
        limpiarDatos1();
        BuscarCliente.setText("");
        deshabilitarCampos();
    }

    public void seleccionarTipoPersona(ActionEvent event){
        try {
            String s = seleccionTipoPersona.getSelectionModel().getSelectedItem().toString();
            if(s.equals("Juridica")) {
                if(!clienteDni.getText().isBlank() || !clienteApellido.getText().isBlank() || !clienteNombre.getText().isBlank()) {
                    limpiarDatos2();
                }//SOLUCIONADO
                deshabilitarCampos();
                siEsJuridica();
                tablaCliente.getItems().clear();


            }else{
                if(s.equals("Fisica")){
                    if(!clienteRazon.getText().isBlank()) {
                        limpiarDatos2();
                    }
                    deshabilitarCampos();
                    siEsFisica();
                    tablaCliente.getItems().clear();
                }

            }
        }catch (Exception e){

        }


    }


    public void bajaCliente(ActionEvent e){
        if(!tablaCliente.getSelectionModel().isEmpty()){
            elimincliente(tablaCliente.getSelectionModel().getSelectedItem().getCuit());
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Seleccione un cliente a dar de baja");
            alert.showAndWait();
        }
    }

    public void guardarCliente(ActionEvent event){

        TipoPersona t = verificarTipoPersona();
        try {
            if(textoIdCliente.getText().isBlank()){
                if (t.getTipopersona().equals("Juridica")) {
                    guardarPersonaJuridica();
                } else {
                    guardarPersonaFisica();
                }
            }else{
                actualizarDatosCliente(t.getTipopersona(), Integer.parseInt(textoIdCliente.getText()));
            }


        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No ha seleccionado el tipo de persona");
            alert.showAndWait();
        }
    }


    public void busquedaCliente(ActionEvent event){
        Cliente c;
        c=retornarCliente(BuscarCliente.getText());
        if(c.getTipoPersona()!=null){
            mostrarClienteEnTabla(BuscarCliente.getText(),c);
            BuscarCliente.setText("");

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Error,");
            alert.setContentText("No se ha encontrado el cliente, por favor, realize una nueva busqueda");
            alert.showAndWait();
        }
    }

    public void mostrarTodosLosClientesPorTipo(ActionEvent e) throws SQLException {

        if(!seleccionTipoPersona.getSelectionModel().isEmpty()) {
            mostrarClientes((String) seleccionTipoPersona.getSelectionModel().getSelectedItem());

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setContentText("Por favor, elija el tipo de persona");
            alert.showAndWait();
        }
    }



    public void mostrarClientes(String s) {
        Cliente c;
        c = retornarClienteJuridicoOfisico(s);
        if(c.getCuit()!=null) {
            mostrarClientesEnTabla(c);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setContentText("No se han encontrado clientes: "+ s);
            alert.showAndWait();
        }
    }
    public Cliente retornarClienteJuridicoOfisico(String s){
        Cliente c = new Cliente();
        try {
            String SQL = "SELECT c.idcliente as idcliente,c.razonsocial as razonsocial,c.estado as estado, c.dni as dni, c.direccion as direccion, c.nombre as nombre, c.apellido as apellido, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva " +
                    "FROM cliente c INNER JOIN tipo_persona tp ON c.idtipopersona=tp.idtipo_persona WHERE tp.tipopersona="+ "'" + s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()){
                if(rs.getBoolean("estado")) {
                    c.setIdcliente(rs.getInt("idcliente"));
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
            }


        } catch (SQLException throwables) {

        }
        return c;
    }

    public ObservableList<Cliente> traerClientesAtabla(int i)  {
       // Connection conn = ConexionBD.getConnection();
        ObservableList<Cliente> lista = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT c.idcliente as idcliente, c.razonsocial as razonsocial,c.estado as estado, c.dni as dni, c.direccion as direccion, c.nombre as nombre, c.apellido as apellido, c.cuit as cuit, c.telefono as telefono, c.email as email,c.idtipopersona as idtipopersona,c.id_condicioniva as idcondicioniva" +
                    " FROM cliente c WHERE c.idtipopersona="+ i +"";

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while(rs.next()){
                if(rs.getBoolean("estado")) {
                    Cliente c = new Cliente();
                    c.setIdcliente(rs.getInt("idcliente"));
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
                    c.setNombreDeLaCondicionIVA(buscarCondicionIva(rs.getInt("idcondicioniva")).getNombre());
                    setearMayusculaParaTabla(c);
                    lista.add(c);
                }

            }


        } catch (SQLException throwables) {

        }

        return lista;
    }


    public void mostrarDatos(){
        habilitarCliente.setVisible(u.getIdperfil()==1);
        textoHabilitar.setVisible(u.getIdperfil()==1);
        botonBaja.setVisible(u.getIdperfil()==1);


    }



    public void mostrarClienteEnTabla(String s,Cliente c){
        if(c.getTipoPersona().getTipopersona().equals("Juridica")){
            columnaCondicionIva.setMinWidth(430);
            columnaApellido.setVisible(false);
            columnaNombre.setVisible(false);
            columnaRazon.setVisible(true);
            columnaRazon.setCellValueFactory(new PropertyValueFactory<Cliente,String>("razonSocial"));
            columnaDni.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cuit"));
            columnaDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
            columnaEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
            columnaCondicionIva.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombreDeLaCondicionIVA"));
            tablaCliente.setItems(traerClienteAtabla(s));
        }else {

            columnaCondicionIva.setMinWidth(340);
            columnaRazon.setVisible(false);
            columnaApellido.setVisible(true);
            columnaNombre.setVisible(true);
            columnaDni.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dni"));
            columnaApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
            columnaDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
            columnaEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
            columnaCondicionIva.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombreDeLaCondicionIVA"));
            tablaCliente.setItems(traerClienteAtabla(s));
        }

    }

    public void mostrarClientesEnTabla(Cliente c)  {
        if(c.getTipoPersona().getId_tipopersona()==1){
            columnaCondicionIva.setMinWidth(430);
            columnaApellido.setVisible(false);
            columnaNombre.setVisible(false);
            columnaRazon.setVisible(true);
            columnaRazon.setCellValueFactory(new PropertyValueFactory<Cliente,String>("razonSocial"));
            columnaDni.setCellValueFactory(new PropertyValueFactory<Cliente,String>("cuit"));
            columnaDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
            columnaEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
            columnaCondicionIva.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombreDeLaCondicionIVA"));
            tablaCliente.setItems(traerClientesAtabla(c.getTipoPersona().getId_tipopersona()));
        }else {

            columnaCondicionIva.setMinWidth(340);
            columnaRazon.setVisible(false);
            columnaApellido.setVisible(true);
            columnaNombre.setVisible(true);
            columnaDni.setCellValueFactory(new PropertyValueFactory<Cliente,String>("dni"));
            columnaApellido.setCellValueFactory(new PropertyValueFactory<Cliente,String>("apellido"));
            columnaNombre.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombre"));
            columnaDireccion.setCellValueFactory(new PropertyValueFactory<Cliente,String>("direccion"));
            columnaEmail.setCellValueFactory(new PropertyValueFactory<Cliente,String>("email"));
            columnaTelefono.setCellValueFactory(new PropertyValueFactory<Cliente,String>("telefono"));
            columnaCondicionIva.setCellValueFactory(new PropertyValueFactory<Cliente,String>("nombreDeLaCondicionIVA"));
            tablaCliente.setItems(traerClientesAtabla(c.getTipoPersona().getId_tipopersona()));
        }

    }
}
