package com.notas.registro.repository;

import java.util.Optional;

import com.notas.registro.model.Asignatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer>{
    
    public Optional<Asignatura> findById(int id);

    @Query(value = "SELECT rut_alumno, sum(n) nota, sum(ponderacion) from ( SELECT nota*ponderacion as n, ponderacion, e.id, r.rut_alumno  from rinde r join evaluacion e on r.id_evaluacion = e.id  join asignatura a  on e.id_asignatura = a.id where a.id = :idAsignatura ) as a group by rut_alumno order by nota desc",
    nativeQuery = true)
    Object[] getPromedioAlumnosAndRanking(@Param("idAsignatura") int idAsignatura);

    @Query(value = "select avg(nota) from (SELECT rut_alumno, sum(n) nota, sum(ponderacion) from ( SELECT nota*ponderacion as n, ponderacion, e.id, r.rut_alumno  from rinde r join evaluacion e on r.id_evaluacion = e.id  join asignatura a  on e.id_asignatura = a.id where a.id = :idAsignatura ) as a group by rut_alumno order by nota desc) as alias",
    nativeQuery = true)
    double getPromedioByAsignatura(@Param("idAsignatura") int idAsignatura);

    @Query(value = "SELECT rut_alumno, asig, sum(n) nota, sum(ponderacion) from ( SELECT nota*ponderacion as n, ponderacion, e.id, r.rut_alumno, a.id asig from rinde r join evaluacion e on r.id_evaluacion = e.id  join asignatura a  on e.id_asignatura = a.id where r.rut_alumno=:rutAlumno ) as a group by asig order by nota desc",
    nativeQuery = true)
    Object[] getAllPromediosByRut(@Param("rutAlumno") String rutAlumno);

    @Query(value = "select avg(nota) from ( SELECT rut_alumno, asig, sum(n) nota, sum(ponderacion) from ( SELECT nota*ponderacion as n, ponderacion, e.id, r.rut_alumno, a.id asig from rinde r join evaluacion e on r.id_evaluacion = e.id  join asignatura a  on e.id_asignatura = a.id where r.rut_alumno=:rutAlumno ) as a group by asig order by nota desc ) as alias",
    nativeQuery = true)
    double getPromedioFinalByRut(@Param("rutAlumno") String rutAlumno);
}
