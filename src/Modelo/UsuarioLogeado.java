package Modelo;

public class UsuarioLogeado {

    private static UsuarioLogeado usuarioLog;

    private int id;
    private String usuario;
    private String rol;

    private UsuarioLogeado() {

    }


    public static UsuarioLogeado getInstance(){

        if(usuarioLog == null){
            usuarioLog = new UsuarioLogeado();
        }
        return usuarioLog;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }




}
