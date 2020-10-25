package Controladores;

import Modelo.Empresa;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DatosEmpresaController implements Initializable {

    @FXML
    private ImageView ImagenFondo;

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
    private TextField textIva;

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

    private Empresa empresa = new Empresa();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingFile = new File("Imagenes/2.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenFondo.setImage(brandingImage);

        botonActualizar.setVisible(u.getIdperfil() == 1);

        mostrarDatos();
        noModifica();

    }

    public void noModifica(){
        if(u.getIdperfil() == 0){
        textTelefono.setDisable(true);
        textRazon.setDisable(true);
        textProvincia.setDisable(true);
        textLocalidad.setDisable(true);
        textEmail.setDisable(true);
        textDireccion.setDisable(true);
        textCodigo.setDisable(true);
        textCuit.setDisable(true);
        textIva.setDisable(true);}

    }

    public void actualizarDatos(ActionEvent e){
        
        empresa.setCodigoPostal(textCodigo.getText());
        empresa.setCUIT(textCuit.getText());
        empresa.setDireccion(textDireccion.getText());
        empresa.setEmail(textEmail.getText());
        empresa.setIva(textIva.getText());
        empresa.setLocalidad(textLocalidad.getText());
        empresa.setProvincia(textProvincia.getText());
        empresa.setRazonSocial(textRazon.getText());
        empresa.setTelefono(textTelefono.getText());

    }

    public void mostrarDatos(){

        textCuit.setText(empresa.getCUIT());
        textCodigo.setText(empresa.getCodigoPostal());
        textDireccion.setText(empresa.getDireccion());
        textEmail.setText(empresa.getEmail());
        textLocalidad.setText(empresa.getLocalidad());
        textProvincia.setText(empresa.getProvincia());
        textRazon.setText(empresa.getRazonSocial());
        textTelefono.setText(empresa.getTelefono());
        textIva.setText(empresa.getIva());

    }

    public void salirDatos(ActionEvent e){

        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();

    }

}
