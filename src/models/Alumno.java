package models;

public class Alumno {
    private int idAlumno;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String domicilio;
    private String municipio;
    private int bachillerato;
    private double promBach;
    private char genero;
    private int cuatrimestre;
    private double pagado;
    private int idCarrera;
    private double promgral;

    public Alumno(int idAlumno, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio, String municipio, int bachillerato, double promBach, char genero, int cuatrimestre, double pagado, int idCarrera, double promgral) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.domicilio = domicilio;
        this.municipio = municipio;
        this.bachillerato = bachillerato;
        this.promBach = promBach;
        this.genero = genero;
        this.cuatrimestre = cuatrimestre;
        this.pagado = pagado;
        this.idCarrera = idCarrera;
        this.promgral = promgral;
    }

    // Getters y Setters
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getBachillerato() {
        return bachillerato;
    }

    public void setBachillerato(int bachillerato) {
        this.bachillerato = bachillerato;
    }

    public double getPromBach() {
        return promBach;
    }

    public void setPromBach(double promBach) {
        this.promBach = promBach;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public int getIdCarrera() {
        return idCarrera;   
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public double getPromgral() {
        return promgral;
    }

    public void setPromgral(double promgral) {
        this.promgral = promgral;
    }
    
    
}
