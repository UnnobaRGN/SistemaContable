package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class RegistrarAsientoController implements Initializable {

    @FXML
    private ImageView FondoRegistrarAsiento = new ImageView();

    @FXML
    private DatePicker FechaRegistrarAsiento = new DatePicker();



    @FXML
    private Button ButtonCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileFondoRegistrarAsiento = new File("Imagenes/2.jpg");
        Image brandingFondoRegistrarAsiento = new Image(fileFondoRegistrarAsiento.toURI().toString());
        FondoRegistrarAsiento.setImage(brandingFondoRegistrarAsiento);


        ///////DatePicker FechaRegistrarAsiento = new DatePicker(LocalDate.now());
        ///////FechaRegistrarAsiento.setEditable(false);

    }

    public void cancelarRegistrarAsiento(ActionEvent event) throws IOException {
        Stage stage = (Stage) ButtonCancelar.getScene().getWindow();
        stage.close();
    }


}