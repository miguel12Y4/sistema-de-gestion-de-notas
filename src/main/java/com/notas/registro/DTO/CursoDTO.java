package com.notas.registro.DTO;


public class CursoDTO {

    private int id;
    private String nombre;
    private String grado;

    private ProfesorDTO profesor;
    
    public CursoDTO() {
    }


    public CursoDTO(int id, String nombre, String grado) {
        this.id = id;
        this.nombre = nombre;
        this.grado = grado;
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

    public String getGrado() {
        return this.grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
    
    public ProfesorDTO getProfesor() {
        return this.profesor;
    }

    public void setProfesor(ProfesorDTO profesor) {
        this.profesor = profesor;
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
