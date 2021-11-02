package com.notas.registro.mapper;

import com.notas.registro.DTO.ApoderadoDTO;
import com.notas.registro.model.Apoderado;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-02T11:46:49-0300",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 11.0.12 (Debian)"
)
public class ApoderadoMapperImpl implements ApoderadoMapper {

    @Override
    public List<ApoderadoDTO> apoderadoListToApoderadoDTOList(List<Apoderado> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<ApoderadoDTO> list = new ArrayList<ApoderadoDTO>( entidadList.size() );
        for ( Apoderado apoderado : entidadList ) {
            list.add( apoderadoToApoderadoDTO( apoderado ) );
        }

        return list;
    }

    @Override
    public ApoderadoDTO apoderadoToApoderadoDTO(Apoderado apoderado) {
        if ( apoderado == null ) {
            return null;
        }

        String rut = null;
        String nombre = null;
        String correo = null;

        rut = apoderado.getRut();
        nombre = apoderado.getNombre();
        correo = apoderado.getCorreo();

        ApoderadoDTO apoderadoDTO = new ApoderadoDTO( rut, nombre, correo );

        return apoderadoDTO;
    }

    @Override
    public List<Apoderado> apoderadoDTOListToApoderadoList(List<ApoderadoDTO> entidadList) {
        if ( entidadList == null ) {
            return null;
        }

        List<Apoderado> list = new ArrayList<Apoderado>( entidadList.size() );
        for ( ApoderadoDTO apoderadoDTO : entidadList ) {
            list.add( apoderadoDTOToApoderado( apoderadoDTO ) );
        }

        return list;
    }

    @Override
    public Apoderado apoderadoDTOToApoderado(ApoderadoDTO apoderadoDTO) {
        if ( apoderadoDTO == null ) {
            return null;
        }

        Apoderado apoderado = new Apoderado();

        apoderado.setRut( apoderadoDTO.getRut() );
        apoderado.setNombre( apoderadoDTO.getNombre() );
        apoderado.setCorreo( apoderadoDTO.getCorreo() );

        return apoderado;
    }
}
