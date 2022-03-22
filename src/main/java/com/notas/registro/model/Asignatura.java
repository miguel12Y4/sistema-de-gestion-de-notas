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

@Entity(name = "asignatura")
public class Asignatura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "rut_profesor")
    private Profesor profesor;

    @OneToMany(mappedBy = "asignatura")
    private List<Evaluacion> evaluaciones;

    @JoinColumn(name = "cerrada")
    private Boolean cerrada;

    public Asignatura(){
        this.evaluaciones = new ArrayList<>();
    }


    public Asignatura(int id, String nombre, Curso curso, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        curso.addAsignatura(this);
        this.profesor = profesor;
        this.cerrada= false;
        this.evaluaciones = new ArrayList<>();
    }

    public Asignatura(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public Boolean isCerrada() {
        return this.cerrada;
    }

    public Boolean getEstaCerrada() {
        return this.cerrada;
    }

    public void setCerrada(Boolean estaCerrada) {
        this.cerrada = estaCerrada;
    }

    public boolean addEvaluacion(Evaluacion e) {
        double ponderacion=0;
        for(Evaluacion ev: evaluaciones){
            ponderacion+=ev.getPonderacion();
        }
        if(ponderacion+e.getPonderacion()<=1){
            e.setAsignatura(this);
            return this.evaluaciones.add(e);
        }
        return false;
    }

    public List<Evaluacion> getEvaluaciones() {
        return this.evaluaciones;
    }

    public boolean removeEvaluacion(Evaluacion e) {
        return this.evaluaciones.remove(e);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Asignatura)) {
            return false;
        }
        Asignatura asignatura = (Asignatura) o;
        return id == asignatura.id && Objects.equals(nombre, asignatura.nombre) && Objects.equals(curso, asignatura.curso) && Objects.equals(profesor, asignatura.profesor) && Objects.equals(evaluaciones, asignatura.evaluaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, curso, profesor, evaluaciones);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }

}
