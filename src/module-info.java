module SistemaContable {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires pdfjet;

    opens sample;
    opens Controladores;
    opens Modelo;

}