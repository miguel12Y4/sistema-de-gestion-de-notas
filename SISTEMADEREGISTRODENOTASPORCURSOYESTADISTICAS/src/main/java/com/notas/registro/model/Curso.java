package com.notas.registro.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "curso")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private int id;
    private String nombre;
    private String grado;

    @OneToMany(mappedBy = "curso")
    private List<Asignatura> asignaturas;

    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;

    @OneToOne
    @JoinColumn(name = "rut_profesor")
    private Profesor profesor;
    
    public Curso(){
        asignaturas = Collections.emptyList();
    }
    
    public Curso ( String nombre, String grado){
        this.nombre = nombre;
        this.grado = grado;
        asignaturas = Collections.emptyList();
    }
    
    public List<Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    public boolean addAsignatura(Asignatura asignatura){
        return asignaturas.add(asignatura);
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public int getId (){
        return this.id;
    }

    public void setId (int id){
        this.id = id;
    }
    
    public String getNombre (){
        return this.nombre;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    
    public String getGrado (){
        return this.grado;
    }

    public void setGrado (String grado){
        this.grado = grado;
    }


    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Alumno> getAlumnos() {
        return this.alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void addAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", grado='" + getGrado() + "'" +
            "}";
    }
    
}