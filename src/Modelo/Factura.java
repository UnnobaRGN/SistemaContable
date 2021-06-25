package Modelo;

public class Factura {

    private int idfactura;
    private Venta venta;
    private boolean facturada;


    public Factura(int idfactura, Venta venta, boolean facturada) {
        this.idfactura = idfactura;
        this.venta = venta;
        this.facturada = facturada;
    }

    public Factura() {
    }


    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public boolean isFacturada() {
        return facturada;
    }

    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }
}
