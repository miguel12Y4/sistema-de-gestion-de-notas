package com.notas.registro.service;

import java.util.List;

import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.mapper.ProfesorMapper;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Evaluacion;
import com.notas.registro.model.Profesor;
import com.notas.registro.model.Rinde;
import com.notas.registro.repository.AsignaturaRepository;
import com.notas.registro.repository.CursoRepository;
import com.notas.registro.repository.ProfesorRepository;
import com.notas.registro.repository.RindeRepository;
import com.notas.registro.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService {

    public static final int NULL = 0;
    public static final int OK = 1;
    public static final int TIENE_ASIGNATURAS = 2;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private RindeRepository rindeRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<ProfesorDTO> findProfesores() {
        return ProfesorMapper.INSTANCIA.profesorListToProfesorDTOList(profesorRepository.findAll());
    }

    public ProfesorDTO findProfesor(String rut) {
        Profesor profesor = searchRepository.searchProfesor(rut);

        if(profesor!=null){
            return ProfesorMapper.INSTANCIA.profesorToProfesorDTO(profesor);
        }
        return null;
    }

    public boolean addProfesor(ProfesorDTO profesorDTO) {

        Profesor profe = searchRepository.searchProfesor(profesorDTO.getRut());

        if (profe == null) {
            try{
                
                Profesor profesor = ProfesorMapper.INSTANCIA.profesorDTOToProfesor(profesorDTO);
                return profesorRepository.saveAndFlush(profesor) != null;
            }catch(Exception e){
                return false;
            }
        }

        return false;

    }

    public int deleteProfesor(String rut){
        Profesor profesor = searchRepository.searchProfesor(rut);

        if(profesor==null){
            return NULL;
        }

        //Si está dictando asignaturas no se puede eliminar
        if(!profesor.getAsignaturas().isEmpty()){
            return TIENE_ASIGNATURAS;
        }
        
        Curso c = profesor.getCurso();
        if(c!=null){
            c.setProfesor(null);
            profesor.setCurso(null);
            cursoRepository.saveAndFlush(c);
        }

        

        profesorRepository.delete(profesor);
        return OK;
    }

    public ProfesorDTO modifyProfesor(ProfesorDTO profesorDTO){

        if(profesorDTO==null) return null;

        Profesor profesor = searchRepository.searchProfesor(profesorDTO.getRut());

        if(profesor==null) return null;

        if(profesorDTO.getNombre()!=null){
            profesor.setNombre(profesorDTO.getNombre());
        }

        if(profesorDTO.getCorreo()!=null){
            profesor.setCorreo(profesorDTO.getCorreo());
        }

        profesorRepository.saveAndFlush(profesor);

        return ProfesorMapper.INSTANCIA.profesorToProfesorDTO(profesor);
    }

    public boolean cerrarAsignatura(String rutProfesor, int idAsignatura){
        Profesor profesor = searchRepository.searchProfesor(rutProfesor);

        Asignatura asignatura = searchRepository.searchAsignatura(idAsignatura);

        
        if(profesor==null || asignatura==null ){
            return false;
        }
        
        if(profesor!=asignatura.getProfesor()){
            //el profesor que dicta la asignatura es otro
            return false;
        }
        List<Alumno> alumnos = asignatura.getCurso().getAlumnos();


        List<Evaluacion> evaluaciones = asignatura.getEvaluaciones();

        double ponderacion = 0;

        //verificar que esten todas las evaluaciones
        for(Evaluacion e: evaluaciones){
            ponderacion+= e.getPonderacion();
        }

        if(ponderacion != 1){
            //faltan asignaturas por crear
            return false;
        }
        
        for(Alumno a: alumnos){
            for(Evaluacion e: evaluaciones){
                Rinde rendicion = rindeRepository.findRindeByAlumnoAndEvaluacion(a,e);

                if(rendicion==null){
                    //se crea al usuario con la nota minima
                    Rinde r = new Rinde(10, a, e);
                    rindeRepository.saveAndFlush(r);
                }
            }
        }

        asignatura.setCerrada(true);

        asignaturaRepository.saveAndFlush(asignatura);

        return true;
    }
}
