package Modelo;
import java.sql.Date;

public class Asiento {

    private int idasiento;
    private Date fecha;
    private String descripcion;

    public Asiento(Date fecha, String descripcion) {

        this.fecha = fecha;
        this.descripcion = descripcion;
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


}
