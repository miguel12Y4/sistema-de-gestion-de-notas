package com.notas.registro.DTO;


public class RindeDTO {

    private String rut;

    private int idEvaluacion;

    private double nota;


    public RindeDTO() {
    }


    public RindeDTO(String rut, int idEvaluacion, double nota) {
        this.rut = rut;
        this.idEvaluacion = idEvaluacion;
        this.nota = nota;
    }



    public String getRut() {
        return this.rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public int getIdEvaluacion() {
        return this.idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "{" +
            " rut='" + getRut() + "'" +
            ", idEvaluacion='" + getIdEvaluacion() + "'" +
            ", nota='" + getNota() + "'" +
            "}";
    }

}