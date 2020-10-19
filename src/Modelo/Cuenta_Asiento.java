package Modelo;

import java.sql.Date;

public class Cuenta_Asiento {

    private int idcuenta;
    private String cuenta;
    private int idasiento;
    private float debe;
    private float haber;
    private float saldo;
    private Date fecha;
    private int num_asiento;
    private String descrip;

    /*public Cuenta_Asiento(int idc, String cuenta, int ida, float d, float h, Date f, String de, int num_a) {

        this.idcuenta = idc;
        this.idasiento = ida;
        this.debe = d;
        this.haber = h;
        this.fecha = f;
        this.descrip = de;
        this.num_asiento = num_a;

    }

     */

    public Cuenta_Asiento(){}

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getNum_asiento() {
        return num_asiento;
    }

    public void setNum_asiento(int num_asiento) {
        this.num_asiento = num_asiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public int getIdasiento() {
        return idasiento;
    }

    public void setIdasiento(int idasiento) {
        this.idasiento = idasiento;
    }

    public float getDebe() {
        return debe;
    }

    public void setDebe(float debe) {
        this.debe = debe;
    }

    public float getHaber() {
        return haber;
    }

    public void setHaber(float haber) {
        this.haber = haber;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}

