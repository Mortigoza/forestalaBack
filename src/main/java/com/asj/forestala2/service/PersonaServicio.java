package com.asj.forestala2.service;

import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.Usuario;
import com.asj.forestala2.negocio.dto.ActualizarPersonaDTO;
import com.asj.forestala2.negocio.dto.PersonaDTO;

import java.util.List;
import java.util.Optional;

public interface PersonaServicio {

    public Persona updatePersona(int id, Persona persona);
//    public Persona updatePersona2(int id, Persona persona);

    public Optional<Persona> findById(int id);
    public List<Persona> getAll();

    public void deletePersona(int id);

    public Persona crearPersona(Persona persona);

    List<Persona> getAllConPlant();
}
