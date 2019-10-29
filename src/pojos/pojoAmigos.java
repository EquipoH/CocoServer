package pojos;
public class pojoAmigos 
{
    private int idAmigos;
    private String amigo;
    private double listaAmigos;
    private char aceptado;
    private String apodo;

    public pojoAmigos() {
    }
    
    public pojoAmigos(int idAmigos, String amigo, double listaAmigos, char aceptado, String apodo) {
        this.idAmigos = idAmigos;
        this.amigo = amigo;
        this.listaAmigos = listaAmigos;
        this.aceptado = aceptado;
        this.apodo = apodo;
    }
    
    public int getIdAmigos() {
        return idAmigos;
    }

    public void setIdAmigos(int idAmigos) {
        this.idAmigos = idAmigos;
    }

    public String getAmigo() {
        return amigo;
    }

    public void setAmigo(String amigo) {
        this.amigo = amigo;
    }

    public double getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(double listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public char getAceptado() {
        return aceptado;
    }

    public void setAceptado(char aceptado) {
        this.aceptado = aceptado;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    } 
}