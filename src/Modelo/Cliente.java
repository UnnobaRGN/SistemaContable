package Modelo;

public class Cliente {

    private int dni;
    private String nombre;
    private int telefono;
    private String direccion;
    private String razonSocial;
    private int condicionIVA;
    private int cuit;

    public Cliente(int dni, String nombre, int telefono, String direccion, String razonSocial, int condicionIVA, int cuit) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razonSocial = razonSocial;
        this.condicionIVA = condicionIVA;
        this.cuit = cuit;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getCondicionIVA() {
        return condicionIVA;
    }

    public void setCondicionIVA(int condicionIVA) {
        this.condicionIVA = condicionIVA;
    }

    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }
}
