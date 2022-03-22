package com.notas.registro.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "alumno")
public class Alumno {

    @Id
    @Column(name = "rut", nullable = false )
    private String rut;
    private String nombre;
    private String correo;

    @ManyToOne
    @JoinColumn(name = "rut_apoderado")
    private Apoderado apoderado;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @OneToMany(mappedBy = "alumno")
    private List<Rinde> rendiciones;

    public Alumno(){
        this.rendiciones = new ArrayList<>();
    }
    
    public Alumno ( String rut, String nombre, String correo){
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.rendiciones = new ArrayList<>();
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

    public Apoderado getApoderado() {
        return this.apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
        apoderado.addAlumno(this);
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Alumno)) {
            return false;
        }
        Alumno alumno = (Alumno) o;
        return Objects.equals(rut, alumno.rut) && Objects.equals(nombre, alumno.nombre) && Objects.equals(correo, alumno.correo) && Objects.equals(apoderado, alumno.apoderado) && Objects.equals(curso, alumno.curso) && Objects.equals(rendiciones, alumno.rendiciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rut, nombre, correo, apoderado, curso, rendiciones);
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