package com.notas.registro.testService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.notas.registro.DTO.AlumnoDTO;
import com.notas.registro.DTO.DetalleNotas;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Evaluacion;
import com.notas.registro.model.Rinde;
import com.notas.registro.repository.AlumnoRepository;
import com.notas.registro.repository.RindeRepository;
import com.notas.registro.repository.SearchRepository;
import com.notas.registro.service.AlumnoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*; 

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {

    @Mock private SearchRepository searchRepository;
    @Mock private AlumnoRepository alumnoRepository;
    @Mock private RindeRepository rindeRepository;
    

    @InjectMocks private AlumnoService alumnoService;

    //test de findAlumnos()

    @Test // si invoco findAlumnos() y hay alumnos, entonces debe retornar los alumnosDTO
    void findAlumnos1(){
        List<Alumno> Alumnos = new ArrayList<Alumno>();
        List<AlumnoDTO> resultado;

        for (int i = 0; i < 10; i++) {
            Alumno ap = new Alumno("197817231"+i, "Alumno"+i, "correo@ubb.cl"+i);
            Alumnos.add(ap);
        }

        when(alumnoRepository.findAll()).thenReturn(Alumnos);

        //Act
        resultado = alumnoService.findAlumnos();

        //Assert (se asume que coincidirán los index de ambos Alumnos en los arreglos)
        for (int i = 0; i < Alumnos.size(); i++) {
            assertEquals(Alumnos.get(i).getRut(), resultado.get(i).getRut());
            assertEquals(Alumnos.get(i).getNombre(), resultado.get(i).getNombre());
            assertEquals(Alumnos.get(i).getCorreo(), resultado.get(i).getCorreo());
        }
    }

    @Test // si invoco findAlumnos() y no hay alumnos, entonces debe retornar una lista vacía
    void findAlumnos2(){

        //arrange
        List<Alumno> alumnos = new ArrayList<Alumno>();
        List<AlumnoDTO> resultado;

        when(alumnoRepository.findAll()).thenReturn(alumnos);
        
        //Act
        resultado = alumnoService.findAlumnos();

        //asert
        assertTrue(resultado.isEmpty());

    }

    //test de findAlumno(String rut)

    @Test // si invoco findAlmno y el rut coincide con un alumno, entonces debe retornar el alumnoDTP
    void findAlumno1(){
        //arrange

        AlumnoDTO resultado;

        Alumno al = new Alumno("1978172-2", "alumno", "correo@ubb.cl");

        when(searchRepository.searchAlumno("1978172-2")).thenReturn(al);

        //Act
        resultado = alumnoService.findAlumno("1978172-2");
        
        //Assert
        assertNotNull(resultado);
        assertEquals(al.getRut(), resultado.getRut());
        assertEquals(al.getNombre(), resultado.getNombre());
        assertEquals(al.getCorreo(), resultado.getCorreo());
    }

    @Test // si invoco findAlmno y el rut no coincide con un alumno, entonces debe retornar null
    void findAlumno2(){
        //arrange

        AlumnoDTO resultado;

        when(searchRepository.searchAlumno("1978172-3")).thenReturn(null);

        //Act
        resultado = alumnoService.findAlumno("1978172-3");
        
        //Assert
        assertNull(resultado);
    }


    @Test //caso positivo
    void getNotas(){

        // Arrange
        Alumno alumno = new Alumno("1913213", "mi nombre", "correo@ubb.cl");


        Asignatura asignatura = new Asignatura();
        asignatura.setId(1);
        asignatura.setNombre("asignatura");

        List<Evaluacion> evaluaciones = new ArrayList<>();

        for (int i = 1; i < 6; i++) {
            
            Evaluacion evaluacion = new Evaluacion(i, 0.2, "evaluacion"+i);
            asignatura.addEvaluacion(evaluacion);
            evaluaciones.add(evaluacion);
        }

        int j=1;
        for (Evaluacion e: evaluaciones) {
            when(rindeRepository.findRindeByAlumnoAndEvaluacion(alumno, e)).thenReturn(new Rinde(10*j,alumno,  e));
            j++;
        }

        when(searchRepository.searchAsignatura(1)).thenReturn(asignatura);

        when(searchRepository.searchAlumno("1913213")).thenReturn(alumno);
        

        //Act
        
        DetalleNotas notas = alumnoService.misNotas(1, "1913213");

        for (int i = 0; i < 5; i++) {
            int id1 = notas.getNotas().get(i).getIdEvaluacion();
            int id2 = evaluaciones.get(i).getId();

            assertEquals(id1, id2);
        }
        
    }

    //@Test //si invoco add alumno y el dato de entrada es valido entonces se guardará y retornará true
   

    //cuando los faltan datos y cuando esta repetido y cuando se hace correcctamente (3)
    //public boolean addAlumno(AlumnoDTO alumnoDTO)

    //cuando el alumno existe, cuando no existe (2)
    //public int deleteAlumno(String rut)

    //cuando el los datos son correctos y cuando son incorrectos. (2)
    //public AlumnoDTO modifyAlumno(AlumnoDTO alumnoDTO)
}
