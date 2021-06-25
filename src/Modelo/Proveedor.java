package Modelo;

public class Proveedor {

    private String nombre;
    private String razonSocial;
    private String sectorComercial;
    private String direccion;
    private String ciudad;
    private String provincia;
    private int telefono;
    private String url;
    private String observaciones;


    public Proveedor(String nombre, String razonSocial, String sectorComercial, String direccion, String ciudad, String provincia, int telefono, String url, String observaciones) {
        this.nombre = nombre;
        this.razonSocial = razonSocial;
        this.sectorComercial = sectorComercial;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.url = url;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getSectorComercial() {
        return sectorComercial;
    }

    public void setSectorComercial(String sectorComercial) {
        this.sectorComercial = sectorComercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
