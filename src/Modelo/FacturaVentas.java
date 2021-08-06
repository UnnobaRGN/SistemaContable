package Modelo;

import java.util.Date;

public class FacturaVentas {

    private String numero;
    private String cliente;
    private String total;
    private String fecha;
    private String deuda;

    private String letra;


    public FacturaVentas() {
    }

    public FacturaVentas(String numero, String cliente, String total, String fecha,String deuda) {
        this.numero = numero;
        this.cliente = cliente;
        this.total = total;
        this.fecha = fecha;
        this.deuda=deuda;

    }


    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDeuda() {
        return deuda;
    }

    public void setDeuda(String deuda) {
        this.deuda = deuda;
    }
}
