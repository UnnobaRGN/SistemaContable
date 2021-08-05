package Modelo;

import java.util.Date;

public class Factura {

    private int idfactura;
    private int idventa;
    private boolean facturada;
    private Date fecha_pago;
    private String numero_factura;
    private double valor_cuota;
    private double total_pagado;
    private double total_debe;
    private int cuotas_pagadas;
    private int cuotas_totales;
    private Date fecha_emision;
    private String letra_factura;


    public Factura(int idventa, boolean facturada, Date fecha_pago, String numero_factura, double valor_cuota, double total_pagado, double total_debe, int cuotas_pagadas, int cuotas_totales, Date fecha_emision, String letra_factura) {
        this.idventa = idventa;
        this.facturada = facturada;
        this.fecha_pago = fecha_pago;
        this.numero_factura = numero_factura;
        this.valor_cuota = valor_cuota;
        this.total_pagado = total_pagado;
        this.total_debe = total_debe;
        this.cuotas_pagadas = cuotas_pagadas;
        this.cuotas_totales = cuotas_totales;
        this.fecha_emision = fecha_emision;
        this.letra_factura = letra_factura;
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

    public double getValor_cuota() {
        return valor_cuota;
    }

    public void setValor_cuota(double valor_cuota) {
        this.valor_cuota = valor_cuota;
    }

    public double getTotal_pagado() {
        return total_pagado;
    }

    public void setTotal_pagado(double total_pagado) {
        this.total_pagado = total_pagado;
    }

    public double getTotal_debe() {
        return total_debe;
    }

    public void setTotal_debe(double total_debe) {
        this.total_debe = total_debe;
    }

    public int getCuotas_pagadas() {
        return cuotas_pagadas;
    }

    public void setCuotas_pagadas(int cuotas_pagadas) {
        this.cuotas_pagadas = cuotas_pagadas;
    }

    public int getCuotas_totales() {
        return cuotas_totales;
    }

    public void setCuotas_totales(int cuotas_totales) {
        this.cuotas_totales = cuotas_totales;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getLetra_factura() {
        return letra_factura;
    }

    public void setLetra_factura(String letra_factura) {
        this.letra_factura = letra_factura;
    }
}
