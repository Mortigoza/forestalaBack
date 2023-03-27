package com.asj.forestala2.negocio.dto;

import com.asj.forestala2.negocio.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCompletoDTO {
    private int idUsuario;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Min(value = 8)
    private String contrasenia;

    private Persona persona;
}
