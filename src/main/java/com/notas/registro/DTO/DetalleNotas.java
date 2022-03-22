package com.notas.registro.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetalleNotas {

    
    private int idAsignatura;
    
    private String nombreAsignatura;
    
    private String rutAlumno;
    
    private List<NotaDTO> notas;


    public DetalleNotas() {
        notas = new ArrayList<>();
    }

    
    public DetalleNotas(int idAsignatura, String nombreAsignatura, String rutAlumno) {
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.setRutAlumno(rutAlumno);
        this.notas = new ArrayList<>();
    }
    
    public String getRutAlumno() {
        return rutAlumno;
    }

    public void setRutAlumno(String rutAlumno) {
        this.rutAlumno = rutAlumno;
    }

    public List<NotaDTO> getNotas() {
        return this.notas;
    }

    public void setNotas(List<NotaDTO> notas) {
        this.notas = notas;
    }

    public int getIdAsignatura() {
        return this.idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return this.nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public boolean addNota(NotaDTO nota) {
        return this.notas.add(nota);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DetalleNotas)) {
            return false;
        }
        DetalleNotas detalleNotas = (DetalleNotas) o;
        return Objects.equals(notas, detalleNotas.notas) && idAsignatura == detalleNotas.idAsignatura && Objects.equals(nombreAsignatura, detalleNotas.nombreAsignatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notas, idAsignatura, nombreAsignatura);
    }

    @Override
    public String toString() {
        return "{" +
            " notas='" + getNotas() + "'" +
            ", idAsignatura='" + getIdAsignatura() + "'" +
            ", nombreAsignatura='" + getNombreAsignatura() + "'" +
            "}";
    }
    
    
}
