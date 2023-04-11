package com.asj.forestala2.negocio.dto;

import com.asj.forestala2.negocio.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCompletoDTO {
    private int idUsuario;
    @Email
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "La cotraseña es obligatoria")
    @Size(min = 8)
    private String contrasenia;

    private Persona persona;
}
