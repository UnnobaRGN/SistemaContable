package Modelo;

public class TipoPersona {

    private int id_tipopersona;
    private String tipopersona;


    public TipoPersona(int id_tipopersona, String tipopersona) {
        this.id_tipopersona = id_tipopersona;
        this.tipopersona = tipopersona;
    }

    public TipoPersona() {
    }

    public int getId_tipopersona() {
        return id_tipopersona;
    }

    public void setId_tipopersona(int id_tipopersona) {
        this.id_tipopersona = id_tipopersona;
    }

    public String getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(String tipopersona) {
        this.tipopersona = tipopersona;
    }
}
