package Controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import Modelo.UsuarioLogeado;


import java.net.URL;
import java.util.ResourceBundle;


public class PrincipalController implements Initializable {

    @FXML
    private Button Boton1;

    @FXML
    private Button boton2;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Boton1.setVisible(u.getIdperfil() == 1);
    }



    public void aCasa(ActionEvent event) {

        System.out.println("hola");

    }

}
