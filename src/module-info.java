module SistemaContable {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens sample;
    opens Controladores;
    opens Modelo;

}