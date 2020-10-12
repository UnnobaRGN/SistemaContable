package Controladores;

import Modelo.Cuentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class RegistrarAsientoController implements Initializable {

    @FXML
    private ImageView FondoRegistrarAsiento = new ImageView();

    @FXML
    private DatePicker FechaRegistrarAsiento = new DatePicker();

    @FXML
    private ComboBox CuentasSeleccion;

    @FXML
    private Button ButtonCancelar;


    ObservableList<String> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileFondoRegistrarAsiento = new File("Imagenes/2.jpg");
        Image brandingFondoRegistrarAsiento = new Image(fileFondoRegistrarAsiento.toURI().toString());
        FondoRegistrarAsiento.setImage(brandingFondoRegistrarAsiento);
        traerCuentasAcomboBox();
        ///////DatePicker FechaRegistrarAsiento = new DatePicker(LocalDate.now());
        ///////FechaRegistrarAsiento.setEditable(false);

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

    public void guardarAsiento(ActionEvent actionEvent){

        Connection conn = ConexionBD.getConnection();
        String s = CuentasSeleccion.getSelectionModel().getSelectedItem().toString();
        try{
            String SQL = "SELECT * FROM cuenta WHERE cuenta='" + s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while(rs.next()){

                Cuentas cuenta = new Cuentas(rs.getInt("codigo_cuenta"),rs.getString("cuenta"),rs.getInt("recibe_saldo"),rs.getString("idtipo"),rs.getInt("idcuenta"),rs.getInt("saldo_actual"));


            }

        }catch(Exception e){

        }



    }
}
