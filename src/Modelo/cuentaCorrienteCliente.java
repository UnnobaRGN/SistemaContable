package Modelo;

public class cuentaCorrienteCliente {

    private String numero;
    private String fechaEmision;
    private String fechaDePago;
    private String cobrado;
    private String deuda;
    private int cuotasTotales;
    private int cuotasPagadas;
    private float Total_IVA;
    private float totalcuotas;
    private int medioDePago;

    private String letra_factura;
    public cuentaCorrienteCliente(String numero, String fechaEmision, String fechaDePago, String cobrado, String deuda, int cuotasTotales, int cuotasPagadas) {
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.fechaDePago = fechaDePago;
        this.cobrado = cobrado;
        this.deuda = deuda;
        this.cuotasTotales = cuotasTotales;
        this.cuotasPagadas = cuotasPagadas;
    }

    public String getLetra_factura() {
        return letra_factura;
    }

    public void setLetra_factura(String letra_factura) {
        this.letra_factura = letra_factura;
    }

    public int getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(int medioDePago) {
        this.medioDePago = medioDePago;
    }

    public float getTotal_IVA() {
        return Total_IVA;
    }

    public void setTotal_IVA(float total_IVA) {
        Total_IVA = total_IVA;
    }

    public float getTotalcuotas() {
        return totalcuotas;
    }

    public void setTotalcuotas(float totalcuotas) {
        this.totalcuotas = totalcuotas;
    }

    public cuentaCorrienteCliente() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(String fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public String getCobrado() {
        return cobrado;
    }

    public void setCobrado(String cobrado) {
        this.cobrado = cobrado;
    }

    public String getDeuda() {
        return deuda;
    }

    public void setDeuda(String deuda) {
        this.deuda = deuda;
    }

    public int getCuotasTotales() {
        return cuotasTotales;
    }

    public void setCuotasTotales(int cuotasTotales) {
        this.cuotasTotales = cuotasTotales;
    }

    public int getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(int cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }
}
