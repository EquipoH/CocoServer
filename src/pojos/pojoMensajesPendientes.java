package pojos;
public class pojoMensajesPendientes 
{
    private double idMensaje;
    private String remitente;
    private String destinatario;
    private String fechayhora;
    private String mensaje;
    private char leido;

    public pojoMensajesPendientes() {
    }

    /**
     * 
     * @param idMensaje Es el identificador del mensaje
     * @param remitente Es un foreign key hacia la tabla de usuarios del usuario que envia el mensaje
     * @param destinatario Es un foreign key hacia la tabla de usuarios del usuario que recibe el mensaje
     * @param fechayhora Es la fecha y hora en la que se envio el mensaje
     * @param mensaje Es el contenido del mensaje
     * @param leido Bandera que indica si el mensaje ha sido leido o no
     */
    public pojoMensajesPendientes(double idMensaje, String remitente, String destinatario, String fechayhora, String mensaje,char leido) {
        this.idMensaje = idMensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.fechayhora = fechayhora;
        this.mensaje = mensaje;
        this.leido=leido;
    }

    public char getLeido() {
        return leido;
    }

    public void setLeido(char leido) {
        this.leido = leido;
    }

    public double getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(double idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getFechayhora() {
        return fechayhora;
    }

    public void setFechayhora(String fechayhora) {
        this.fechayhora = fechayhora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}