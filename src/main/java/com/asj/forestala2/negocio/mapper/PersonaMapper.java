package com.asj.forestala2.negocio.mapper;

import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.Usuario;
import com.asj.forestala2.negocio.dto.ActualizarPersonaDTO;
import com.asj.forestala2.negocio.dto.PersonaDTO;
import com.asj.forestala2.negocio.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    ActualizarPersonaDTO entityToActualizarDTO(Persona persona);
    Persona actualizarPersonaDtoToEntity( ActualizarPersonaDTO actualizarPersonaDTO);
    PersonaDTO entityToDto(Persona persona);

    Persona dtoToEntity(PersonaDTO personaDTO);

}
