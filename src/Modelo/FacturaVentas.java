package Modelo;

import java.util.Date;

public class FacturaVentas {

    private String numero;
    private String cliente;
    private float total;
    private Date fecha;


    public FacturaVentas() {
    }

    public FacturaVentas(String numero, String cliente, float total, Date fecha) {
        this.numero = numero;
        this.cliente = cliente;
        this.total = total;
        this.fecha = fecha;

    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
