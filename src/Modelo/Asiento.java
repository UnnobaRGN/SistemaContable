package Modelo;
import java.sql.Date;

public class Asiento {

    private int idasiento;
    private Date fecha;
    private String descripcion;
    private int id_usuario;

    public Asiento(Date fecha, String descripcion, int id_usuario) {

        this.fecha = fecha;
        this.descripcion = descripcion;
        this.id_usuario = id_usuario;
    }

    public int getIdasiento() {
        return idasiento;
    }

    public void setIdasiento(int idasiento) {
        this.idasiento = idasiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

}
