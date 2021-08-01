package Modelo;

public class Venta_Producto {

    private int idventa;
    private int idproducto;

    public Venta_Producto() {
    }

    public Venta_Producto(int idventa, int idproducto) {
        this.idventa = idventa;
        this.idproducto = idproducto;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }
}
