package com.notas.registro.DTO;

import java.util.Objects;

public class Promedio {
    
    private String rut;
    private double nota;
    private int idAsignatura;


    public Promedio() {
    }
    
    public Promedio(String rut, double nota, int idAsignatura) {
        this.rut = rut;
        this.nota = nota;
        this.setIdAsignatura(idAsignatura);
    }
    
    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }


    public String getRut() {
        return this.rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Promedio rut(String rut) {
        setRut(rut);
        return this;
    }

    public Promedio nota(double nota) {
        setNota(nota);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Promedio)) {
            return false;
        }
        Promedio promedio = (Promedio) o;
        return Objects.equals(rut, promedio.rut) && nota == promedio.nota;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rut, nota);
    }

    @Override
    public String toString() {
        return "{" +
            " rut='" + getRut() + "'" +
            ", nota='" + getNota() + "'" +
            "}";
    }
    
}
