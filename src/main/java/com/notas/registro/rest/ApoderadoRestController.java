package com.notas.registro.rest;

import java.util.List;

import com.notas.registro.DTO.ApoderadoDTO;
import com.notas.registro.service.ApoderadoService;

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
@RequestMapping("apoderados")
public class ApoderadoRestController {

    @Autowired
    private ApoderadoService apoderadoService;

    @GetMapping("")
    public ResponseEntity<List<ApoderadoDTO>> getAllApoderado() {
        List<ApoderadoDTO> apoderadoList = apoderadoService.findApoderados();
        if (!apoderadoList.isEmpty()) {
            return new ResponseEntity<>(apoderadoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/apoderado")
    public ResponseEntity<ApoderadoDTO> apoderado(@RequestParam(value = "rut") String rut) {
        ApoderadoDTO apoderado = apoderadoService.findApoderado(rut);
        if (apoderado != null) {

            return new ResponseEntity<>(apoderado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addApoderado")
    public ResponseEntity<String> addApoderado(@RequestBody ApoderadoDTO apoderadoDTO) {
        
        // validar que los datos necesarios existan
        if (apoderadoDTO.getNombre() != null && apoderadoDTO.getCorreo() != null && apoderadoDTO.getRut() != null) {

            boolean status = apoderadoService.addApoderado(apoderadoDTO);
            if (status) {
                return new ResponseEntity<>("Apoderado creado exitosamente", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Apoderado no creado", HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<>("Ingrese todos los datos del apoderado", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<Void> deleteApoderado(@RequestParam("rut") String rut) {
        int deleted = apoderadoService.deleteApoderado(rut);
        
        if (deleted==ApoderadoService.OK) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<ApoderadoDTO> updateAlumno(@RequestBody ApoderadoDTO apoderadoDTO) {
        ApoderadoDTO mofified = apoderadoService.modifyApoderado(apoderadoDTO);
        if (mofified!=null) {
            return new ResponseEntity<>(mofified, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
