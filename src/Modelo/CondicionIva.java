package Modelo;

public class CondicionIva {

    private int idcondicioniva;
    private int codigo;
    private String nombre;

    public CondicionIva(int idcondicioniva, int codigo, String nombre) {
        this.idcondicioniva = idcondicioniva;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public CondicionIva() {
    }

    public int getIdcondicioniva() {
        return idcondicioniva;
    }

    public void setIdcondicioniva(int idcondicioniva) {
        this.idcondicioniva = idcondicioniva;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
