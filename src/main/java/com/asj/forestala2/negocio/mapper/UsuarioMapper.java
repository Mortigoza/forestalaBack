package com.asj.forestala2.negocio.mapper;

import com.asj.forestala2.negocio.domain.Usuario;
import com.asj.forestala2.negocio.dto.RegistroDTO;
import com.asj.forestala2.negocio.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO entityToDto(Usuario usuario);

    Usuario dtoToEntity(UsuarioDTO usuarioDTO);

    Usuario registroDtoToEntity(RegistroDTO registroDTO);


}