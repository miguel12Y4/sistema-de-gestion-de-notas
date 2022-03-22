package com.notas.registro.testService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.model.Profesor;
import com.notas.registro.repository.ProfesorRepository;
import com.notas.registro.repository.SearchRepository;
import com.notas.registro.service.ProfesorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*; 

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceTest {
    @Mock private SearchRepository searchRepository;
    @Mock private ProfesorRepository profesorRepository;

    @InjectMocks private ProfesorService profesorService;

    //test de findProfesores()

    @Test // si invoco findProfesores() y hay Profesores, entonces debe retornar los ProfesoresDTO
    void findProfesores1(){
        List<Profesor> profesores = new ArrayList<Profesor>();
        List<ProfesorDTO> resultado;

        for (int i = 0; i < 10; i++) {
            Profesor ap = new Profesor("197817231"+i, "Profesor"+i, "correo@ubb.cl"+i);
            profesores.add(ap);
        }

        when(profesorRepository.findAll()).thenReturn(profesores);

        //Act
        resultado = profesorService.findProfesores();

        //Assert (se asume que coincidirán los index de ambos Profesors en los arreglos)
        for (int i = 0; i < profesores.size(); i++) {
            assertEquals(profesores.get(i).getRut(), resultado.get(i).getRut());
            assertEquals(profesores.get(i).getNombre(), resultado.get(i).getNombre());
            assertEquals(profesores.get(i).getCorreo(), resultado.get(i).getCorreo());
        }
    }

    @Test // si invoco findProfesores() y no hay Profesores, entonces debe retornar una lista vacía
    void findProfesores2(){

        //arrange
        List<Profesor> profesores = new ArrayList<Profesor>();
        List<ProfesorDTO> resultado;

        when(profesorRepository.findAll()).thenReturn(profesores);
        
        //Act
        resultado = profesorService.findProfesores();

        //asert
        assertTrue(resultado.isEmpty());

    }

    //test de findProfesor(String rut)

    @Test // si invoco findAlmno y el rut coincide con un Profesor, entonces debe retornar el ProfesorDTP
    void findProfesor1(){
        //arrange

        ProfesorDTO resultado;

        Profesor al = new Profesor("1978172-2", "Profesor", "correo@ubb.cl");

        when(searchRepository.searchProfesor("1978172-2")).thenReturn(al);

        //Act
        resultado = profesorService.findProfesor("1978172-2");
        
        //Assert
        assertNotNull(resultado);
        assertEquals(al.getRut(), resultado.getRut());
        assertEquals(al.getNombre(), resultado.getNombre());
        assertEquals(al.getCorreo(), resultado.getCorreo());
    }

    @Test // si invoco findAlmno y el rut no coincide con un Profesor, entonces debe retornar null
    void findProfesor2(){
        //arrange

        ProfesorDTO resultado;

        when(searchRepository.searchProfesor("1978172-3")).thenReturn(null);

        //Act
        resultado = profesorService.findProfesor("1978172-3");
        
        //Assert
        assertNull(resultado);
    }
}
