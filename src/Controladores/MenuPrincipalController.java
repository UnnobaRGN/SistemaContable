package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {



        @FXML
        private ImageView imagenVenta = new ImageView();

        @FXML
        private ImageView imagenAdministracion = new ImageView();

        @FXML
        private Button botonVenta;

        @FXML
        private Button botonAdministracion;

        @FXML
        private AnchorPane anchor1 = new AnchorPane();

        @FXML
        private AnchorPane anchor2 = new AnchorPane();

        @FXML
        private ImageView imagenFondo = new ImageView();

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            File brandingFile = new File("../Imagenes/asd.png.jpg");
            Image brandingImage = new Image(brandingFile.toURI().toString());
            imagenFondo.setImage(brandingImage);

            File brandingVenta = new File("../Imagenes/MenuPrincipalVenta.jpeg");
            Image brandingVentas = new Image(brandingVenta.toURI().toString());
            imagenVenta.setImage(brandingVentas);

            File brandingAdm = new File("../Imagenes/MenuPrincipalAdm.jpg");
            Image brandingAdms = new Image(brandingAdm.toURI().toString());
            imagenAdministracion.setImage(brandingAdms);

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

        public void accederAdministracion(ActionEvent event) throws IOException {
            Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Principal.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(asiento);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Principal");
            stage.show();
        }


}
