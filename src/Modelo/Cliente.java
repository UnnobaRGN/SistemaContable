package Modelo;

public class Cliente {

    private int dni;
    private String nombre;
    private int telefono;
    private String direccion;
    private String razonSocial;
    //private int condicionIVA;
    private int cuit;
    private CondicionIva condicionIVA;
    private String apellido;
    private String Email;
    private TipoPersona tipoPersona;

    public Cliente(int dni, String nombre, int telefono, String direccion, String razonSocial, CondicionIva condicionIVA, int cuit,String apelldo,String Email,TipoPersona persona) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.razonSocial = razonSocial;
        this.condicionIVA = condicionIVA;
        this.cuit = cuit;
        this.apellido=apelldo;
        this.Email = Email;
        this.tipoPersona= persona;
    }

    public Cliente() {

    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre(String nombre) {
        return this.nombre;
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

    public String getRazonSocial(String razon_social) {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public CondicionIva getCondicionIVA() {
        return condicionIVA;
    }

        public void setCondicionIVA(CondicionIva condicionIVA) {
        this.condicionIVA = condicionIVA;
    }

    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }
}
