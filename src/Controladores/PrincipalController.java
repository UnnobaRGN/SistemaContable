package Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import Modelo.UsuarioLogeado;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private MenuBar MenuBar = new MenuBar();

    @FXML
    private Menu Ayuda = new Menu();

    @FXML
    private MenuItem MenuAcercaDe = new MenuItem();

    @FXML
    private TextField TextUsuario;

    @FXML
    private Button Asiento;

    @FXML
    private Button ButtonLibroDiario;

    @FXML
    private Button ButtonLibroMayor;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File("Imagenes/2.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenFondo.setImage(brandingImage);

        File brandingFile1 = new File("Imagenes/3.jpg");
        Image brandingImage1 = new Image(brandingFile1.toURI().toString());
        Imagen1.setImage(brandingImage1);

        File brandingFile2 = new File("Imagenes/ImagenPrincipal2.jpg");
        Image brandingImage2 = new Image(brandingFile2.toURI().toString());
        Imagen2.setImage(brandingImage2);

        File brandingFile3 = new File("Imagenes/ImagenPrincipal3.jpg");
        Image brandingImage3= new Image(brandingFile3.toURI().toString());
        Imagen3.setImage(brandingImage3);

        File brandingFile4 = new File("Imagenes/Imagen4.jpg");
        Image brandingImage4 = new Image(brandingFile4.toURI().toString());
        Imagen4.setImage(brandingImage4);

        informarUsuario();

    }

    public void abrirAcercaDe(ActionEvent e) throws IOException {

        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/AcercaDe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Urano's Contability");
        stage.show();


    }

    public void informarUsuario(){

        TextUsuario.setText(u.getUsuario() + ": " +retornarPerfil(u.getIdperfil()));
        TextUsuario.setDisable(true);

    }

    public String retornarPerfil(int p){

        String perfil = new String();
        if (p==1){
            perfil = "Admin";
        }
        if (p==2)
            perfil = "Contador";
        return perfil;
    }

    public void abrirAsientos(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/Asiento.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Urano's Contability");
        stage.show();
    }


    public void planDeCuentasButtonAction(ActionEvent event) throws IOException{


        Parent padre = FXMLLoader.load(getClass().getResource("/Vista/PlanDeCuentas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(padre);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Urano's Contability");
        stage.show();

    }

    public void abrirLibroDiario(ActionEvent event) throws IOException{

        Parent padre = FXMLLoader.load(getClass().getResource("/Vista/LibroDiario.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(padre);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Urano's Contability");
        stage.show();

    }

    public void abrirLibroMayor(ActionEvent event) throws IOException{

        Parent padre = FXMLLoader.load(getClass().getResource("/Vista/LibroMayor.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(padre);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Urano's Contability");
        stage.show();

    }

}
