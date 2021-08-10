package Controladores;

import Modelo.UsuarioLogeado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private MenuBar menuBar;

    @FXML
    private Menu menuInterno;

    @FXML
    private TextField textoUsuario;

    @FXML
    private MenuItem menuUsuario;

    @FXML
    private MenuItem menuAcercaDe;

    @FXML
    private MenuItem datosEmpresa;

    @FXML
    private ImageView imagenFondo = new ImageView();

    private UsuarioLogeado u = UsuarioLogeado.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingFile = new File("Imagenes/asd.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        imagenFondo.setImage(brandingImage);

        File brandingVenta = new File("Imagenes/MenuPrincipalVenta.jpeg");
        Image brandingVentas = new Image(brandingVenta.toURI().toString());
        imagenVenta.setImage(brandingVentas);

        File brandingAdm = new File("Imagenes/MenuPrincipalAdm.jpg");
        Image brandingAdms = new Image(brandingAdm.toURI().toString());
        imagenAdministracion.setImage(brandingAdms);

        informarUsuario();
        menuUsuario.setVisible(u.getIdperfil()==1);

    }

    public void accederVentas(ActionEvent event) throws IOException {
        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/MenuVentas.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Menu Ventas");
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

    public void informarUsuario(){

        textoUsuario.setText(u.getUsuario() + ": " +retornarPerfil(u.getIdperfil()));
        textoUsuario.setDisable(true);

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

    public void crearUsuario(ActionEvent event) throws  IOException{
        Parent padre = FXMLLoader.load(getClass().getResource("/Vista/CrearUsuario.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(padre);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Crear Usuario");
        stage.show();
    }

    public void abrirDatosEmpresa(ActionEvent e) throws IOException{

        Parent padre = FXMLLoader.load(getClass().getResource("/Vista/DatosEmpresa.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(padre);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Datos de empresa");
        stage.show();

    }

    public void abrirAcercaDe(ActionEvent e) throws IOException {

        Parent asiento = FXMLLoader.load(getClass().getResource("/Vista/AcercaDe.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(asiento);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Acerca De");
        stage.show();

    }

}
