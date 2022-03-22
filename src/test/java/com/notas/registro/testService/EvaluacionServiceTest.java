package com.notas.registro.testService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.notas.registro.DTO.EvaluacionDTO;
import com.notas.registro.DTO.RindeDTO;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Evaluacion;
import com.notas.registro.model.Profesor;
import com.notas.registro.model.Rinde;
import com.notas.registro.repository.EvaluacionRepository;
import com.notas.registro.repository.RindeRepository;
import com.notas.registro.repository.SearchRepository;
import com.notas.registro.service.EvaluacionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*; 

@ExtendWith(MockitoExtension.class)
public class EvaluacionServiceTest {
    
    @Mock private SearchRepository searchRepository;
    @Mock private EvaluacionRepository EvaluacionRepository;
    @Mock private RindeRepository rindeRepository;

    @InjectMocks private EvaluacionService EvaluacionService;

    //test de findEvaluacions()

    @Test // si invoco findEvaluacions() y hay Evaluacions, entonces debe retornar los EvaluacionsDTO
    void findEvaluacions1(){
        List<Evaluacion> Evaluacions = new ArrayList<Evaluacion>();
        List<EvaluacionDTO> resultado;

        for (int i = 1; i < 10; i++) {
            Evaluacion ap = new Evaluacion(0.2, "primero");
            ap.setId(i);
            Evaluacions.add(ap);
        }

        when(EvaluacionRepository.findAll()).thenReturn(Evaluacions);

        //Act
        resultado = EvaluacionService.findEvaluaciones();

        //Assert (se asume que coincidirán los index de ambos Evaluacions en los arreglos)
        for (int i = 1; i < Evaluacions.size(); i++) {
            assertEquals(Evaluacions.get(i).getId(), resultado.get(i).getId());
            assertEquals(Evaluacions.get(i).getDescripcion(), resultado.get(i).getDescripcion());
        }
    }

    @Test // si invoco findEvaluacions() y no hay Evaluacions, entonces debe retornar una lista vacía
    void findEvaluacions2(){

        //arrange
        List<Evaluacion> Evaluacions = new ArrayList<Evaluacion>();
        List<EvaluacionDTO> resultado;

        when(EvaluacionRepository.findAll()).thenReturn(Evaluacions);
        
        //Act
        resultado = EvaluacionService.findEvaluaciones();

        //asert
        assertTrue(resultado.isEmpty());

    }

    @Test //caso correcto
    void addNotas1(){

        //Arrange
        Profesor profesor = new Profesor("rut","nombre","correo");

        List<Alumno> alumnos = new ArrayList<Alumno>();
        
        Curso c = new Curso(1, "A", "primero", profesor);

        Asignatura asignatura = new Asignatura(1, "matematicas", c, profesor);


        Alumno alumno;
        for (int i = 0; i <10; i++){
            alumno = new Alumno("rut"+i,"nombre"+i,"correo"+i);
            c.addAlumno(alumno);

            alumnos.add(alumno);
        }
        List<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();
        
        double[] ponderaciones = {0.2, 0.2, 0.2, 0.2, 0.2};
        
        Evaluacion evaluacion;

        for (int i = 0; i <ponderaciones.length; i++){
            
            evaluacion = new Evaluacion(i, ponderaciones[i], "Certamen"+i);
            asignatura.addEvaluacion(evaluacion);
            evaluaciones.add(evaluacion);
        }
        
        List<RindeDTO> rendicionesdto = new ArrayList<RindeDTO>();
        List<Rinde> rendiciones = new ArrayList<Rinde>();
        
        for(int i = 0; i < evaluaciones.size(); i++){
            for (int j = 0; j < alumnos.size() ; j++) {
                RindeDTO r = new RindeDTO(alumnos.get(j).getRut(),evaluaciones.get(i).getId(), 50);
                Rinde ren = new Rinde(r.getNota(), alumnos.get(j), evaluaciones.get(i));
                rendiciones.add(ren);
                rendicionesdto.add(r);
            }
        }

        when(searchRepository.searchProfesor(profesor.getRut())).thenReturn(profesor);
        
        for(int i = 0; i <alumnos.size(); i++) {
            when(searchRepository.searchAlumno(alumnos.get(i).getRut())).thenReturn(alumnos.get(i));
        }
        
        for(int i = 0; i <evaluaciones.size(); i++){
            when(searchRepository.searchEvaluacion(evaluaciones.get(i).getId())).thenReturn(evaluaciones.get(i));
        }

        for(int i = 0; i <rendiciones.size(); i++) {
            when(rindeRepository.saveAndFlush(rendiciones.get(i))).thenReturn(rendiciones.get(i));
        }


        //Act
        int resut = EvaluacionService.addNotas(rendicionesdto, profesor.getRut());

        //Assert
        assertEquals(com.notas.registro.service.EvaluacionService.OK, resut);
    }

    @Test //Entregar una lista vacia
    void addNotas2(){

        
        List<RindeDTO> rendicionesdto = new ArrayList<RindeDTO>();

        //Act
        int resut = EvaluacionService.addNotas(rendicionesdto, "");

        //Assert
        assertEquals(com.notas.registro.service.EvaluacionService.LISTA_VACIA, resut);
    }

    @Test //Entregar el rut como null
    void addNotas3(){

        
        List<RindeDTO> rendicionesdto = new ArrayList<RindeDTO>();
        rendicionesdto.add(new RindeDTO());

        //Act
        int resut = EvaluacionService.addNotas(rendicionesdto, null);

        //Assert
        assertEquals(com.notas.registro.service.EvaluacionService.PROFESOR_NO_EXISTE, resut);
    }

    @Test //Entregar el rut de un profesor que no existe
    void addNotas4(){

        Alumno alumnos = new Alumno();
        Evaluacion evaluacion = new Evaluacion();

        List<RindeDTO> rendicionesdto = new ArrayList<RindeDTO>();
        rendicionesdto.add(new RindeDTO(alumnos.getRut(),evaluacion.getId(), 7));

        //Act
        int resut = EvaluacionService.addNotas(rendicionesdto, "11111111-1");

        //Assert
        assertEquals(com.notas.registro.service.EvaluacionService.PROFESOR_NO_EXISTE, resut);
    }

    @Test //el rut del alumno de un rindeDTO no pertenece a algun alumno
    void addNotas5(){

        Alumno alumno = new Alumno();
        Evaluacion evaluacion = new Evaluacion();

        Profesor profesor = new Profesor();

        List<RindeDTO> rendicionesdto = new ArrayList<RindeDTO>();
        RindeDTO r = new RindeDTO();
        r.setNota(60);
        rendicionesdto.add(r);

        when(searchRepository.searchAlumno(alumno.getRut())).thenReturn(null);
        when(searchRepository.searchEvaluacion(evaluacion.getId())).thenReturn(evaluacion);

        when(searchRepository.searchProfesor("11111111-1")).thenReturn(profesor);

        //Act
        int resut = EvaluacionService.addNotas(rendicionesdto, "11111111-1");

        //Assert
        assertEquals(com.notas.registro.service.EvaluacionService.NULL, resut);
    }

    @Test //el id de evaluacion de un rindeDTO no pertenece a alguna evaluacion
    void addNotas6(){

        Alumno alumno = new Alumno();
        Evaluacion evaluacion = new Evaluacion();
        Profesor profesor = new Profesor();

        List<RindeDTO> rendicionesdto = new ArrayList<RindeDTO>();
        RindeDTO r = new RindeDTO();
        r.setNota(60);
        rendicionesdto.add(r);

        when(searchRepository.searchAlumno(alumno.getRut())).thenReturn(alumno);
        when(searchRepository.searchEvaluacion(evaluacion.getId())).thenReturn(null);

        when(searchRepository.searchProfesor("11111111-1")).thenReturn(profesor);

        //Act
        int resut = EvaluacionService.addNotas(rendicionesdto, "11111111-1");

        //Assert
        assertEquals(com.notas.registro.service.EvaluacionService.NULL, resut);
    }
}
