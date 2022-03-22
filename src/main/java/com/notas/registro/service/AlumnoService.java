package com.notas.registro.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import java.math.BigInteger;

import com.notas.registro.DTO.AlumnoDTO;
import com.notas.registro.DTO.DetalleNotas;
import com.notas.registro.DTO.NotaDTO;
import com.notas.registro.mapper.AlumnoMapper;
import com.notas.registro.model.Alumno;
import com.notas.registro.model.Apoderado;
import com.notas.registro.model.Asignatura;
import com.notas.registro.model.Curso;
import com.notas.registro.model.Evaluacion;
import com.notas.registro.model.Rinde;
import com.notas.registro.repository.AlumnoRepository;
import com.notas.registro.repository.ApoderadoRepository;
import com.notas.registro.repository.CursoRepository;
import com.notas.registro.repository.RindeRepository;
import com.notas.registro.repository.SearchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

    public static final int NULL = 0;
    public static final int OK = 2;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ApoderadoRepository apoderadoRepository;

    @Autowired
    private RindeRepository rindeRepository;
    

    public List<AlumnoDTO> findAlumnos() {
        return AlumnoMapper.INSTANCIA.alumnoListToAlumnoDTOList(alumnoRepository.findAll());
    }


    public AlumnoDTO findAlumno(String rut) {
        Alumno alumno = searchRepository.searchAlumno(rut);

        if(alumno!=null){
            return AlumnoMapper.INSTANCIA.alumnoToAlumnoDTO(alumno);
        }
        return null;
    }

    public boolean addAlumno(AlumnoDTO alumnoDTO) {

        if(alumnoDTO==null ) return false;

        //el alumno debe tener un apoderado y curso para ingresarlo al sistema
        if(alumnoDTO.getCurso() == null && alumnoDTO.getApoderado()==null) return false;

        Curso curso = searchRepository.searchCurso(alumnoDTO.getCurso().getId());
        Apoderado apoderado = searchRepository.searchApoderado(alumnoDTO.getApoderado().getRut());
        Alumno alumn = searchRepository.searchAlumno(alumnoDTO.getRut());

        if(curso==null){
            return false;
        }
        
        int asignaturasCerradas = cursoRepository.asignaturasCerradas(curso.getId());

        System.out.println(asignaturasCerradas);

        if(asignaturasCerradas>0){
            //no se pueden agregar alumnos al curso porque ya se cerró una asignatura
            return false;
        }
        
        if (apoderado != null && alumn == null) {
            Alumno alumno = AlumnoMapper.INSTANCIA.alumnoDTOToAlumno(alumnoDTO);
            alumno.setCurso(curso);
            alumno.setApoderado(apoderado);
            
            return alumnoRepository.saveAndFlush(alumno) != null;
        }
        
        return false;
    }
    
    @Transactional
    public int deleteAlumno(String rut){
        Alumno alumno = searchRepository.searchAlumno(rut);
        
        if(alumno==null){
            return NULL;
        }
        
        //remover al alumno del curso o apoderado en el caso de que tenga alguno asociado
        Curso curso=  alumno.getCurso();
        if(curso!=null){
            curso.removeAlumno(alumno);
            cursoRepository.saveAndFlush(curso);
        }
        Apoderado apoderado = alumno.getApoderado();
        if(apoderado!=null){
            apoderado.removeAlumno(alumno);
            apoderadoRepository.saveAndFlush(apoderado);
        }
        
        alumnoRepository.delete(alumno);

		return OK;
    }
    
    public AlumnoDTO modifyAlumno(AlumnoDTO alumnoDTO){

        if(alumnoDTO==null) return null;

        Alumno alumno = searchRepository.searchAlumno(alumnoDTO.getRut());
        
        String nombre = alumnoDTO.getNombre();
        String correo = alumnoDTO.getCorreo();
        
        //validar si ingresó algun apoderado
        Apoderado apoderado=null;
        if(alumnoDTO.getApoderado()!=null){
            String rutApoderado = alumnoDTO.getApoderado().getRut();
            apoderado = searchRepository.searchApoderado(rutApoderado);
        }

        //validar si ingresó algun curso
        Curso curso=null;
        if(alumnoDTO.getCurso()!=null){
            int idCurso = alumnoDTO.getCurso().getId();
            curso = searchRepository.searchCurso(idCurso);
        }
        
        //validar que el alumno sea valido y que haya ingresado al menos un dato para actualizar
        if(alumno==null||(nombre==null && correo==null && apoderado==null
        && curso==null )) {
            return null;
        }
        //actualizar los datos que vienen en el dto
        if(nombre!=null){
            alumno.setNombre(nombre);
        }
        if(correo!=null){
            alumno.setCorreo(correo);
        }
        if(apoderado!=null){
            //quitar el alumno del apoderado anterior
            Apoderado ap = alumno.getApoderado();
            ap.removeAlumno(alumno);

            //agregar nuevo apoderado
            alumno.setApoderado(apoderado);

            //guardar apoderado al que se agregó el alumno y al que se le quitó
            apoderadoRepository.saveAndFlush(apoderado);
            apoderadoRepository.saveAndFlush(ap);
        }
        if(curso!=null){
            //quitar al alumno del curso anterior
            Curso c = alumno.getCurso();
            c.removeAlumno(alumno);

            //agrego el nuevo curso
            alumno.setCurso(curso);

            //guardar curso al que se le agregó el alumno y al que se le quitó el alumno
            cursoRepository.saveAndFlush(curso);
            cursoRepository.saveAndFlush(c);
        }

        //guardar el alumno al que se le modificaron los datos
        return AlumnoMapper.INSTANCIA.alumnoToAlumnoDTO(alumnoRepository.saveAndFlush(alumno));
        
    }

    public DetalleNotas misNotas(int idAsignatura, String rut){
        
        try{
            Alumno alumno = searchRepository.searchAlumno(rut);
            Asignatura asignatura = searchRepository.searchAsignatura(idAsignatura);
    
            List<Evaluacion> evaluaciones = asignatura.getEvaluaciones();
            
            DetalleNotas notas = new DetalleNotas(asignatura.getId(), asignatura.getNombre(), alumno.getRut());
            

            NotaDTO nota;
            for (Evaluacion e : evaluaciones) {

                Rinde rendicion = rindeRepository.findRindeByAlumnoAndEvaluacion(alumno, e);

                if(rendicion != null){
                    nota= new NotaDTO(e.getId(), rendicion.getNota(), e.getPonderacion());
                    
                    notas.addNota(nota);
                }
            }

            return notas;
            
        }catch (Exception e) {
            return null;
        }
    }
    
    public List<DetalleNotas> getLibreta(String rut){
        
        Alumno alumno = searchRepository.searchAlumno(rut);

        if(alumno==null) return new ArrayList<>();

        Curso curso = alumno.getCurso();

        List<Asignatura> asignaturas = curso.getAsignaturas();

        List<DetalleNotas> detalles = new ArrayList<>();

        for(Asignatura as : asignaturas){

            DetalleNotas detalle = misNotas(as.getId(), rut);

            detalles.add(detalle);
        }

        return detalles;

    }

    public double getPromedioByAsignatura(int idAsignatura, String rut){

        
        
        Asignatura asignatura = searchRepository.searchAsignatura(idAsignatura);

        if(Boolean.FALSE.equals(asignatura.getEstaCerrada())){
            return 0;
        }
        
        Alumno alumno = searchRepository.searchAlumno(rut);

        int evaluaciones = asignatura.getEvaluaciones().size();
        
        
        Object[] datos = (Object[]) rindeRepository.getPromedio(alumno.getRut(), asignatura.getId());

        double promedio = (double) datos[0];
        BigInteger evaluacionesRendidas = (BigInteger) datos[2];
        int v = evaluacionesRendidas.intValue();
        
        if(v!=evaluaciones){
            //Faltan evaluaciones por rendir
            return 0;
        }
        
        return promedio;
        
    }
}
