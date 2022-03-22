package com.notas.registro.service;

import java.util.List;

import com.notas.registro.DTO.ApoderadoDTO;
import com.notas.registro.mapper.ApoderadoMapper;
import com.notas.registro.model.Apoderado;
import com.notas.registro.repository.ApoderadoRepository;
import com.notas.registro.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApoderadoService {

    public static final int NULL = 0;
    public static final int TIENE_ALUMNOS = 1;
    public static final int OK = 2;


    @Autowired
    private ApoderadoRepository apoderadoRepository;

    @Autowired
    private SearchRepository searchRepository;

    public List<ApoderadoDTO> findApoderados() {
        return ApoderadoMapper.INSTANCIA.apoderadoListToApoderadoDTOList(apoderadoRepository.findAll());
    }

    

    public ApoderadoDTO findApoderado(String rut){

        Apoderado apoderado = searchRepository.searchApoderado(rut);

        if(apoderado!=null){
            return ApoderadoMapper.INSTANCIA.apoderadoToApoderadoDTO(apoderado);
        }
        return null;
    }

    public boolean addApoderado(ApoderadoDTO apoderadoDTO) {

        if(apoderadoDTO==null) return false;

        Apoderado apoderado = searchRepository.searchApoderado(apoderadoDTO.getRut());

        if (apoderado == null) {

            Apoderado ap = ApoderadoMapper.INSTANCIA.apoderadoDTOToApoderado(apoderadoDTO);

            return apoderadoRepository.saveAndFlush(ap) != null;
        }
        return false;
    }

    public int deleteApoderado(String rut){
        Apoderado apoderado = searchRepository.searchApoderado(rut);

        if(apoderado==null){
            return NULL;
        }
        //si tiene alumnos no se puede eliminar
        if(!apoderado.getAlumnos().isEmpty()){
            return TIENE_ALUMNOS;
        }

        apoderadoRepository.delete(apoderado);
        return OK;
    }

    public ApoderadoDTO modifyApoderado(ApoderadoDTO apoderadoDTO){

        if(apoderadoDTO==null) return null;

        Apoderado apoderado = searchRepository.searchApoderado(apoderadoDTO.getRut());

        if(apoderado==null) return null;

        if(apoderadoDTO.getNombre()!=null){
            apoderado.setNombre(apoderadoDTO.getNombre());
        }

        if(apoderadoDTO.getCorreo()!=null){
            apoderado.setCorreo(apoderadoDTO.getCorreo());
        }

        apoderadoRepository.saveAndFlush(apoderado);

        return ApoderadoMapper.INSTANCIA.apoderadoToApoderadoDTO(apoderado);
    }

}
