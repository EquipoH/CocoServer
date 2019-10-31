package pojos;
public class pojoListaAmigos 
{
    private double idListaAmigos;
    private String nombre;
    private String dueno;
    
    public pojoListaAmigos() {
    }

    public pojoListaAmigos(double idListaAmigos, String nombre, String dueno) {
        this.idListaAmigos = idListaAmigos;
        this.nombre = nombre;
        this.dueno = dueno;
    }

    public double getIdListaAmigos() {
        return idListaAmigos;
    }

    public void setIdListaAmigos(double idListaAmigos) {
        this.idListaAmigos = idListaAmigos;
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