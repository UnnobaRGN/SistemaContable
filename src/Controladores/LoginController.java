package Controladores;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import sample.ConexionBD;
import sample.Main;
import Modelo.UsuarioLogeado;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Label loginMessageLabel;

    @FXML
    private ImageView ImagenPrincipal;

    @FXML
    private ImageView ImagenCerrar;

    @FXML
    private Button BotonCerrar;

    @FXML
    private TextField CampoUsuario;

    @FXML
    private PasswordField CampoContraseña;

    @FXML
    private AnchorPane parent;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();

    private static double xOffSet = 0;
    private static double yOffSet = 0;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("Imagenes/Captura.PNG");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenPrincipal.setImage(brandingImage);

        File brandingFile2 = new File("Imagenes/faa3172dd52035d0c227d7ecab4d6024-doodle-cruzado-x-by-vexels.png");
        Image brandingImage2 = new Image(brandingFile2.toURI().toString());
        ImagenCerrar.setImage(brandingImage2);
        makeStageDragable();

    }

    public void makeStageDragable() {
        parent.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        parent.setOnMouseDragged((event) -> {
            Main.primaryStage.setX(event.getScreenX() - xOffSet);
            Main.primaryStage.setY(event.getScreenY() - yOffSet);
            Main.primaryStage.setOpacity(0.8f);
        });
        parent.setOnDragDone((event) -> {
            Main.primaryStage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((event) -> {
            Main.primaryStage.setOpacity(1.0f);
        });
    }



    public void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) BotonCerrar.getScene().getWindow();
        stage.close();
    }

    public void loginButtonAction(ActionEvent event) throws IOException {
        if (!CampoUsuario.getText().isBlank() && !CampoContraseña.getText().isBlank()) {
            loginMessageLabel.setText("");
            if(validarLogin()){

                Parent part = FXMLLoader.load(getClass().getResource("/Vista/MenuPrincipal.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(part);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Urano's Contability");
                stage.show();


                ((Node)event.getSource()).getScene().getWindow().hide();
            }
        } else {
            loginMessageLabel.setText("Por favor, ingrese usuario y contraseña");

        }

    }

    public boolean validarLogin() {

        ConexionBD conectarAhora = new ConexionBD();
        Connection conectarBD = conectarAhora.getConnection();


        String VerificarLogin = "SELECT * FROM usuario WHERE usuario ='" +  CampoUsuario.getText() + "'AND contrasenia ='" + CampoContraseña.getText() + "'";


        try{
            Statement statement = conectarBD.createStatement();
            ResultSet  ResultadoDeQuery =  statement.executeQuery(VerificarLogin);


            if(ResultadoDeQuery.next()) {
                u.setId(ResultadoDeQuery.getInt("idusuario"));
                u.setUsuario(ResultadoDeQuery.getString("usuario"));
                u.setIdperfil(ResultadoDeQuery.getInt("idperfil"));
                    return true;


            }else {
                loginMessageLabel.setText("usuario o contrasenia invalido");
            }


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


        return false;
    }
}