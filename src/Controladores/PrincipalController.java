package Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import Modelo.UsuarioLogeado;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class PrincipalController implements Initializable {

    @FXML
    private ImageView ImagenFondo = new ImageView();

    @FXML
    private ImageView Imagen1 = new ImageView();

    @FXML
    private ImageView Imagen2 = new ImageView();

    @FXML
    private ImageView Imagen3 = new ImageView();

    @FXML
    private ImageView Imagen4 = new ImageView();

    @FXML
    private Button Asiento;

    @FXML
    private Button ButtonLibroDiario;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("Imagenes/2.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenFondo.setImage(brandingImage);

        File brandingFile1 = new File("Imagenes/3.jpg");
        Image brandingImage1 = new Image(brandingFile1.toURI().toString());
        Imagen1.setImage(brandingImage1);

        File brandingFile2 = new File("Imagenes/3.jpg");
        Image brandingImage2 = new Image(brandingFile2.toURI().toString());
        Imagen2.setImage(brandingImage2);

        File brandingFile3 = new File("Imagenes/3.jpg");
        Image brandingImage3= new Image(brandingFile3.toURI().toString());
        Imagen3.setImage(brandingImage3);

        File brandingFile4 = new File("Imagenes/3.jpg");
        Image brandingImage4 = new Image(brandingFile4.toURI().toString());
        Imagen4.setImage(brandingImage4);


    }



    public void abrirAsientos(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Asiento.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public void planDeCuentasButtonAction(ActionEvent event) throws IOException{


        Parent padre = FXMLLoader.load(getClass().getResource("/Vista/PlanDeCuentas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(padre);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public void abrirLibroDiario(ActionEvent event) throws IOException{

        Parent padre = FXMLLoader.load(getClass().getResource("/Vista/LibroDiario.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(padre);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

}
