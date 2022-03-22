package com.notas.registro.DTO;

import java.util.Objects;

public class NotaDTO {
    
    private int idEvaluacion;
    private double nota;
    private double ponderacion;



    public NotaDTO() {
    }

    public NotaDTO(int idEvaluacion, double nota, double ponderacion) {
        this.idEvaluacion = idEvaluacion;
        this.nota = nota;
        this.ponderacion = ponderacion;
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

    public double getPonderacion() {
        return this.ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NotaDTO)) {
            return false;
        }
        NotaDTO notasDTO = (NotaDTO) o;
        return idEvaluacion == notasDTO.idEvaluacion && nota == notasDTO.nota && ponderacion == notasDTO.ponderacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvaluacion, nota, ponderacion);
    }

    @Override
    public String toString() {
        return "{" +
            " idEvaluacion='" + getIdEvaluacion() + "'" +
            ", nota='" + getNota() + "'" +
            ", ponderacion='" + getPonderacion() + "'" +
            "}";
    }

}
