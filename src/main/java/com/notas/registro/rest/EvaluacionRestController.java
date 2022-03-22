package com.notas.registro.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.notas.registro.DTO.EvaluacionDTO;
import com.notas.registro.DTO.RindeDTO;
import com.notas.registro.service.EvaluacionService;

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
@RequestMapping("evaluacion")
public class EvaluacionRestController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping("")
    public ResponseEntity<List<EvaluacionDTO>> getAllEvaluaciones() {
        List<EvaluacionDTO> evalList = evaluacionService.findEvaluaciones();
        if (!evalList.isEmpty()) {
            return new ResponseEntity<>(evalList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @PostMapping("/addEvaluacion")
    public ResponseEntity<String> addEvaluacion(@RequestBody EvaluacionDTO evaluacionDTO, @RequestParam(value = "rut") String rut) {

        // validar que los datos existan
        if (evaluacionDTO !=null && evaluacionDTO.getAsignatura() != null && evaluacionDTO.getDescripcion() != null
                && evaluacionDTO.getPonderacion() > 0 && rut!=null) {

            int status = evaluacionService.addEvaluacion(evaluacionDTO, rut);

            if (status == EvaluacionService.OK) {
                return new ResponseEntity<>("evaluacion creada exitosamente", HttpStatus.OK);
            } else {
                if(status == EvaluacionService.EXCESO_EN_PONDERACION){
                    return new ResponseEntity<>("Error, poderación excede el limite", HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>("evaluacion no creada", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Ingrese todos los datos de la evaluacion", HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/addNotas")
    public ResponseEntity<String> addNotas(@RequestBody RindeDTO[] notas, @RequestParam(value = "rut") String rut) {
        List<RindeDTO> rindeDTOs = new ArrayList<>(Arrays.asList(notas));
        int status = -1;
        if(notas.length>0 && rut!=null){
            status = evaluacionService.addNotas(rindeDTOs, rut);
        }

        if(status==EvaluacionService.OK){
            return new ResponseEntity<>("Notas agregadas correctamente", HttpStatus.OK);
        }
        return new ResponseEntity<>("Notas no se agregaron", HttpStatus.BAD_REQUEST);
    }

}
