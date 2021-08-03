package Modelo;

import java.util.Date;

public class Venta {

    private int idventa;
    private int codigo;
    private double total_descuento;
    private double total;
    private Date fecha;
    private int usuarioLogeado;
    private int cliente;
    private int idmediopago;


    public Venta(int idventa, int codigo, double total_descuento, double total, Date fecha, int usuarioLogeado, int cliente, int idmediopago) {
        this.idventa = idventa;
        this.codigo = codigo;
        this.total_descuento = total_descuento;
        this.total = total;
        this.fecha = fecha;
        this.usuarioLogeado = usuarioLogeado;
        this.cliente = cliente;
        this.idmediopago=idmediopago;
    }

    public Venta() {
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getTotal_descuento() {
        return total_descuento;
    }

    public void setTotal_descuento(double total_descuento) {
        this.total_descuento = total_descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(int usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getIdmediopago() {
        return idmediopago;
    }

    public void setIdmediopago(int idmediopago) {
        this.idmediopago = idmediopago;
    }
}
