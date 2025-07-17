package models;

public class Carrera {
    private int idcarrera;
    private String nombre;
    private double monto;

    public Carrera(int idcarrera, String nombre, double monto) {
        this.idcarrera = idcarrera;
        this.nombre = nombre;
        this.monto = monto;
    }
    
    public int getIdcarrera() {
        return idcarrera;
    }
    public void setIdcarrera(int idcarrera) {
        this.idcarrera = idcarrera;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

    
    
}
