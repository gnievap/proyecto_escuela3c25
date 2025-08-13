package models;

public class Bachillerato {
    private int idBachillerato;
    private String nombre;

    public Bachillerato(int idBachillerato, String nombre) {
        this.idBachillerato = idBachillerato;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdBachillerato() {
        return idBachillerato;
    }

    public void setIdBachillerato(int idBachillerato) {
        this.idBachillerato = idBachillerato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
