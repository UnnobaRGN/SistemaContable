package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class CrearUsuarioController implements Initializable {



    @FXML
    private TextField Nombre;

    @FXML
    private TextField PassWord;

    @FXML
    private Button agregar;

    @FXML
    private Button salir;

    @FXML
    private ImageView ImagenFondo = new ImageView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingFile = new File("Imagenes/2.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenFondo.setImage(brandingImage);

    }

    public void agregarUsuario(ActionEvent e){
        Connection conn = ConexionBD.getConnection();
        if(!Nombre.getText().isBlank() && !PassWord.getText().isBlank()) {
            try {

                String sql = "INSERT INTO usuario(usuario,contrasenia,idperfil) VALUES (?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1,Nombre.getText());
                ps.setString(2,PassWord.getText());
                ps.setInt(3, 2);
                ps.execute();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atencion");
                alert.setHeaderText("Operacion exitosa!");
                alert.setContentText("Se ha agregado el usuario satisfactoriamente");
                alert.showAndWait();
                ((Node) e.getSource()).getScene().getWindow().hide();

            }catch (Exception x){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Ha surgido un problema");
                alert.setContentText("Se han ingresado mal los datos, por favor, intente de nuevo");
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


        public void salir(ActionEvent event){
            Stage stage = (Stage) salir.getScene().getWindow();
            stage.close();

        }
}
