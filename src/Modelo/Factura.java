package Modelo;

public class Factura {

    private int idfactura;
    private int idventa;
    private boolean facturada;


    public Factura(int idfactura, int venta, boolean facturada) {
        this.idfactura = idfactura;
        this.idventa = venta;
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

    public int getIdVenta() {
        return idventa;
    }

    public void setIdVenta(int venta) {
        this.idventa = venta;
    }

    public boolean isFacturada() {
        return facturada;
    }

    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }
}
