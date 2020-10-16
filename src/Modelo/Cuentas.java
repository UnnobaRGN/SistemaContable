package Modelo;

public class Cuentas {

    private int id_cuenta;
    private String cuenta;
    private int codigo_cuenta;
    private int recibe_saldo;
    private float saldo_actual;
    private String tipo;
    private String habilitadaOno;

    public Cuentas(int codigo_cuenta, String cuenta, int recibe_saldo, String tipo,int id_cuenta,float saldo_actual,String habilitadaOno) {
        this.codigo_cuenta = codigo_cuenta;
        this.cuenta = cuenta;
        this.recibe_saldo = recibe_saldo;
        this.tipo = tipo;
        this.id_cuenta = id_cuenta;
        this.saldo_actual = saldo_actual;
        this.habilitadaOno = habilitadaOno;

    }

    public String getHabilitadaOno() {
        return habilitadaOno;
    }

    public void setHabilitadaOno(String habilitadaOno) {
        this.habilitadaOno = habilitadaOno;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public int getCodigo_cuenta() {
        return codigo_cuenta;
    }

    public void setCodigo_cuenta(int codigo_cuenta) {
        this.codigo_cuenta = codigo_cuenta;
    }

    public int getRecibe_saldo() {
        return recibe_saldo;
    }

    public void setRecibe_saldo(int recibe_saldo) {
        this.recibe_saldo = recibe_saldo;
    }

    public float getSaldo_actual() {
        return saldo_actual;
    }

    public void setSaldo_actual(float saldo_actual) {
        this.saldo_actual = saldo_actual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
