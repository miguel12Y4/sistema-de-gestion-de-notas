package com.notas.registro.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "rinde")
public class Rinde {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    private double nota;

    @ManyToOne
    @JoinColumn(name = "rut_alumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_evaluacion")
    private Evaluacion evaluacion;



    public Rinde() {
    }

    public Rinde(double nota, Alumno alumno, Evaluacion evaluacion) {
        this.nota = nota;
        this.alumno = alumno;
        this.evaluacion = evaluacion;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return this.alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Evaluacion getEvaluacion() {
        return this.evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rinde)) {
            return false;
        }
        Rinde rinde = (Rinde) o;
        return id == rinde.id && nota == rinde.nota && Objects.equals(alumno, rinde.alumno) && Objects.equals(evaluacion, rinde.evaluacion);
    }

    public boolean equals2(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rinde)) {
            return false;
        }
        Rinde rinde = (Rinde) o;
        return nota == rinde.nota && Objects.equals(alumno, rinde.alumno) && Objects.equals(evaluacion, rinde.evaluacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nota, alumno, evaluacion);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nota='" + getNota() + "'" +
            ", alumno='" + getAlumno() + "'" +
            ", evaluacion='" + getEvaluacion() + "'" +
            "}";
    }

    

}
