package com.notas.registro.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResumenAsignatura {
    
    private int idAsignatura;
    private double promedio;
    private int nroEnRanking;
    private DetalleNotas notas;



    public ResumenAsignatura() {
    }

    public ResumenAsignatura(int idAsignatura, double promedio, int nroEnRanking, DetalleNotas notas) {
        this.idAsignatura = idAsignatura;
        this.promedio = promedio;
        this.nroEnRanking = nroEnRanking;
        this.notas = notas;
    }

    public int getIdAsignatura() {
        return this.idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public double getPromedio() {
        return this.promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public int getNroEnRanking() {
        return this.nroEnRanking;
    }

    public void setNroEnRanking(int nroEnRanking) {
        this.nroEnRanking = nroEnRanking;
    }

    public DetalleNotas getNotas() {
        return this.notas;
    }

    public void setNotas(DetalleNotas notas) {
        this.notas = notas;
    }

    public ResumenAsignatura idAsignatura(int idAsignatura) {
        setIdAsignatura(idAsignatura);
        return this;
    }

    public ResumenAsignatura promedio(double promedio) {
        setPromedio(promedio);
        return this;
    }

    public ResumenAsignatura nroEnRanking(int nroEnRanking) {
        setNroEnRanking(nroEnRanking);
        return this;
    }

    public ResumenAsignatura notas(DetalleNotas notas) {
        setNotas(notas);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ResumenAsignatura)) {
            return false;
        }
        ResumenAsignatura resumenAsignatura = (ResumenAsignatura) o;
        return idAsignatura == resumenAsignatura.idAsignatura && promedio == resumenAsignatura.promedio && nroEnRanking == resumenAsignatura.nroEnRanking && Objects.equals(notas, resumenAsignatura.notas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsignatura, promedio, nroEnRanking, notas);
    }

    @Override
    public String toString() {
        return "{" +
            " idAsignatura='" + getIdAsignatura() + "'" +
            ", promedio='" + getPromedio() + "'" +
            ", nroEnRanking='" + getNroEnRanking() + "'" +
            ", notas='" + getNotas() + "'" +
            "}";
    }

    
}
