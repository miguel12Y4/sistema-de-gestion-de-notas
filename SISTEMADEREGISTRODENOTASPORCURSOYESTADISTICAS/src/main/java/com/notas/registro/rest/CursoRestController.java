package com.notas.registro.rest;

import java.util.List;

import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cursos")
public class CursoRestController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("")
    public ResponseEntity<List<CursoDTO>> getAllCurso() {
        List<CursoDTO> cursoList = cursoService.findCursos();
        if (!cursoList.isEmpty()) {
            return new ResponseEntity<>(cursoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/curso")
    public ResponseEntity<CursoDTO> curso(@RequestParam(value = "id") int id) {
        CursoDTO curso = cursoService.findCurso(id);
        if (curso != null) {

            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCurso")
    public ResponseEntity<String> addCurso(@RequestBody CursoDTO cursoDTO) {

        // validar que los datos existan
        if (cursoDTO.getNombre() != null && cursoDTO.getGrado() != null && cursoDTO.getProfesor() != null
                && cursoDTO.getProfesor().getRut() != null) {

            int id = cursoService.addCurso(cursoDTO);

            if (id > 0) {
                return new ResponseEntity<>("Curso creado exitosamente con id = " + id, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Curso no creado", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Ingrese todos los datos del curso", HttpStatus.BAD_REQUEST);

    }

}
