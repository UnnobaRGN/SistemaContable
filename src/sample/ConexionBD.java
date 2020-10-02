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



}
