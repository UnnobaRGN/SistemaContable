package sample;


import Modelo.Cuentas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ConexionBD {

    public static Connection BaseDatosLink;

    public static Connection getConnection(){

        String nombreBD = "pruebaSistemaContable";
        String usuarioBD = "postgres";
        String contraseniaBD = "1234";
        String url = "jdbc:postgresql://localhost/" + nombreBD;

        try{
            Class.forName("org.postgresql.Driver");
            BaseDatosLink = DriverManager.getConnection(url,usuarioBD,contraseniaBD);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return BaseDatosLink;

    }

    public static ObservableList<Cuentas> getCuentas(){
        Connection conn = getConnection();
        ObservableList<Cuentas> list = FXCollections.observableArrayList();
        try {

            String datos = "SELECT *, t.cuenta as tipo FROM cuenta AS c INNER JOIN tipocuenta as t ON t.idtipo = c.idtipo ";

            Statement statement = conn.createStatement();
            ResultSet  rs =  statement.executeQuery(datos);

            while (rs.next()){
             
               list.add(new Cuentas(rs.getInt("codigo_cuenta"),rs.getString("cuenta"),rs.getInt("recibe_saldo"),rs.getString("tipo"),rs.getInt("idcuenta"),rs.getInt("saldo_actual")));

            }
        }catch (Exception e){


        }
        return list;
    }

}
