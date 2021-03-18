package Modelo;

import java.sql.Date;

public class Producto {

    private int idProducto;
    private Integer codigo;
    private String nombreProducto;
    private String stock;
    private float precio;
    private String descripcion;

    public Producto(String nombreProducto, String stock, float precio, String descripcion) {
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
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
}
