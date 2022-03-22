package com.notas.registro.rest;

import java.util.List;

import com.notas.registro.DTO.ProfesorDTO;
import com.notas.registro.service.ProfesorService;

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

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<Void> deleteProfesor(@RequestParam("rut") String rut) {
        int deleted = profesorService.deleteProfesor(rut);
        
        if (deleted==ProfesorService.OK) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<ProfesorDTO> updateProfesor(@RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO mofified = profesorService.modifyProfesor(profesorDTO);
        if (mofified!=null) {
            return new ResponseEntity<>(mofified, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/cerrarAsignatura", produces = "application/json")
    public ResponseEntity<String> cerrarAsignatura(@RequestParam("rut") String rut, @RequestParam("idAsignatura") int asignatura) {

        boolean status = profesorService.cerrarAsignatura(rut, asignatura);

        if(status){
            return new ResponseEntity<>("Asignatura cerrada",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
