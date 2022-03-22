package com.notas.registro.rest;

import java.util.List;

import com.notas.registro.DTO.AlumnoDTO;
import com.notas.registro.DTO.DetalleNotas;
import com.notas.registro.service.AlumnoService;

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
@RequestMapping("alumnos")
public class AlumnoRestController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("")
    public ResponseEntity<List<AlumnoDTO>> getAllAlumno() {
        List<AlumnoDTO> alumnoList = alumnoService.findAlumnos();
        if (!alumnoList.isEmpty()) {
            return new ResponseEntity<>(alumnoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/alumno")
    public ResponseEntity<AlumnoDTO> alumno(@RequestParam(value = "rut") String rut) {
        AlumnoDTO alumno = alumnoService.findAlumno(rut);
        if (alumno != null) {

            return new ResponseEntity<>(alumno, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addAlumno")
    public ResponseEntity<String> addAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        
        //valido que todos los datos necesarios existan
        if (alumnoDTO.getRut() != null && alumnoDTO.getNombre() != null && alumnoDTO.getCorreo() != null
                && alumnoDTO.getApoderado() != null && alumnoDTO.getApoderado().getRut() != null
                && alumnoDTO.getCurso() != null && alumnoDTO.getCurso().getId() > 0) {


            boolean status = alumnoService.addAlumno(alumnoDTO);
            if (status) {
                return new ResponseEntity<>("Alumno creado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Alumno no creado", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Ingrese todos los datos del alumno", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<Void> deleteAlumno(@RequestParam("rut") String rut) {
        int deleted = alumnoService.deleteAlumno(rut);
        if (deleted==AlumnoService.OK) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<AlumnoDTO> updateAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        AlumnoDTO mofified = alumnoService.modifyAlumno(alumnoDTO);
        if (mofified!=null) {
            return new ResponseEntity<>(mofified, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getNotas")
    public ResponseEntity<DetalleNotas> getNotas(@RequestParam(value = "rut") String rut, @RequestParam(value = "idAsignatura") int id) {
        
        DetalleNotas data = alumnoService.misNotas(id, rut);
        if (data != null) {

            return new ResponseEntity<>(data, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getLibreta")
    public ResponseEntity<List<DetalleNotas>> getNotas(@RequestParam(value = "rut") String rut) {

        List<DetalleNotas> notas = alumnoService.getLibreta(rut);

        if(!notas.isEmpty()){
            return new ResponseEntity<>(notas, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getPromedioByAsignatura")
    public ResponseEntity<String> getPromedioByAsignatura(@RequestParam(value = "rut") String rut, @RequestParam(value = "idAsignatura") int idAsignatura) {
        
        double promedio = 0;
        try {
            promedio = alumnoService.getPromedioByAsignatura(idAsignatura, rut);
        } catch (Exception e) {

            return new ResponseEntity<>("No se puede calcular", HttpStatus.OK);
        }
        
        return new ResponseEntity<>(promedio+"", HttpStatus.OK);
    }
}
