package com.notas.registro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        this.alumnos = new ArrayList<>();
    }
    
    public Apoderado ( String rut, String nombre, String correo){
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.alumnos = new ArrayList<>();
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

    public boolean removeAlumno(Alumno alumno){
        return this.alumnos.remove(alumno);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Apoderado)) {
            return false;
        }
        Apoderado apoderado = (Apoderado) o;
        return Objects.equals(rut, apoderado.rut) && Objects.equals(nombre, apoderado.nombre) && Objects.equals(correo, apoderado.correo) && Objects.equals(alumnos, apoderado.alumnos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rut, nombre, correo, alumnos);
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