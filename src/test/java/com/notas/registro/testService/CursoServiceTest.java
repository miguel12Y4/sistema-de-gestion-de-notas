package com.notas.registro.testService;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.model.Curso;
import com.notas.registro.repository.CursoRepository;
import com.notas.registro.repository.SearchRepository;
import com.notas.registro.service.CursoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*; 

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
    @Mock private SearchRepository searchRepository;
    @Mock private CursoRepository CursoRepository;

    @InjectMocks private CursoService CursoService;

    //test de findCursos()

    @Test // si invoco findCursos() y hay Cursos, entonces debe retornar los CursosDTO
    void findCursos1(){
        List<Curso> cursos = new ArrayList<Curso>();
        List<CursoDTO> resultado;

        for (int i = 1; i < 10; i++) {
            Curso ap = new Curso("Curso"+i, "primero");
            ap.setId(i);
            cursos.add(ap);
        }

        when(CursoRepository.findAll()).thenReturn(cursos);

        //Act
        resultado = CursoService.findCursos();

        //Assert (se asume que coincidirán los index de ambos Cursos en los arreglos)
        for (int i = 1; i < cursos.size(); i++) {
            assertEquals(cursos.get(i).getId(), resultado.get(i).getId());
            assertEquals(cursos.get(i).getNombre(), resultado.get(i).getNombre());
        }
    }

    @Test // si invoco findCursos() y no hay Cursos, entonces debe retornar una lista vacía
    void findCursos2(){

        //arrange
        List<Curso> cursos = new ArrayList<Curso>();
        List<CursoDTO> resultado;

        when(CursoRepository.findAll()).thenReturn(cursos);
        
        //Act
        resultado = CursoService.findCursos();

        //asert
        assertTrue(resultado.isEmpty());

    }

    //test de findCurso(int id)

    @Test // si invoco findCurso y el id coincide con un Curso, entonces debe retornar el CursoDTP
    void findCurso1(){
        //arrange

        CursoDTO resultado;

        Curso cur = new Curso("Curso", "primero");
        cur.setId(1);

        when(searchRepository.searchCurso(1)).thenReturn(cur);

        //Act
        resultado = CursoService.findCurso(1);
        
        //Assert
        assertNotNull(resultado);
        assertEquals(cur.getId(), resultado.getId());
        assertEquals(cur.getNombre(), resultado.getNombre());
        assertEquals(cur.getGrado(), resultado.getGrado());
    }

    @Test // si invoco findAlmno y el rut no coincide con un Curso, entonces debe retornar null
    void findCurso2(){
        //arrange

        CursoDTO resultado;

        when(searchRepository.searchCurso(1)).thenReturn(null);

        //Act
        resultado = CursoService.findCurso(1);
        
        //Assert
        assertNull(resultado);
    }
}
    
    //cuando no hay Cursos y cuando hay Cursos
    //public List<CursoDTO> findCursos()

    //cuando el Curso existe, cuando no existe
    //public CursoDTO findCurso(String rut)

    //cuando los faltan datos y cuando esta repetido y cuando se hace correcctamente
    //public boolean addCurso(CursoDTO CursoDTO)

    //cuando el Curso existe, cuando no existe
    //public int deleteCurso(int id)

    //cuando el los datos son correctos y cuando son incorrectos.
    //public CursoDTO modifyCurso(CursoDTO CursoDTO)
