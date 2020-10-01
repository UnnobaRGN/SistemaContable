package Controladores;

import Modelo.Cuentas;
import Modelo.UsuarioLogeado;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PlanDeCuentaController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private TableView<Cuentas> cuentasTableView;

    @FXML
    private TableColumn<Cuentas, Integer> Nro_Cuenta;

    @FXML
    private TableColumn<Cuentas, String> Cuenta;

    @FXML
    private TableColumn<Cuentas, Integer> Recibe_Saldo;

    @FXML
    private TableColumn<Cuentas, String> Tipo;

    @FXML
    private Button AgregarCuenta;

    @FXML
    private Button DesabilitarCuenta;

    @FXML
    private Button ModificarCuenta;

    @FXML
    private Button BotonSalir;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();


    ObservableList<Cuentas> lista;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AgregarCuenta.setVisible(u.getIdperfil() == 1);
        DesabilitarCuenta.setVisible(u.getIdperfil() == 1);
        ModificarCuenta.setVisible(u.getIdperfil() == 1);
        Nro_Cuenta.setCellValueFactory(new PropertyValueFactory<Cuentas, Integer>("codigo_cuenta"));
        Cuenta.setCellValueFactory(new PropertyValueFactory<Cuentas, String>("cuenta"));
        Recibe_Saldo.setCellValueFactory(new PropertyValueFactory<Cuentas, Integer>("recibe_saldo"));
        Tipo.setCellValueFactory(new PropertyValueFactory<Cuentas, String>("tipo"));

        lista = ConexionBD.getCuentas();
        cuentasTableView.setItems(lista);

    }

    public void Salir(ActionEvent event) {
        Stage stage = (Stage) BotonSalir.getScene().getWindow();
        stage.close();
    }
}
