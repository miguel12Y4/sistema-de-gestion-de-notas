package com.notas.registro.testService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.notas.registro.DTO.AsignaturaDTO;
import com.notas.registro.model.Asignatura;
import com.notas.registro.repository.AsignaturaRepository;
import com.notas.registro.repository.SearchRepository;
import com.notas.registro.service.AsignaturaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*; 

@ExtendWith(MockitoExtension.class)
public class AsignaturaServiceTest {
    @Mock private SearchRepository searchRepository;
    @Mock private AsignaturaRepository AsignaturaRepository;

    @InjectMocks private AsignaturaService AsignaturaService;

    //test de findAsignaturas()

    @Test // si invoco findAsignaturas() y hay Asignaturas, entonces debe retornar los AsignaturasDTO
    void findAsignaturas1(){
        List<Asignatura> Asignaturas = new ArrayList<Asignatura>();
        List<AsignaturaDTO> resultado;

        for (int i = 0; i < 10; i++) {
            Asignatura ap = new Asignatura("Asignatura"+i);
            ap.setId(i);
            Asignaturas.add(ap);
        }

        when(AsignaturaRepository.findAll()).thenReturn(Asignaturas);

        //Act
        resultado = AsignaturaService.findAsignaturas();

        //Assert (se asume que coincidirán los index de ambos Asignaturas en los arreglos)
        for (int i = 0; i < Asignaturas.size(); i++) {
            assertEquals(Asignaturas.get(i).getId(), resultado.get(i).getId());
            assertEquals(Asignaturas.get(i).getNombre(), resultado.get(i).getNombre());
        }
    }

    @Test // si invoco findAsignaturas() y no hay Asignaturas, entonces debe retornar una lista vacía
    void findAsignaturas2(){

        //arrange
        List<Asignatura> Asignaturas = new ArrayList<Asignatura>();
        List<AsignaturaDTO> resultado;

        when(AsignaturaRepository.findAll()).thenReturn(Asignaturas);
        
        //Act
        resultado = AsignaturaService.findAsignaturas();

        //asert
        assertTrue(resultado.isEmpty());

    }

    //test de findAsignatura(int id)

    @Test // si invoco findAsignatura y el id coincide con un Asignatura, entonces debe retornar el AsignaturaDTP
    void findAsignatura1(){
        //arrange

        AsignaturaDTO resultado;

        Asignatura as = new Asignatura("Asignatura");
        as.setId(1);

        when(searchRepository.searchAsignatura(1)).thenReturn(as);

        //Act
        resultado = AsignaturaService.findAsignatura(1);
        
        //Assert
        assertNotNull(resultado);
        assertEquals(as.getId(), resultado.getId());
        assertEquals(as.getNombre(), resultado.getNombre());
    }

    @Test // si invoco findAlmno y el rut no coincide con un Asignatura, entonces debe retornar null
    void findAsignatura2(){
        //arrange

        AsignaturaDTO resultado;

        when(searchRepository.searchAsignatura(1)).thenReturn(null);

        //Act
        resultado = AsignaturaService.findAsignatura(1);
        
        //Assert
        assertNull(resultado);
    }
}

    //cuando los faltan datos y cuando esta repetido y cuando se hace correcctamente (3)
    //public boolean addAsignatura(AsignaturaDTO AsignaturaDTO)

    //cuando el Asignatura existe, cuando no existe (2)
    //public int deleteAsignatura(int id)

    //cuando el los datos son correctos y cuando son incorrectos. (2)
    //public AsignaturaDTO modifyAsignatura(AsignaturaDTO AsignaturaDTO)
