package Controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class AgregarCuentaController implements Initializable {

    @FXML
    private ImageView ImagenDeAgregarCuenta;

    @FXML
    private TextField NombreCuenta;

    @FXML
    private TextField CodigoCuenta;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Button AgregarCuenta;

    @FXML
    private RadioButton BotonSi;


    @FXML
    public ToggleGroup toggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Imagenes/2.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenDeAgregarCuenta.setImage(brandingImage);
        ObservableList<String> list = FXCollections.observableArrayList("Ac","Pa","Pm","R+","R-");
        comboBox.setItems(list);

    }


    public void AgregarCuentaBoton(ActionEvent actionEvent){
        Connection conn = ConexionBD.getConnection();
        if(!NombreCuenta.getText().isBlank() && !CodigoCuenta.getText().isBlank() && !comboBox.getSelectionModel().isEmpty()) {
            try {

                String sql = "INSERT INTO CUENTA(cuenta,codigo_cuenta,recibe_saldo,saldo_actual,idtipo) VALUES (?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, NombreCuenta.getText());
                ps.setInt(2, Integer.parseInt(CodigoCuenta.getText()));
                ps.setInt(3, recibeSaldoSioNo(BotonSi));
                ps.setInt(4, 0);
                String s = comboBox.getSelectionModel().getSelectedItem().toString();
                ps.setInt(5, verificarTipoCuenta(s));

                ps.execute();

            } catch (Exception e) {

            }
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Complete todos los campos");
            alert.showAndWait();
        }
    }




    public int recibeSaldoSioNo(RadioButton BotonSi){

        if(BotonSi.isSelected()){
            return 1;
        }else{
            return 0;
        }



    }



   public int verificarTipoCuenta(String idtipo){
        int aux = 0;
        if(idtipo.equals("Ac")){
            aux = 1;
        }
        if(idtipo.equals("Pa")) {
            aux = 2;
        }
        if(idtipo.equals("Pm")){
            aux = 3;
        }
        if(idtipo.equals("R+")){
            aux = 4;
        }
        if(idtipo.equals("R-")){
            aux = 5;
        }
        return aux;
   }



}
