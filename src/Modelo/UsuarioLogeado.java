package Modelo;

public class UsuarioLogeado {

    private static UsuarioLogeado usuarioLog;

    private int id;
    private String usuario;
    private int idperfil;

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

    public int getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }
}
