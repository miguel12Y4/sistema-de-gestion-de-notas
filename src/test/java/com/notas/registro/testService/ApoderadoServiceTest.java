package com.notas.registro.testService;

import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.extension.ExtendWith; 
import org.mockito.InjectMocks; 
import org.mockito.Mock; 
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import com.notas.registro.DTO.ApoderadoDTO;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Apoderado;
import com.notas.registro.repository.ApoderadoRepository;
import com.notas.registro.repository.SearchRepository;
import com.notas.registro.service.ApoderadoService;

import static org.junit.jupiter.api.Assertions.*; 
import static org.mockito.Mockito.*; 

@ExtendWith(MockitoExtension.class)
public class ApoderadoServiceTest {

    @Mock private SearchRepository searchRepository;
    @Mock private ApoderadoRepository apoderadoRepository;

    @InjectMocks private ApoderadoService apoderadoService;

    //test de funcion findApoderados()

    @Test //si invoco find apoderados, debería mostrarme una lista DTO con todos los apoderados que tengo
    void siInvocoFindApoderadosYHayAPoderados(){
        //arrange
        List<Apoderado> apoderados = new ArrayList<Apoderado>();
        List<ApoderadoDTO> resultado;

        for (int i = 0; i < 10; i++) {
            Apoderado ap = new Apoderado("197817231"+i, "apoderado"+i, "correo@ubb.cl"+i);
            apoderados.add(ap);
        }

        when(apoderadoRepository.findAll()).thenReturn(apoderados);

        //Act
        resultado = apoderadoService.findApoderados();

        //Assert (se asume que coincidirán los index de ambos apoderados en los arreglos)
        for (int i = 0; i < apoderados.size(); i++) {
            assertEquals(apoderados.get(i).getRut(), resultado.get(i).getRut());
            assertEquals(apoderados.get(i).getNombre(), resultado.get(i).getNombre());
            assertEquals(apoderados.get(i).getCorreo(), resultado.get(i).getCorreo());
        }


    }

    @Test //si invoco find apoderados y no hay apoderados, debería mostrarme una lista DTO vacía
    void siInvocoFindApoderadosYNoHayAPoderados(){
        //arrange
        List<Apoderado> apoderados = new ArrayList<Apoderado>();
        List<ApoderadoDTO> resultado;

        when(apoderadoRepository.findAll()).thenReturn(apoderados);
        
        //Act
        resultado = apoderadoService.findApoderados();

        //asert
        assertTrue(resultado.isEmpty());


    }

    //test de findApoderado(String rut)

    @Test//si invoco findApoderao y el rut coincice con un apoderado, debe retornar el apoderadoDTO
    void siInvocoFindApoderadoYElRutCoincideConAlgunApoderado(){

        //arrange

        ApoderadoDTO resultado;

        Apoderado ap = new Apoderado("1978172-2", "apoderado", "correo@ubb.cl");

        when(searchRepository.searchApoderado("1978172-2")).thenReturn(ap);

        //Act
        resultado = apoderadoService.findApoderado("1978172-2");
        
        //Assert
        assertNotNull(resultado);
        assertEquals(ap.getRut(), resultado.getRut());
        assertEquals(ap.getNombre(), resultado.getNombre());
        assertEquals(ap.getCorreo(), resultado.getCorreo());
        
    }

    @Test//si invoco findApoderado y el rut No coincice con un apoderado, debe retornar null
    void siInvocoFindApoderadoYElRutNoCoincideConAlgunApoderado(){

        //arrange

        ApoderadoDTO resultado;

        when(searchRepository.searchApoderado("1978172-3")).thenReturn(null);

        //Act
        resultado = apoderadoService.findApoderado("1978172-3");
        
        //Assert
        assertNull(resultado);
        
    }


    //test addApoderado(ApoderadoDTO apoderado)

    @Test// si Invoco AddApoderado Y El Dato De Entrada Es Valido Entonce Se Guardara Y Retornara True
    void siInvocoAddApoderadoYElDatoDeEntradaEsValidoEntonceSeGuardaraYRetornaraTrue(){

        //Arrange

        ApoderadoDTO apoderadoInput = new ApoderadoDTO("1912131", "nombre", "correo@ubb.cl");
        Apoderado ap = new Apoderado("1912131", "nombre", "correo@ubb.cl");
        
        when(searchRepository.searchApoderado("1912131")).thenReturn(null);
        when(apoderadoRepository.saveAndFlush(ap)).thenReturn(ap);
        
        //Act
        boolean result = apoderadoService.addApoderado(apoderadoInput);


        //Assert
        assertTrue(result);

    }

    @Test// si Invoco AddApoderado Y El Dato De Entrada No Es Valido Entonces No Se Guardara Y Retornara False
    void siInvocoAddApoderadoYElDatoDeEntradaNoEsValidoEntonceNoSeGuardaraYRetornaraFalse(){

        //Arrange

        ApoderadoDTO apoderadoInput = new ApoderadoDTO("1912131", null, "correo@ubb.cl");
        ApoderadoDTO apoderadoInput2 = new ApoderadoDTO(null, "nombre", "correo@ubb.cl");
        ApoderadoDTO apoderadoInput3 = new ApoderadoDTO("1912131", "nombre", null);
        
        //Act
        boolean result = apoderadoService.addApoderado(apoderadoInput);
        boolean result2 = apoderadoService.addApoderado(apoderadoInput2);
        boolean result3 = apoderadoService.addApoderado(apoderadoInput3);
        boolean result4 = apoderadoService.addApoderado(null);


        //Assert
        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);

    }

    @Test// si Invoco AddApoderado Y El Dato De Entrada Ya estaba en la base de datos Entonces No Se Guardara Y Retornara False
    void siInvocoAddApoderadoYElDatoDeEntradaYaEstabaEnLaBDEntonceNoSeGuardaraYRetornaraFalse(){

        //Arrange

        ApoderadoDTO apoderadoInput = new ApoderadoDTO("1912131", "Nombre", "correo@ubb.cl");
        Apoderado apoderado = new Apoderado("1912131", "Nombre", "correo@ubb.cl");
        
        when(searchRepository.searchApoderado("1912131")).thenReturn(apoderado);

        //Act
        boolean result = apoderadoService.addApoderado(apoderadoInput);


        //Assert
        assertFalse(result);

    }


    //test de deleteApoderado(String rut)


    @Test // si invoco deleteApoderado y el apoderado no existe entonces retornar code NULL
    void siInvocoDeleteAPoderadoYElApoderadoNoExisteRetornaCodeNULL(){

        //arrange

        when(searchRepository.searchApoderado("12183912-1")).thenReturn(null);

        //Act
        int result = apoderadoService.deleteApoderado("12183912-1");

        //Assert
        assertEquals(ApoderadoService.NULL, result);

    }

    @Test // si invoco deleteApoderado y el apoderado existe entonces se debe eliminar y retornar code OK
    void siInvocoDeleteAPoderadoYElApoderadoExisteRetornaCodeOK(){

        //arrange

        Apoderado apoderado = new Apoderado("12183912-1", "nombre","correo@ubb.cl");

        when(searchRepository.searchApoderado("12183912-1")).thenReturn(apoderado);

        //Act
        int result = apoderadoService.deleteApoderado("12183912-1");

        //Assert
        assertEquals(ApoderadoService.OK, result);

    }

    @Test // si invoco deleteApoderado y el apoderado existe Y tiene alumnos a su cargo algún alumno entonces retornar code TIENE_ALUMNOS
    void siInvocoDeleteAPoderadoYElApoderadoExistePeroTieneAlumnosDebeRetornaCodeTIENE_ALUMNOS(){

        //arrange

        Apoderado apoderado = new Apoderado("12183912-1", "nombre","correo@ubb.cl");
        List<Alumno> alumnoList = new ArrayList<Alumno>();
        Alumno alumno = new Alumno("1912131-1","nombreAl","correoAlumno@ubb.cl");
        alumnoList.add(alumno);
        apoderado.setAlumnos(alumnoList);

        when(searchRepository.searchApoderado("12183912-1")).thenReturn(apoderado);

        //Act
        int result = apoderadoService.deleteApoderado("12183912-1");

        //Assert
        assertEquals(ApoderadoService.TIENE_ALUMNOS, result);

    }


    //test de modifyApoderado(ApoderadoDTO apoderado)

    @Test
    void SiInvocoModifyApoderadoYLosDatosDeEntradaSonIncorrectosEntoncesRetornaraNull(){
        //Arrange

        ApoderadoDTO apoderadoInput = new ApoderadoDTO("1912131", null, "correo@ubb.cl");
        ApoderadoDTO apoderadoInput2 = new ApoderadoDTO(null, "nombre", "correo@ubb.cl");

        when(searchRepository.searchApoderado("1912131")).thenReturn(null);
        
        //Act
        ApoderadoDTO result = apoderadoService.modifyApoderado(apoderadoInput);
        ApoderadoDTO result2 = apoderadoService.modifyApoderado(apoderadoInput2);
        ApoderadoDTO result4 = apoderadoService.modifyApoderado(null);


        //Assert
        assertNull(result);
        assertNull(result2);
        assertNull(result4);
    }

    @Test
    void SiInvocoModifyApoderadoYLosDatosDeEntradaSoncorrectosEntoncesSeActualizaraYRetornaraElObjetoDTO(){
        //Arrange
        
        ApoderadoDTO apoderadoInput = new ApoderadoDTO("1912131", null, "correo@ubb.cl");
        ApoderadoDTO apoderadoInput2 = new ApoderadoDTO("1912131", "nombres", null);
        
        Apoderado apoderado = new Apoderado("1912131", "nombreAntiguo", "correoAntiguo@ubb.cl");

        when(searchRepository.searchApoderado("1912131")).thenReturn(apoderado);

        //Act
        ApoderadoDTO result = apoderadoService.modifyApoderado(apoderadoInput);
        
        
        //Assert
        //para apoderadoInput
        assertEquals(apoderado.getRut(), result.getRut());
        assertEquals(apoderado.getNombre(), result.getNombre());
        assertEquals(apoderadoInput.getCorreo(), result.getCorreo());
        
        //Act
        ApoderadoDTO result2 = apoderadoService.modifyApoderado(apoderadoInput2);
        
        //Assert
        //para apoderadoInput2
        assertEquals(apoderado.getRut(), result2.getRut());
        assertEquals(apoderadoInput2.getNombre(), result2.getNombre());
        assertEquals(apoderado.getCorreo(), result2.getCorreo());
    }

}
