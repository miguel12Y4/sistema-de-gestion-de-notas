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
        this.asignaturas = new ArrayList<>();
        this.alumnos = new ArrayList<>();
    }
    

    public Curso(int id, String nombre, String grado, Profesor profesor){
        this.id = id;
        this.nombre = nombre;
        this.grado = grado;
        this.asignaturas = new ArrayList<>();
        this.alumnos = new ArrayList<>();
        this.profesor = profesor;
    }

    public Curso ( String nombre, String grado){
        this.nombre = nombre;
        this.grado = grado;
        asignaturas =  new ArrayList<>();
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
        alumno.setCurso(this);
        this.alumnos.add(alumno);
    }

    public boolean removeAlumno(Alumno alumno){
        return this.alumnos.remove(alumno);
    }

    public boolean removeAsignatura(Asignatura asignatura){
        return this.asignaturas.remove(asignatura);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Curso)) {
            return false;
        }
        Curso curso = (Curso) o;
        return id == curso.id && Objects.equals(nombre, curso.nombre) && Objects.equals(grado, curso.grado) && Objects.equals(asignaturas, curso.asignaturas) && Objects.equals(alumnos, curso.alumnos) && Objects.equals(profesor, curso.profesor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, grado, asignaturas, alumnos, profesor);
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