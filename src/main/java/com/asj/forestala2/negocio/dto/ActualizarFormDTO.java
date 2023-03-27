package com.asj.forestala2.negocio.dto;

import com.asj.forestala2.negocio.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarFormDTO {
    @NotNull
    String email;
    @NotNull
    String contrasenia;
    @NotNull
    Persona persona;
}
