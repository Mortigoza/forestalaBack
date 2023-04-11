package com.asj.forestala2.service;

import com.asj.forestala2.exception.ErrorProcessException;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.dto.ActualizarPersonaDTO;
import com.asj.forestala2.negocio.dto.PersonaDTO;

import java.util.List;
import java.util.Optional;

public interface PersonaServicio {

   Persona updatePersona(int id, ActualizarPersonaDTO actualizarPersonaDTO) throws ErrorProcessException;
//    public Persona updatePersona2(int id, Persona persona);

    PersonaDTO findById(int id) throws ErrorProcessException;
    List<PersonaDTO> getAll() throws ErrorProcessException;

    String deletePersona(int id) throws ErrorProcessException;

    Persona crearPersona(PersonaDTO personaDTO) throws ErrorProcessException;

    List<PersonaDTO> getAllConPlant() throws ErrorProcessException;
}
