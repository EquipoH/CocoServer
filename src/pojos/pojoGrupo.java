package pojos;
public class pojoGrupo 
{
    private double idGrupo;
    private String nombre;
    private String dueno;

    public pojoGrupo() {
    }

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