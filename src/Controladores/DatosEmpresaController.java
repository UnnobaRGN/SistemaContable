package Controladores;

import Modelo.UsuarioLogeado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DatosEmpresaController implements Initializable {

    @FXML
    private ImageView imagenFondo;

    @FXML
    private TextField textRazon;

    @FXML
    private TextField textDireccion;

    @FXML
    private TextField textCodigo;

    @FXML
    private TextField textLocalidad;

    @FXML
    private TextField textProvincia;

    @FXML
    private ComboBox<String> comboBoxIva;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textTelefono;

    @FXML
    private TextField textCuit;

    @FXML
    private Button botonActualizar;

    @FXML
    private Button botonSalir;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingFile = new File("Imagenes/2.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        imagenFondo.setImage(brandingImage);

        botonActualizar.setVisible(u.getIdperfil() == 1);

        llenarIva();

    }

    public void llenarIva(){

        ObservableList<String> list = FXCollections.observableArrayList("Responsable Inscripto","Monotributista");
        comboBoxIva.setItems(list);

    }

    public void actualizarDatos(ActionEvent e){

        textCodigo.setText(textCodigo.getText());
        textCuit.setText(textCuit.getText());
        textDireccion.setText(textDireccion.getText());
        textEmail.setText(textEmail.getText());
        textLocalidad.setText(textLocalidad.getText());
        textProvincia.setText(textProvincia.getText());
        textRazon.setText(textRazon.getText());
        textTelefono.setText(textTelefono.getText());

    }

    public void salirDatos(ActionEvent e){

        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();

    }

}
