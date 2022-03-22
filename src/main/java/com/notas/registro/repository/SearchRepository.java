package com.notas.registro.repository;

import java.util.Optional;

import com.notas.registro.model.Alumno;
import com.notas.registro.model.Apoderado;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Evaluacion;
import com.notas.registro.model.Profesor;
import com.notas.registro.model.Rinde;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchRepository {
    
    @Autowired
    private ApoderadoRepository apoderadoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private RindeRepository rindeRepository;


    public Apoderado searchApoderado(String rut) {
        try {
            return apoderadoRepository.getByRut(rut);

        } catch (Exception e) {
            return null;
        }
    }

    public Alumno searchAlumno(String rut) {
        try {

            return alumnoRepository.getByRut(rut);

        } catch (Exception e) {

            return null;

        }
    }

    public Profesor searchProfesor(String rut) {
        try {
            return profesorRepository.getByRut(rut);

        } catch (Exception e) {
            return null;
        }
    }

    public Curso searchCurso(int id) {
        try {

            Optional<Curso> curso = cursoRepository.findById(id);
            if (curso.isPresent()) {
                return curso.get();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Asignatura searchAsignatura(int id) {
        try {
            Optional<Asignatura> asignatura = asignaturaRepository.findById(id);
            if (asignatura.isPresent()) {
                return asignatura.get();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Evaluacion searchEvaluacion(int idEvaluacion) {
        try {
            Optional<Evaluacion> evaluacion = evaluacionRepository.findById(idEvaluacion);
            if (evaluacion.isPresent()) {
                return evaluacion.get();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Rinde searchRinde(Alumno alumno, Evaluacion evaluacion){
        try {
            return rindeRepository.findRindeByAlumnoAndEvaluacion(alumno, evaluacion);
        } catch (Exception e) {
            return null;
        }
    }
}
