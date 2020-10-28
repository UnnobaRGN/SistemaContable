package Controladores;

import Modelo.Cuentas;
import Modelo.UsuarioLogeado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;
import sample.ConexionBD;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
    private TableColumn<Cuentas,String> Habilitada;

    @FXML
    private Button AgregarCuenta;

    @FXML
    private Button EliminarCuenta;

    @FXML
    private Button HabilitarCuenta;

    @FXML
    private Button BotonSalir;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();


    ObservableList<Cuentas> lista;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MostrarDatos();

    }

    public void MostrarDatos() {
        AgregarCuenta.setVisible(u.getIdperfil() == 1);
        EliminarCuenta.setVisible(u.getIdperfil() == 1);
        HabilitarCuenta.setVisible(u.getIdperfil() == 1);
        Nro_Cuenta.setCellValueFactory(new PropertyValueFactory<Cuentas, Integer>("codigo_cuenta"));
        Cuenta.setCellValueFactory(new PropertyValueFactory<Cuentas, String>("cuenta"));
        Recibe_Saldo.setCellValueFactory(new PropertyValueFactory<Cuentas, Integer>("recibe_saldo"));
        Tipo.setCellValueFactory(new PropertyValueFactory<Cuentas, String>("tipo"));
        Habilitada.setCellValueFactory(new PropertyValueFactory<Cuentas,String>("habilitadaOno"));

        lista = getCuentas();
        cuentasTableView.setItems(lista);
    }

    public static ObservableList<Cuentas> getCuentas() {
        Connection conn = ConexionBD.getConnection();
        ObservableList<Cuentas> list = FXCollections.observableArrayList();
        try {

            String datos = "SELECT *, t.cuenta as tipo FROM cuenta AS c INNER JOIN tipocuenta as t ON t.idtipo = c.idtipo ORDER BY c.idcuenta ";

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(datos);

            while (rs.next()) {

                list.add(new Cuentas(rs.getInt("codigo_cuenta"), rs.getString("cuenta"), rs.getInt("recibe_saldo"), rs.getString("tipo"), rs.getInt("idcuenta"), rs.getFloat("saldo_actual"), rs.getString("habilitada_no")));

            }
        } catch (Exception e) {


        }
        return list;
    }

    public void Salir(ActionEvent event) {
        Stage stage = (Stage) BotonSalir.getScene().getWindow();
        stage.close();
    }


    public void AgregarCuentaBoton(ActionEvent event) throws IOException {

        Parent padre = FXMLLoader.load(getClass().getResource("/Vista/AgregarCuenta.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(padre);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setTitle("Agregar cuenta");
        ((Node) event.getSource()).getScene().getWindow().hide();

    }


    public void DeshabilitarCuentaBoton(ActionEvent actionEvent) {
        if (cuentasTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Seleccione la cuenta a deshabilitar");
            alert.showAndWait();
        } else {
            Cuentas cuenta = cuentasTableView.getSelectionModel().getSelectedItem();
            if (cuenta.getRecibe_saldo() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Por Favor,");
                alert.setContentText("Deshabilite las cuentas HIJAS!");
                alert.showAndWait();
            } else {
                if (cuenta.getHabilitadaOno().equals("No")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Atencion!");
                    alert.setContentText("La cuenta " + cuenta.getCuenta() + " ya se encuentra deshabilitada!");
                    alert.showAndWait();
                } else {
                    try {
                        Connection conn = ConexionBD.getConnection();
                        String sql = "UPDATE cuenta SET habilitada_no= '" + "No" + "'WHERE cuenta= '" + cuenta.getCuenta() + "'";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.execute();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Exito");
                        alert.setHeaderText("Operacion realizada!");
                        alert.setContentText("Ha deshabilitado la cuenta " + cuenta.getCuenta());
                        alert.showAndWait();
                        MostrarDatos();

                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    public void HabilitarCuentaBoton(ActionEvent actionEvent) {
        if (cuentasTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Seleccione la cuenta a habilitar");
            alert.showAndWait();
        } else {
            Cuentas cuenta = cuentasTableView.getSelectionModel().getSelectedItem();
            if (cuenta.getHabilitadaOno().equals("Si")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Atencion!");
                alert.setContentText("La cuenta " + cuenta.getCuenta() + " se encuentra habilitada!");
                alert.showAndWait();
            } else {
                try {
                    Connection conn = ConexionBD.getConnection();
                    String sql = "UPDATE cuenta SET habilitada_no= '" + "Si" + "'WHERE cuenta= '" + cuenta.getCuenta() + "'";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.execute();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Exito");
                    alert.setHeaderText("Operacion realizada!");
                    alert.setContentText("Ha habilitado la cuenta " + cuenta.getCuenta());
                    alert.showAndWait();
                    MostrarDatos();

                } catch (Exception e) {

                }
            }
        }
    }


}