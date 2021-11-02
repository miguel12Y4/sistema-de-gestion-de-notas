package com.notas.registro.DTO;

public class AsignaturaDTO {

    private int id;
    private String nombre;
    private CursoDTO curso;
    private ProfesorDTO profesor;


    public AsignaturaDTO() {
    }

    public AsignaturaDTO(String nombre, CursoDTO curso) {
        this.nombre = nombre;
        this.curso = curso;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CursoDTO getCurso() {
        return this.curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    public ProfesorDTO getProfesor() {
        return this.profesor;
    }

    public void setProfesor(ProfesorDTO profesor) {
        this.profesor = profesor;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", curso='" + getCurso() + "'" +
            "}";
    }
   

    
}
