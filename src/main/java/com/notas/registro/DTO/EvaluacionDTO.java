package com.notas.registro.DTO;

public class EvaluacionDTO {
    
    private int id;
    private String descripcion;
    private double ponderacion;

    private AsignaturaDTO asignatura;


    public EvaluacionDTO() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EvaluacionDTO id(int id) {
        setId(id);
        return this;
    }

    public EvaluacionDTO descripcion(String descripcion) {
        setDescripcion(descripcion);
        return this;
    }

    public EvaluacionDTO ponderacion(int ponderacion) {
        setPonderacion(ponderacion);
        return this;
    }

    public EvaluacionDTO asignatura(AsignaturaDTO asignatura) {
        setAsignatura(asignatura);
        return this;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPonderacion() {
        return this.ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public AsignaturaDTO getAsignatura() {
        return this.asignatura;
    }

    public void setAsignatura(AsignaturaDTO asignatura) {
        this.asignatura = asignatura;
    }


    @Override
    public String toString() {
        return "{" +
            " descripcion='" + getDescripcion() + "'" +
            ", ponderacion='" + getPonderacion() + "'" +
            ", asignatura='" + getAsignatura() + "'" +
            "}";
    }
    
}
