package Modelo;

public class Producto {

    private int idProducto;
    private int codigo;
    private String nombreProducto;
    private String stock;
    private float precio;
    private String descripcion;
    private boolean activo;
    //private int idproveedor;
    private Proveedor proveedor;
    private float total;
    private int cantidad;

    public Producto() {}

    public Producto(int idProducto, int codigo, String nombreProducto, String stock, float precio, String descripcion, boolean activo, Proveedor proveedor, float total) {
        this.idProducto = idProducto;
        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
        this.activo = activo;
        this.proveedor = proveedor;
        this.total = total;
    }

    public Producto(Integer codigo, String nombreProducto, String stock, float precio, Proveedor proveedor) {

        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precio = precio;
        //this.idproveedor = idproveedor;
        this.proveedor=proveedor;
    }

    public Producto(Integer codigo, String nombreProducto, String stock, float precio, float total) {

        this.codigo = codigo;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precio = precio;
        //this.idproveedor = idproveedor;
        this.total=total;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
