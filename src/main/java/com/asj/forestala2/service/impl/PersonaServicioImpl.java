package com.asj.forestala2.service.impl;

import com.asj.forestala2.exception.Excepciones;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.repository.PersonaRepository;
import com.asj.forestala2.service.PersonaServicio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServicioImpl implements PersonaServicio {
    private final PersonaRepository personaRepository;

    public PersonaServicioImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Transactional
    public Persona updatePersona(int id, Persona persona) {
        Persona personaActualizada;
        Optional<Persona> optionalPersona = this.personaRepository.findById(id);
        if(optionalPersona.isPresent()){
            personaActualizada = optionalPersona.get();
        } else {
            throw new Excepciones("Persona no encontrada", HttpStatus.NOT_FOUND);
        }

        personaActualizada.setNombre(persona.getNombre());
        personaActualizada.setApellido(persona.getApellido());
        personaActualizada.setCalle(persona.getCalle());
        personaActualizada.setAltura(persona.getAltura());
        personaActualizada.setLocalidad(persona.getLocalidad());
        personaActualizada.setTelContacto(persona.getTelContacto());
        personaActualizada.setPlantacionParticular(persona.getPlantacionParticular());


        return this.personaRepository.save(personaActualizada);
    }

    @Override
    public Optional<Persona> findById(int id) {
        return this.personaRepository.findByIdPersonas(id);
    }

    @Transactional
    @Modifying
    public void deletePersona(int id){
        this.personaRepository.deleteById(id);
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return this.personaRepository.save(persona);
    }

    @Override
    public List<Persona> getAllConPlant() {
        return this.personaRepository.findPersonaAndPlantacionParticular();
    }

    @Override
    public List<Persona> getAll() {
        return this.personaRepository.findAll();
    }

}
