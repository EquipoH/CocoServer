package pojos;
public class pojoUsuarioGrupo 
{
    private double idUsuarioGrupo;
    private double idGrupo;
    private String usuario;
    private char solicitud;

    public pojoUsuarioGrupo() {
    }

    /**
     * 
     * @param idUsuarioGrupo identificador de un usuario en un grupo
     * @param idGrupo foreign key hacia el grupo al que pertenece
     * @param usuario foreign key hacia la tabla usuario del usuario
     * @param solicitud bandera que indica si el usuario ha aceptado la solicitud de grupo o no
     */
    public pojoUsuarioGrupo(double idUsuarioGrupo, double idGrupo, String usuario, char solicitud) {
        this.idUsuarioGrupo = idUsuarioGrupo;
        this.idGrupo = idGrupo;
        this.usuario = usuario;
        this.solicitud = solicitud;
    }

    public double getIdUsuarioGrupo() {
        return idUsuarioGrupo;
    }

    public void setIdUsuarioGrupo(double idUsuarioGrupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
    }

    public double getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(double idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public char getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(char solicitud) {
        this.solicitud = solicitud;
    }
}