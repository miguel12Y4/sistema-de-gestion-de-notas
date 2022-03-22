package com.notas.registro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "evaluacion")
public class Evaluacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    private double ponderacion;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @OneToMany(mappedBy = "evaluacion")
    private List<Rinde> rendiciones;


    public Evaluacion() {
        this.rendiciones = new ArrayList<>();
    }

    public Evaluacion(double ponderacion, String descripcion) {
        this.ponderacion = ponderacion;
        this.descripcion = descripcion;
        this.rendiciones = new ArrayList<>();
    }

    public Evaluacion(int id, double ponderacion, String descripcion) {
        this.id = id;
        this.ponderacion = ponderacion;
        this.descripcion = descripcion;
        this.rendiciones = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPonderacion() {
        return this.ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Asignatura getAsignatura() {
        return this.asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public List<Rinde> getRendiciones() {
        return this.rendiciones;
    }

    public void setRendiciones(List<Rinde> rendiciones) {
        this.rendiciones = rendiciones;
    }

    public boolean addRendicion(Rinde rendicion){
        return this.rendiciones.add(rendicion);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Evaluacion)) {
            return false;
        }
        Evaluacion evaluacion = (Evaluacion) o;
        return id == evaluacion.id && ponderacion == evaluacion.ponderacion && Objects.equals(descripcion, evaluacion.descripcion) && Objects.equals(asignatura, evaluacion.asignatura) && Objects.equals(rendiciones, evaluacion.rendiciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ponderacion, descripcion, asignatura, rendiciones);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", ponderacion='" + getPonderacion() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", asignatura='" + getAsignatura() + "'" +
            ", rendiciones='" + getRendiciones() + "'" +
            "}";
    }

}
