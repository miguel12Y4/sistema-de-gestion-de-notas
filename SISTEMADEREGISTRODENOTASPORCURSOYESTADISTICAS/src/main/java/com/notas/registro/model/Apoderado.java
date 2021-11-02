package com.notas.registro.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "apoderado")
public class Apoderado {
    
    @Id
    @Column(name = "rut", nullable = false)
    private String rut;
    private String nombre;
    private String correo;

    @OneToMany(mappedBy = "apoderado")
    private List<Alumno> alumnos;
    
    public Apoderado(){
        alumnos = Collections.emptyList();
    }
    
    public Apoderado ( String rut, String nombre, String correo){
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        alumnos = Collections.emptyList();
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public boolean addAlumno(Alumno al){
        return alumnos.add(al);
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
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
    

    @Override
    public String toString() {
        return "{" +
            " rut='" + getRut() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", correo='" + getCorreo() + "'" +
            "}";
    }

}