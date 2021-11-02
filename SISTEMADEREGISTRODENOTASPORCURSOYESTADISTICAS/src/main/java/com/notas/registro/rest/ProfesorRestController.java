package com.notas.registro.rest;

import java.util.List;

import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.service.ProfesorService;

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
@RequestMapping("profesores")
public class ProfesorRestController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("")
    public ResponseEntity<List<ProfesorDTO>> getAllProfesor() {
        List<ProfesorDTO> profesorList = profesorService.findProfesores();
        if (!profesorList.isEmpty()) {
            return new ResponseEntity<>(profesorList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/profesor")
    public ResponseEntity<ProfesorDTO> profesor(@RequestParam(value = "rut") String rut) {
        ProfesorDTO profesor = profesorService.findProfesor(rut);
        if (profesor != null) {

            return new ResponseEntity<>(profesor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addProfesor")
    public ResponseEntity<String> addProfesor(@RequestBody ProfesorDTO profesorDTO) {

        // validar que los datos existan
        if (profesorDTO.getNombre() != null && profesorDTO.getRut() != null && profesorDTO.getCorreo() != null) {

            boolean status = profesorService.addProfesor(profesorDTO);
            if (status) {
                return new ResponseEntity<>("Profesor creado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Profesor no creado", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Ingrese todos los datos del profesor", HttpStatus.BAD_REQUEST);
    }
}
