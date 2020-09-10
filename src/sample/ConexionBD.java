package sample;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    public Connection BaseDatosLink;

    public Connection getConnection(){

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
