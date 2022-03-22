package com.notas.registro.rest;

import java.util.List;

import com.notas.registro.DTO.CursoDTO;
import com.notas.registro.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

            int status = cursoService.addCurso(cursoDTO);

            System.out.println("el id es"+status);

            if (status == CursoService.OK) {
                return new ResponseEntity<>("Curso creado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Curso no creado", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Ingrese todos los datos del curso", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<Void> deleteCurso(@RequestParam("id") int id) {
        int deleted = cursoService.deleteCurso(id);
        
        if (deleted==CursoService.OK) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<CursoDTO> updateAlumno(@RequestBody CursoDTO alumnoDTO) {
        CursoDTO mofified = cursoService.modifyCurso(alumnoDTO);
        if (mofified!=null) {
            return new ResponseEntity<>(mofified, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
