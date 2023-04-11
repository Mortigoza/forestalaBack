package com.asj.forestala2.service.impl;

import com.asj.forestala2.exception.ErrorProcessException;
import com.asj.forestala2.exception.Excepciones;
import com.asj.forestala2.exception.NotFoundException;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.dto.ActualizarPersonaDTO;
import com.asj.forestala2.negocio.dto.PersonaDTO;
import com.asj.forestala2.negocio.mapper.PersonaMapper;
import com.asj.forestala2.repository.PersonaRepository;
import com.asj.forestala2.service.PersonaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonaServicioImpl implements PersonaServicio {
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;
    private final String ERROR_NOT_FOUND = "An error occurred in the process: ";

    @Transactional
    public Persona updatePersona(int id, ActualizarPersonaDTO actualizarPersonaDTO) throws ErrorProcessException {
        Persona personaActualizada = personaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("The person's id was not found in the database"));

        try {
            personaMapper.actualizarPersonaDtoToEntity(actualizarPersonaDTO);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }

        personaActualizada.setNombre(actualizarPersonaDTO.getNombre());
        personaActualizada.setApellido(actualizarPersonaDTO.getApellido());
        personaActualizada.setCalle(actualizarPersonaDTO.getCalle());
        personaActualizada.setAltura(actualizarPersonaDTO.getAltura());
        personaActualizada.setLocalidad(actualizarPersonaDTO.getLocalidad());
        personaActualizada.setTelContacto(actualizarPersonaDTO.getTelContacto());


        return this.personaRepository.save(personaActualizada);
    }

    @Override
    public PersonaDTO findById(int id) throws ErrorProcessException {
        Persona persona = personaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("The person's id was not found in the database"));
        try {
            return personaMapper.entityToDto(persona);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Transactional
    @Modifying
    public String deletePersona(int id) throws ErrorProcessException{
        try {
            personaRepository.deleteById(id);
            return "El usuario ha sido eliminado con éxito.";
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public Persona crearPersona(PersonaDTO personaDTO) throws ErrorProcessException {

        Persona persona = personaMapper.dtoToEntity(personaDTO);
        try {
            return personaRepository.save(persona);
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }


        //if(bindingResult.hasErrors()){
//            Map<String, String> validaciones = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(v -> validaciones.put(v.getField(), v.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(validaciones);
//        }
//
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            Persona persona = personaMapper.dtoToEntity(personaDTO);
//            Persona personaCreada = personService.crearPersona(persona);
//            return ResponseEntity.status(HttpStatus.CREATED).body(personaCreada);
//        } catch (RuntimeException ex){
//            response.put("sucess", Boolean.FALSE);
//            response.put("mensaje", ex.getMessage());
//            return ResponseEntity.badRequest()
//                    .body(response);
//        }
    }

    @Override
    public List<PersonaDTO> getAllConPlant() throws ErrorProcessException {
        try {
            return personaRepository.findPersonaAndPlantacionParticular().stream()
                    .map(personaMapper::entityToDto)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public List<PersonaDTO> getAll() throws ErrorProcessException {
        try {
            return personaRepository.findAll().stream()
                    .map(personaMapper::entityToDto)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

}
