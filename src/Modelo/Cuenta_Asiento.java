package Modelo;

import java.util.Date;

public class Cuenta_Asiento {

    private int idcuenta;
    private int idasiento;
    private float debe;
    private float haber;
    private float saldo;

    public Cuenta_Asiento(int idcuenta, int idasiento, float debe, float haber, float saldo) {
        this.idcuenta = idcuenta;
        this.idasiento = idasiento;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
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

