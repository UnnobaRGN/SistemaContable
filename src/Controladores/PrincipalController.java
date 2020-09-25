package Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Modelo.UsuarioLogeado;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
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
    private MenuItem AgregarCuenta = new MenuItem();

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


        AgregarCuenta.setVisible(u.getIdperfil() == 1);
    }



    public void aCasa(ActionEvent event) {

        System.out.println("hola");

    }




}