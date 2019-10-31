package pojos;
public class pojoPreguntaRecuperacion 
{
   private int idPreguntaRecuperacion;
   private String pregunta;
   
   public pojoPreguntaRecuperacion() {
   }
   
   /**
    * @param idPreguntaRecuperacion Es el identificador de la pegunta que se utiliza par recuperar la contraseña
    * @param pregunta es la pregunta que se le hará al usuario para recuperar su contraseña
    */
   public pojoPreguntaRecuperacion(int idPreguntaRecuperacion, String pregunta) {
       this.idPreguntaRecuperacion = idPreguntaRecuperacion;
       this.pregunta = pregunta;
   }
   
   public int getIdPreguntaRecuperacion() {
       return idPreguntaRecuperacion;
   }
   
   public void setIdPreguntaRecuperacion(int idPreguntaRecuperacion) {
       this.idPreguntaRecuperacion = idPreguntaRecuperacion;
   }
   
   public String getPregunta() {
       return pregunta;
   }
   
   public void setPregunta(String pregunta) {
       this.pregunta = pregunta;
   }
}