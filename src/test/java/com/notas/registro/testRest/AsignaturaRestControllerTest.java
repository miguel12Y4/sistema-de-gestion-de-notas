package com.notas.registro.testRest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.notas.registro.DTO.AsignaturaDTO;
import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.service.AsignaturaService;
import com.notas.registro.rest.AsignaturaRestController;

@ExtendWith(MockitoExtension.class)
public class AsignaturaRestControllerTest {
	private JacksonTester<AsignaturaDTO> jsonAsignatura;
    private MockMvc mockMvc;
    
    @Mock
    private AsignaturaService AsignaturaService;
    @InjectMocks
    private AsignaturaRestController AsignaturaRestController;

    @BeforeEach
    void setup() {
        JacksonTester.initFields(this,new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(AsignaturaRestController).build();
    }
     @Test
    void siInvocoFindAsignaturaConUnIdYExisteUnaAsignaturaConEseIdDebeDevolverAsignaturaDTOYStatusOk() throws Exception {
        // Given
        AsignaturaDTO AsignaturaDTO = getAsignaturaDTO();
        given(AsignaturaService.findAsignatura(1)).willReturn(AsignaturaDTO);

        // When
        MockHttpServletResponse response = mockMvc.perform(get("/asignaturas/asignatura/?id=1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals(jsonAsignatura.write(AsignaturaDTO).getJson(),response.getContentAsString());
    }
    
    @Test
    void siInvocoFindAsignaturaConUnIdYNoExisteUnaAsignaturaConEseIdDebeDevolverStatusNotFound() throws Exception {
    	// Given
        given(AsignaturaService.findAsignatura(1)).willReturn(null);

        // When
        MockHttpServletResponse response = mockMvc.perform(get("/asignaturas/asignatura/?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
    }
    
    @Test
    void siInvocoCreateAsignaturaYLosDatosNoSonValidosDebeDevolverStatusBadRequest() throws Exception {
    	AsignaturaDTO AsignaturaDTO = getAsignaturaDTO();
    	given(AsignaturaService.addAsignatura(any(AsignaturaDTO.class))).willReturn(-1);
    	
    	 // When
    	MockHttpServletResponse response = mockMvc.perform(post("/asignaturas/addAsignatura")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAsignatura.write(AsignaturaDTO).getJson())
                .accept(MediaType.APPLICATION_JSON))
        .andReturn()
        .getResponse();

        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }
    
    @Test
    void siInvocoCreateAsignaturaYLosDatosSonValidosDebeDevolverAsignaturaDTOYStatusOK() throws IOException, Exception {
    	// Given
        AsignaturaDTO AsignaturaDTO = getAsignaturaDTO();
        AsignaturaDTO AsignaturaDTO1 = getAsignaturaDTO();
        AsignaturaDTO.setId(0);
        given(AsignaturaService.addAsignatura(any(AsignaturaDTO.class))).willReturn(1);

        // When
        MockHttpServletResponse response = mockMvc.perform(post("/asignaturas/addAsignatura")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAsignatura.write(AsignaturaDTO1).getJson())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        assertEquals("Asignatura creado exitosamente con id=1",response.getContentAsString());
    }
    
    @Test
    void siInvocoDeleteAsignaturaYNoExisteLaAsignaturaDebeDevolverStatusBadRequest() throws Exception {
    	// Given
    	given(AsignaturaService.deleteAsignatura(1)).willReturn(com.notas.registro.service.AsignaturaService.NULL);
    	
    	// When
        MockHttpServletResponse response = mockMvc.perform(delete("/asignaturas/delete/?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }
    
    @Test
    void siInvocoDeleteAsignaturaYExisteLaAsignaturaDebeDevolverStatusOK() throws Exception {
    	// Given
    	given(AsignaturaService.deleteAsignatura(1)).willReturn(com.notas.registro.service.AsignaturaService.OK);
    	
    	// When
        MockHttpServletResponse response = mockMvc.perform(delete("/asignaturas/delete/?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        // Then
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    
    private AsignaturaDTO getAsignaturaDTO() {
    	AsignaturaDTO AsignaturaDTO = new AsignaturaDTO();
    	AsignaturaDTO.setId(1);
		AsignaturaDTO.setNombre("Matematicas");
		AsignaturaDTO.setCurso(new CursoDTO(3,"Cuarto","4"));
		AsignaturaDTO.setProfesor(new ProfesorDTO("10.567.764-1","Juan Ribera","juan@gmail.com"));
		return AsignaturaDTO;
    }
    
    
}
