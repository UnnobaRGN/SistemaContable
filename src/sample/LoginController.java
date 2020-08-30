package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
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

    private double xOffSet = 0;
    private double yOffSet = 0;


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

    private void makeStageDragable() {
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

    public void loginButtonAction(ActionEvent event) {
        if (CampoUsuario.getText().isBlank() == false && CampoContraseña.getText().isBlank() == false) {
            loginMessageLabel.setText("");
            validateLogin();
        } else {
            loginMessageLabel.setText("Por favor, ingrese usuario y contraseña");
        }

    }

    public void validateLogin() {



    }
}