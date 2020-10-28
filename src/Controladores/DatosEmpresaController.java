package Controladores;

import Modelo.Empresa;
import Modelo.UsuarioLogeado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    private Button botonSalir;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();

    private Empresa e = Empresa.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File brandingFile = new File("Imagenes/2.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        ImagenFondo.setImage(brandingImage);

        mostrarDatos();
        noModifica();

    }

    public void noModifica(){

        textTelefono.setDisable(true);
        textRazon.setDisable(true);
        textProvincia.setDisable(true);
        textLocalidad.setDisable(true);
        textEmail.setDisable(true);
        textDireccion.setDisable(true);
        textCodigo.setDisable(true);
        textCuit.setDisable(true);
        textIva.setDisable(true);

    }

    public void mostrarDatos(){

        ConexionBD conectarAhora = new ConexionBD();
        Connection conectarBD = conectarAhora.getConnection();


        String empresa = "SELECT * FROM empresa";


        try{
            Statement statement = conectarBD.createStatement();
            ResultSet rs =  statement.executeQuery(empresa);

            while (rs.next()) {
                textRazon.setText(rs.getString("razon_social"));
                textEmail.setText(rs.getString("email"));
                textDireccion.setText(rs.getString("direccion"));
                textCuit.setText(rs.getString("cuit"));
                textLocalidad.setText(rs.getString("localidad"));
                textProvincia.setText(rs.getString("provincia"));
                textIva.setText(rs.getString("iva"));
                textCodigo.setText(rs.getString("codigoPostal"));
                textTelefono.setText(rs.getString("telefono"));
            }



        }catch (Exception e){

        }


    }

    public void salirDatos(ActionEvent e){

        Stage stage = (Stage) botonSalir.getScene().getWindow();
        stage.close();

    }


}
