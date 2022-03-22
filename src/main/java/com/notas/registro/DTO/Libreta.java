package com.notas.registro.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Libreta {
    
    private String rut;
    private String nombreAlumno;
    private int idCurso;
    private double promedioGeneral;

    private List<ResumenAsignatura> asignaturas;

    

    public Libreta() {
        this.asignaturas= new ArrayList<>();
    }

    public Libreta(String rut, String nombreAlumno, int idCurso, double promedioGeneral, List<ResumenAsignatura> asignaturas) {
        this.rut = rut;
        this.nombreAlumno = nombreAlumno;
        this.idCurso = idCurso;
        this.promedioGeneral = promedioGeneral;
        this.asignaturas= asignaturas;
    }

    public String getRut() {
        return this.rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombreAlumno() {
        return this.nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public int getIdCurso() {
        return this.idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public double getPromedioGeneral() {
        return this.promedioGeneral;
    }

    public void setPromedioGeneral(double promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }

    public List<ResumenAsignatura> getAsignaturas() {
        return this.asignaturas;
    }

    public void setAsignaturas(List<ResumenAsignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Libreta rut(String rut) {
        setRut(rut);
        return this;
    }

    public Libreta nombreAlumno(String nombreAlumno) {
        setNombreAlumno(nombreAlumno);
        return this;
    }

    public Libreta idCurso(int idCurso) {
        setIdCurso(idCurso);
        return this;
    }

    public Libreta promedioGeneral(double promedioGeneral) {
        setPromedioGeneral(promedioGeneral);
        return this;
    }

    public Libreta asignaturas(List<ResumenAsignatura> asignaturas) {
        setAsignaturas(asignaturas);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Libreta)) {
            return false;
        }
        Libreta libreta = (Libreta) o;
        return Objects.equals(rut, libreta.rut) && Objects.equals(nombreAlumno, libreta.nombreAlumno) && idCurso == libreta.idCurso && promedioGeneral == libreta.promedioGeneral && Objects.equals(asignaturas, libreta.asignaturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rut, nombreAlumno, idCurso, promedioGeneral, asignaturas);
    }

    @Override
    public String toString() {
        return "{" +
            " rut='" + getRut() + "'" +
            ", nombreAlumno='" + getNombreAlumno() + "'" +
            ", idCurso='" + getIdCurso() + "'" +
            ", promedioGeneral='" + getPromedioGeneral() + "'" +
            ", asignaturas='" + getAsignaturas() + "'" +
            "}";
    }

}
