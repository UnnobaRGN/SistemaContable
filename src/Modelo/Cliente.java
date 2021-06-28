package Modelo;

public class Cliente {

    private String dni;
    private String nombre;
    private String telefono;
    private String direccion;
    private String razonSocial;
    //private int condicionIVA;
    private String cuit;
    private CondicionIva condicionIVA;
    private String apellido;
    private String Email;
    private TipoPersona tipoPersona;

    public Cliente(String dni, String nombre, String telefono, String direccion, String razonSocial, CondicionIva condicionIVA, String cuit,String apelldo,String Email,TipoPersona persona) {
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
}
