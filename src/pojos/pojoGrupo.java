package pojos;
public class pojoGrupo 
{
    private double idGrupo;
    private String nombre;
    private String dueno;

    public pojoGrupo() {
    }
    
    /**
     * @param idGrupo identifica el id del grupo
     * @param nombre es el nombre del grupo
     * @param dueno es el foreign key del dueño del grupo
     */
    public pojoGrupo(double idGrupo, String nombre, String dueno) {
        this.idGrupo = idGrupo;
        this.nombre = nombre;
        this.dueno = dueno;
    }
    
    public double getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(double idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }
}