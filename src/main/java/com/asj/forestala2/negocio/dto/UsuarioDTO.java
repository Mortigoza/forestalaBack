package com.asj.forestala2.negocio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private int idUsuario;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Min(value = 8)
    private String contrasenia;

    private int idPersonas;
}
