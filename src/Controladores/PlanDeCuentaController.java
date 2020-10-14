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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
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
    private Button AgregarCuenta;

    @FXML
    private Button EliminarCuenta;

    @FXML
    private Button ModificarCuenta;

    @FXML
    private Button BotonSalir;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();


    ObservableList<Cuentas> lista;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MostrarDatos();

    }

    public void MostrarDatos(){
        AgregarCuenta.setVisible(u.getIdperfil() == 1);
        EliminarCuenta.setVisible(u.getIdperfil() == 1);
        ModificarCuenta.setVisible(u.getIdperfil() == 1);
        Nro_Cuenta.setCellValueFactory(new PropertyValueFactory<Cuentas, Integer>("codigo_cuenta"));
        Cuenta.setCellValueFactory(new PropertyValueFactory<Cuentas, String>("cuenta"));
        Recibe_Saldo.setCellValueFactory(new PropertyValueFactory<Cuentas, Integer>("recibe_saldo"));
        Tipo.setCellValueFactory(new PropertyValueFactory<Cuentas, String>("tipo"));

        lista = getCuentas();
        cuentasTableView.setItems(lista);
    }

    public static ObservableList<Cuentas> getCuentas(){
        Connection conn = ConexionBD.getConnection();
        ObservableList<Cuentas> list = FXCollections.observableArrayList();
        try {

            String datos = "SELECT *, t.cuenta as tipo FROM cuenta AS c INNER JOIN tipocuenta as t ON t.idtipo = c.idtipo ORDER BY c.idcuenta ";

            Statement statement = conn.createStatement();
            ResultSet  rs =  statement.executeQuery(datos);

            while (rs.next()){

                list.add(new Cuentas(rs.getInt("codigo_cuenta"),rs.getString("cuenta"),rs.getInt("recibe_saldo"),rs.getString("tipo"),rs.getInt("idcuenta"),rs.getInt("saldo_actual")));

            }
        }catch (Exception e){


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
        ((Node)event.getSource()).getScene().getWindow().hide();

    }


    public void EliminarCuentaBoton(ActionEvent actionEvent){
        Connection conn = ConexionBD.getConnection();
        Cuentas cuenta = cuentasTableView.getSelectionModel().getSelectedItem();


        try{

            String sql = "DELETE FROM cuenta WHERE idcuenta="+ cuenta.getId_cuenta();

            Statement s = conn.createStatement();

            int n = s.executeUpdate(sql);

            if(n>=0){
                MostrarDatos();
            }

        }catch(Exception e) {

        }

    }
}
