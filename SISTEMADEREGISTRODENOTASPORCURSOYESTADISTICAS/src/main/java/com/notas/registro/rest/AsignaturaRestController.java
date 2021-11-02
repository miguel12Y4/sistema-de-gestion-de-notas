package com.notas.registro.rest;

import java.util.List;

import com.notas.registro.DTO.AsignaturaDTO;
import com.notas.registro.service.AsignaturaService;

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
@RequestMapping("asignaturas")
public class AsignaturaRestController {

    @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping("")
    public ResponseEntity<List<AsignaturaDTO>> getAllAsignatura() {
        List<AsignaturaDTO> asignaturaList = asignaturaService.findAsignaturas();
        if (!asignaturaList.isEmpty()) {
            return new ResponseEntity<>(asignaturaList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/asignatura")
    public ResponseEntity<AsignaturaDTO> asignatura(@RequestParam(value = "id") int id) {
        AsignaturaDTO asignatura = asignaturaService.findAsignatura(id);
        if (asignatura != null) {

            return new ResponseEntity<>(asignatura, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addAsignatura")
    public ResponseEntity<String> addAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {

        // validar que los datos existan
        if (asignaturaDTO.getNombre() != null && asignaturaDTO.getCurso() != null
                && asignaturaDTO.getCurso().getId() > 0 && asignaturaDTO.getProfesor() != null
                && asignaturaDTO.getProfesor().getRut() != null) {

            int id = asignaturaService.addAsignatura(asignaturaDTO);

            if (id > 0) {
                return new ResponseEntity<>("Asignatura creado exitosamente con id=" + id, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Asignatura no creada", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("ingrese todos los datos de la asignatura", HttpStatus.BAD_REQUEST);
    }

}