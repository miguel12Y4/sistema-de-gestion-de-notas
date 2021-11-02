package com.notas.registro.DTO;

public class AlumnoDTO {
    
    private String rut;
    private String nombre;
    private String correo;

    private ApoderadoDTO apoderado;
    private CursoDTO curso;

    public AlumnoDTO() {
    }


    public String getRut() {
        return this.rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public ApoderadoDTO getApoderado() {
        return this.apoderado;
    }
    
    public void setApoderado(ApoderadoDTO apoderado) {
        this.apoderado = apoderado;
    }
    
    
    public CursoDTO getCurso() {
        return this.curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }


    @Override
    public String toString() {
        return "{" +
            " rut='" + getRut() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", apoderado='" + getApoderado().toString() + "'" +
            ", curso='" + getCurso().toString() + "'" +
            "}";
    }
    
    
}
