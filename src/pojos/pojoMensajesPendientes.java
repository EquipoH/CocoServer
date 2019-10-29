package pojos;
public class pojoMensajesPendientes 
{
    private double idMensaje;
    private String remitente;
    private String destinatario;
    private String fechayhora;
    private String mensaje;

    public pojoMensajesPendientes() {
    }

    public pojoMensajesPendientes(double idMensaje, String remitente, String destinatario, String fechayhora, String mensaje) {
        this.idMensaje = idMensaje;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.fechayhora = fechayhora;
        this.mensaje = mensaje;
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