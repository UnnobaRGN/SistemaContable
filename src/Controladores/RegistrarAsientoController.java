package Controladores;

import Modelo.Cuenta_Asiento;
import Modelo.Cuentas;
import Modelo.UsuarioLogeado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ConexionBD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

//java.sql.Date hoy=java.sql.Date.valueOf("2010-03-04"); NO TOCAR!!!!!!!!!
public class RegistrarAsientoController implements Initializable {

    @FXML
    private ImageView FondoRegistrarAsiento = new ImageView();


    @FXML
    private ComboBox CuentasSeleccion;

    @FXML
    private TextField fecha;

    @FXML
    private TextField numeroAsiento;

    @FXML
    private Button ButtonCancelar;

    @FXML
    private RadioButton BotonDebe;

    @FXML
    private RadioButton BotonHaber;
    @FXML
    public ToggleGroup toggleGroup;

    @FXML
    private TableView<Cuenta_Asiento> CuentaAsientoTableView;

    @FXML
    private TableColumn<Cuenta_Asiento, String> Cuenta;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> Debe;

    @FXML
    private TableColumn<Cuenta_Asiento, Float> Haber;

    @FXML
    private TextField Monto;
    @FXML
    private TextArea Descripcion;

    private UsuarioLogeado u = UsuarioLogeado.getInstance();


    ObservableList<Cuenta_Asiento> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File fileFondoRegistrarAsiento = new File("Imagenes/2.jpg");
        Image brandingFondoRegistrarAsiento = new Image(fileFondoRegistrarAsiento.toURI().toString());
        FondoRegistrarAsiento.setImage(brandingFondoRegistrarAsiento);
        traerCuentasAcomboBox();
        fechaActual();
        numAsiento();
        InicializarLaTabla();


    }


    public void fechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        fecha.setText(formateador.format(ahora));
        fecha.setDisable(true);
    }


    public void cancelarRegistrarAsiento(ActionEvent event) throws IOException {
        Stage stage = (Stage) ButtonCancelar.getScene().getWindow();
        stage.close();
    }


    public void traerCuentasAcomboBox() {
        ObservableList<String> list = FXCollections.observableArrayList();
        this.CuentasSeleccion.getItems().clear();

        try {
            Connection conn = ConexionBD.getConnection();
            Statement s = conn.createStatement();
            String SQL = "SELECT * FROM cuenta WHERE recibe_saldo = 1 AND habilitada_no='"+"Si"+"'";
            ResultSet rs = s.executeQuery(SQL);

            while (rs.next()) {
                list.add(rs.getString("cuenta"));
            }

            CuentasSeleccion.setItems(list);
        } catch (Exception e) {

        }

    }

    public Cuentas retornarCuenta() {
        Cuentas cuenta = null;
        Connection conn = ConexionBD.getConnection();
        if (!CuentasSeleccion.getSelectionModel().isEmpty()) {
            String s = CuentasSeleccion.getSelectionModel().getSelectedItem().toString();

            try {
                String SQL = "SELECT * FROM cuenta WHERE cuenta='" + s + "'";
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(SQL);
                while (rs.next()) {

                    cuenta = new Cuentas(rs.getInt("codigo_cuenta"), rs.getString("cuenta"), rs.getInt("recibe_saldo"), rs.getString("idtipo"), rs.getInt("idcuenta"), rs.getInt("saldo_actual"),rs.getString("habilitada_no"));


                }

            } catch (Exception e) {

            }
            return cuenta;
        } else {

            return cuenta;
        }
    }


    public void numAsiento() {

        try {
            Connection conn = ConexionBD.getConnection();
            Statement s = conn.createStatement();
            String SQL = "SELECT COUNT(*) as asientonum FROM asiento";
            ResultSet rs = s.executeQuery(SQL);

            while (rs.next()) {
                int p = rs.getInt("asientonum");
                numeroAsiento.setText(String.valueOf(p + 1));
                numeroAsiento.setDisable(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void GuardarAsiento(ActionEvent actionEvent) throws IOException {

        if (retornarCuenta() != null) {
            AgregarAtabla();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Por favor,");
            alert.setContentText("Ingrese una cuenta antes de continuar");
            alert.showAndWait();
        }

    }


    public Cuenta_Asiento GuardarEnTabla(Cuenta_Asiento cuenta) {

        if (BotonDebe.isSelected()) {
            cuenta.setDebe(Float.parseFloat(Monto.getText()));
            cuenta.setHaber(0);
            cuenta.setCuenta(retornarCuenta().getCuenta());
            cuenta.setSaldo(retornarCuenta().getSaldo_actual());
            cuenta.setIdcuenta(retornarCuenta().getId_cuenta());
        }
        if (BotonHaber.isSelected()) {
            cuenta.setHaber(Float.parseFloat(Monto.getText()));
            cuenta.setDebe(0);
            cuenta.setCuenta(retornarCuenta().getCuenta());
            cuenta.setSaldo(retornarCuenta().getSaldo_actual());
            cuenta.setIdcuenta(retornarCuenta().getId_cuenta());
        }
        return cuenta;


    }

    public void AgregarAtabla() {

        if (BotonDebe.isSelected()) {
            tieneSaldoDebe(retornarCuenta());
        }
        if (BotonHaber.isSelected()) {
            tieneSaldoHaber(retornarCuenta());
        }

    }


    public void tieneSaldoDebe(Cuentas c) {
        if (c.getTipo().equals("2") || c.getTipo().equals("5")) {
            chequearDebeYhaber(c);
        } else {
            CuentaAsientoTableView.getItems().add(GuardarEnTabla(new Cuenta_Asiento()));
        }

    }

    public void tieneSaldoHaber(Cuentas c) {
        if (c.getTipo().equals("1")) {
            chequearDebeYhaber(c);
        } else {
            CuentaAsientoTableView.getItems().add(GuardarEnTabla(new Cuenta_Asiento()));
        }
    }


    public void chequearDebeYhaber(Cuentas c) {
        if (c.getSaldo_actual() > Float.parseFloat(Monto.getText())) {
            CuentaAsientoTableView.getItems().add(GuardarEnTabla(new Cuenta_Asiento()));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("A surgido un problema");
            alert.setContentText("La cuenta no dispone del saldo necesario para realizar la operacion requerida");
            alert.showAndWait();
        }
    }

    public void InicializarLaTabla() {
        Cuenta.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, String>("cuenta"));
        Debe.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("debe"));
        Haber.setCellValueFactory(new PropertyValueFactory<Cuenta_Asiento, Float>("haber"));
        CuentaAsientoTableView.setItems(list);
    }


    public void realizarCalculos() {
        for (int i = 0; i <= CuentaAsientoTableView.getItems().size(); i++) {

            try {
                Connection conn = ConexionBD.getConnection();
                String st = CuentaAsientoTableView.getItems().get(i).getCuenta();
                String SQL = "SELECT * FROM cuenta WHERE cuenta= '" + st + "'";
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery(SQL);
                while (rs.next()) {
                    Cuentas cuenta = new Cuentas(rs.getInt("codigo_cuenta"), rs.getString("cuenta"), rs.getInt("recibe_saldo"), rs.getString("idtipo"), rs.getInt("idcuenta"), rs.getFloat("saldo_actual"),rs.getString("habilitada_no"));
                    if(CuentaAsientoTableView.getItems().get(i).getDebe()!=0.0) {
                        realizarCalculoDebe(cuenta.getSaldo_actual(),cuenta.getTipo());

                    }

                }
            } catch (Exception e) {

            }

        }
    }


    public boolean sumarDebeYhaber() {
        float totalhaber = 0f;
        float totalDebe = 0f;
        for (Cuenta_Asiento a : CuentaAsientoTableView.getItems()) {
            totalhaber += a.getHaber();
            totalDebe += a.getDebe();
        }
        return totalhaber == totalDebe;

    }


    public void registrarAsiento(ActionEvent event) {

        if (sumarDebeYhaber() && CuentaAsientoTableView.getItems().size() > 1) {
            realizarCalculos();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion!");
            alert.setHeaderText("Ha surgido un problema");
            alert.setContentText("La suma del Debe NO es igual a la del Haber o hay un solo Registro");
            alert.showAndWait();

        }
    }

    public void realizarCalculoDebe(float saldo_actual, String tipo){

            if(tipo.equals("1")){
                saldo_actual+=sumaDebe();
                Connection conn = ConexionBD.getConnection();
                String sql = "UPDATE cuenta SET saldo_actual= '";

            }
            if(tipo.equals("2") || tipo.equals("5")) {
                saldo_actual-=sumaDebe();
            }
    }

    public float sumaDebe(){
        float totalDebe = 0f;
        for (Cuenta_Asiento a : CuentaAsientoTableView.getItems()) {
            totalDebe += a.getDebe();
        }
        return totalDebe;
    }

    public float sumahaber(){
        float totalhaber = 0f;
        for (Cuenta_Asiento a : CuentaAsientoTableView.getItems()) {
            totalhaber += a.getHaber();
        }
        return totalhaber;
    }
}
