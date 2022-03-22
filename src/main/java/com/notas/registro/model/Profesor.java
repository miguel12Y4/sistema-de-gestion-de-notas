package com.notas.registro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "profesor")
public class Profesor {
    
    @Id
    @Column(name = "rut", nullable = false)
    private String rut;
    private String nombre;
    private String correo;

    @OneToOne(mappedBy = "profesor")
    private Curso curso;

    @OneToMany(mappedBy = "profesor")
    private List<Asignatura> asignaturas;

    public Profesor(){
        this.asignaturas = new ArrayList<>();
    }
    
    public Profesor ( String rut, String nombre, String correo){
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.asignaturas = new ArrayList<>();
    }

    public String getRut (){
        return this.rut;
    }

    public void setRut (String rut){
        if(rut!=null) this.rut = rut;
    }
    
    public String getNombre (){
        return this.nombre;
    }

    public void setNombre (String nombre){
        if(nombre!=null) this.nombre = nombre;
    }
    
    public String getCorreo (){
        return this.correo;
    }

    public void setCorreo (String correo){
        if(correo!=null) this.correo = correo;
    }


    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void addAsignatura(Asignatura asignatura){
        this.asignaturas.add(asignatura);
    }

    public boolean removeAsignatura(Asignatura asignatura){
        return this.asignaturas.remove(asignatura);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Profesor)) {
            return false;
        }
        Profesor profesor = (Profesor) o;
        return Objects.equals(rut, profesor.rut) && Objects.equals(nombre, profesor.nombre) && Objects.equals(correo, profesor.correo) && Objects.equals(curso, profesor.curso) && Objects.equals(asignaturas, profesor.asignaturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rut, nombre, correo, curso, asignaturas);
    }

    @Override
    public String toString() {
        return "{" +
            " rut='" + getRut() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", correo='" + getCorreo() + "'" +
            "}";
    }

    
}