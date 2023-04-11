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
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8)
    private String contrasenia;

    private int idPersonas;
}
