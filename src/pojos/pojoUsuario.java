package pojos;

import java.io.Serializable;

public class pojoUsuario implements Serializable
{
    private String usuario;
    private String nombre;
    private String apellidos;
    private String contrasena;
    private String correo;
    private char conectado;
    private int idPreguntaRecuperacion;
    private String respuestaRecuperacion;

    public pojoUsuario() {
    }

    /**
     * 
     * @param usuario Es el correo con el que el usuario se registro
     * @param nombre    Es el nombre del usuario
     * @param apellidos Apellidos del usuario
     * @param contrasena Contraseña del usuario
     * @param correo Correo del usuario. Es el mismo que el usuario.
     * @param conectado Bandera que indica si esta conectado el usuario o no.
     * @param idPreguntaRecuperacion Foreign key hacia la pregunta de recuperacion que corresponde al usuario
     * @param respuestaRecuperacion Respuesta de la pregunta de recuperacion del usuario.
     */
    public pojoUsuario(String usuario, String nombre, String apellidos, String contrasena, String correo, char conectado, int idPreguntaRecuperacion, String respuestaRecuperacion) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        this.correo = correo;
        this.conectado = conectado;
        this.idPreguntaRecuperacion = idPreguntaRecuperacion;
        this.respuestaRecuperacion = respuestaRecuperacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public char getConectado() {
        return conectado;
    }

    public void setConectado(char conectado) {
        this.conectado = conectado;
    }

    public int getIdPreguntaRecuperacion() {
        return idPreguntaRecuperacion;
    }

    public void setIdPreguntaRecuperacion(int idPreguntaRecuperacion) {
        this.idPreguntaRecuperacion = idPreguntaRecuperacion;
    }

    public String getRespuestaRecuperacion() {
        return respuestaRecuperacion;
    }

    public void setRespuestaRecuperacion(String respuestaRecuperacion) {
        this.respuestaRecuperacion = respuestaRecuperacion;
    }
}