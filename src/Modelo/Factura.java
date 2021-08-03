package Modelo;

import java.util.Date;

public class Factura {

    private int idfactura;
    private int idventa;
    private boolean facturada;
    private Date fecha_pago;
    private String numero_factura;


    public Factura(int idfactura, int venta, boolean facturada, Date fecha_pago, String numero_factura) {
        this.idfactura = idfactura;
        this.idventa = venta;
        this.facturada = facturada;
        this.fecha_pago=fecha_pago;
        this.numero_factura=numero_factura;
    }

    public Factura() {
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public boolean isFacturada() {
        return facturada;
    }

    public void setFacturada(boolean facturada) {
        this.facturada = facturada;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }
}
