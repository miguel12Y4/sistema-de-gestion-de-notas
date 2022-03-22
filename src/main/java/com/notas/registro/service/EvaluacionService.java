package com.notas.registro.service;

import java.util.List;

import com.notas.registro.DTO.EvaluacionDTO;
import com.notas.registro.DTO.RindeDTO;
import com.notas.registro.mapper.EvaluacionMapper;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Evaluacion;
import com.notas.registro.model.Profesor;
import com.notas.registro.model.Rinde;
import com.notas.registro.repository.AsignaturaRepository;
import com.notas.registro.repository.EvaluacionRepository;
import com.notas.registro.repository.RindeRepository;
import com.notas.registro.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluacionService {

    public static final int NULL = 0;
    public static final int OK = 1;
    public static final int PROFESOR_SIN_PERMISOS = 2;
    public static final int EXCESO_EN_PONDERACION = 3;
    public static final int PROFESOR_NO_EXISTE = 4;
    public static final int LISTA_VACIA = 5;
    public static final int ERROR = 6;
    public static final int NOTA_FUERA_DE_RANGO = 7;
    

    
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;
    
    @Autowired
    private RindeRepository rindeRepository;

    public List<EvaluacionDTO> findEvaluaciones() {
        return EvaluacionMapper.INSTANCIA.evaluacionListToEvaluacionDTOList(evaluacionRepository.findAll());
    }

    public int addEvaluacion(EvaluacionDTO evaluacionDTO, String rut) {

        //si la evaluacionDTO viene vacía o la asignatura o el rut del profesor
        if(evaluacionDTO == null || evaluacionDTO.getAsignatura() ==null || rut==null){
            return NULL;
        }

        Profesor profesor = searchRepository.searchProfesor(rut);

        Asignatura asignatura = searchRepository.searchAsignatura(evaluacionDTO.getAsignatura().getId());

        if(asignatura==null){
            return NULL;
        }

        List<Evaluacion> evaluaciones = asignatura.getEvaluaciones();

        //asegurarse que el total de las ponderaciones de la evaluacion no pase de 1
        double ponderacion = 0;
        for (Evaluacion evaluacion : evaluaciones){
            ponderacion+=evaluacion.getPonderacion();
        }
        ponderacion+=evaluacionDTO.getPonderacion();
        if(ponderacion>1){
            return EXCESO_EN_PONDERACION;
        }


        if(profesor!=asignatura.getProfesor()){
            return PROFESOR_SIN_PERMISOS;
        }
        
        Evaluacion evaluacion = EvaluacionMapper.INSTANCIA.evaluacionDTOToEvaluacion(evaluacionDTO);
        asignatura.addEvaluacion(evaluacion);

        evaluacionRepository.saveAndFlush(evaluacion);
        asignaturaRepository.saveAndFlush(asignatura);
        return OK;
    }

    public int addNotas(List<RindeDTO> notas, String rutProfesor){

        if(notas.isEmpty()){
            return LISTA_VACIA;

        }

        if(rutProfesor==null){
            return PROFESOR_NO_EXISTE;
        }

        Profesor profesor = searchRepository.searchProfesor(rutProfesor);

        if(profesor==null){
            return PROFESOR_NO_EXISTE;
        }

        //procesar cada rendicion
        for(RindeDTO rindeDTO : notas){
            
            String rut = rindeDTO.getRut() ;
            int idEvaluacion = rindeDTO.getIdEvaluacion();
            double nota = rindeDTO.getNota();

            Alumno alumno = searchRepository.searchAlumno(rut);
            Evaluacion evaluacion = searchRepository.searchEvaluacion(idEvaluacion);

            if(nota<10 || nota>70){
                return NOTA_FUERA_DE_RANGO;
            }
            
            if(alumno==null || evaluacion==null){
                return NULL;
            }

            try{
                if(!evaluacion.getAsignatura().getProfesor().getRut().equals(profesor.getRut())){
                    return PROFESOR_SIN_PERMISOS;
                }
            }catch(Exception e){
                e.printStackTrace();
                return ERROR;
            }

            Rinde rendicion = new Rinde(nota, alumno, evaluacion);
            evaluacion.addRendicion(rendicion);
            
            try{
            
                Rinde r = rindeRepository.saveAndFlush(rendicion);

                if(!r.equals2(rendicion)){
                    return ERROR;
                }
            
            }catch(Exception e){
                return ERROR;
            }

        }
        return OK;

    }
}
