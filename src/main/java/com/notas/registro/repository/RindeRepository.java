package com.notas.registro.repository;


import com.notas.registro.model.Alumno;
import com.notas.registro.model.Evaluacion;
import com.notas.registro.model.Rinde;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RindeRepository extends JpaRepository<Rinde, Integer>{
    
    Rinde findRindeByAlumnoAndEvaluacion(Alumno alumno, Evaluacion evaluacion);

    @Query(value = "SELECT sum(n), sum(ponderacion), count(ev) from (SELECT nota*ponderacion as n, ponderacion, e.id  as ev from rinde r join evaluacion e on r.id_evaluacion = e.id where rut_alumno = :rut and e.id_asignatura = :idAsignatura) as a",
    nativeQuery = true)
    Object getPromedio(@Param("rut") String rut, @Param("idAsignatura") int idAsignatura);
}
