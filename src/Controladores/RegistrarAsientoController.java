package Controladores;

import Modelo.Asiento;
import Modelo.Cuenta_Asiento;
import Modelo.Cuentas;
import Modelo.UsuarioLogeado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

public class RegistrarAsientoController implements Initializable {

    @FXML
    private ImageView FondoRegistrarAsiento = new ImageView();


    @FXML
    private ComboBox CuentasSeleccion;

    @FXML
    private TextField fecha;

    @FXML
    private TextField numeroAsiento;

    @FXML
    private Button ButtonCancelar;

    @FXML
    private RadioButton BotonDebe;


    @FXML
    public ToggleGroup toggleGroup;

    @FXML
    private TableView<Cuenta_Asiento> CuentaAsientoTableView;

    @FXML
    private TableColumn<Cuentas,String> Cuenta;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> Debe;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> Haber;

    @FXML
    private TextArea Descripcion;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();

    ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileFondoRegistrarAsiento = new File("Imagenes/2.jpg");
        Image brandingFondoRegistrarAsiento = new Image(fileFondoRegistrarAsiento.toURI().toString());
        FondoRegistrarAsiento.setImage(brandingFondoRegistrarAsiento);
        traerCuentasAcomboBox();
        fechaActual();
        numAsiento();

    }

    public void fechaActual(){
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        fecha.setText(formateador.format(ahora));
        fecha.setDisable(true);
    }


    public void cancelarRegistrarAsiento(ActionEvent event) throws IOException {
        Stage stage = (Stage) ButtonCancelar.getScene().getWindow();
        stage.close();
    }


    public void traerCuentasAcomboBox(){
        ObservableList<String> list = FXCollections.observableArrayList();
        this.CuentasSeleccion.getItems().clear();

        try{
            Connection conn = ConexionBD.getConnection();
            Statement s = conn.createStatement();
            String SQL = "SELECT * FROM cuenta WHERE recibe_saldo = 1";
            ResultSet  rs =  s.executeQuery(SQL);

            while (rs.next()){
                list.add(rs.getString("cuenta"));
            }

            CuentasSeleccion.setItems(list);
        }catch(Exception e){

        }

    }

    public Cuentas retornarCuenta(){
        Cuentas cuenta = null;
        Connection conn = ConexionBD.getConnection();
        String s = CuentasSeleccion.getSelectionModel().getSelectedItem().toString();
        try{
            String SQL = "SELECT * FROM cuenta WHERE cuenta='" + s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while(rs.next()){

                cuenta = new Cuentas(rs.getInt("codigo_cuenta"),rs.getString("cuenta"),rs.getInt("recibe_saldo"),rs.getString("idtipo"),rs.getInt("idcuenta"),rs.getInt("saldo_actual"));


            }

        }catch(Exception e){

        }

        return cuenta;

    }

    public void numAsiento(){

        try{
            Connection conn = ConexionBD.getConnection();
            Statement s = conn.createStatement();
            String SQL = "SELECT COUNT(*) as asientonum FROM asiento";
            ResultSet  rs =  s.executeQuery(SQL);

            while (rs.next()){
                int p = rs.getInt("asientonum");
                numeroAsiento.setText(String.valueOf(p+1));
                numeroAsiento.setDisable(true);
            }

        }catch(Exception e){

        }



    }


    public void GuardarAsiento(ActionEvent actionEvent){


        Debe.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("debe"));
        Haber.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("haber"));


    }

    public ObservableList<Cuenta_Asiento> retornarLista(){

        ObservableList<Cuentas> list = FXCollections.observableArrayList();


    }

}
