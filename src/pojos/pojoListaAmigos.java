package pojos;
public class pojoListaAmigos 
{
    private double idListaAmigos;
    private String nombre;

    public pojoListaAmigos() {
    }

    public pojoListaAmigos(double idListaAmigos, String nombre) {
        this.idListaAmigos = idListaAmigos;
        this.nombre = nombre;
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
}