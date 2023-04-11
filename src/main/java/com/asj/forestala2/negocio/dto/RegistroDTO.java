package com.asj.forestala2.negocio.dto;

import com.asj.forestala2.negocio.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {
    @NotBlank(message = "El email es obligatorio")
    String email;
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8)
    String contrasenia;
    @NotNull(message = "La persona es obligatoria")
    Persona persona;

}
