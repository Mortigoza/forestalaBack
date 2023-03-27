package com.asj.forestala2.negocio.dto;

import com.asj.forestala2.negocio.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {
    @NotNull
    String email;
    @NotNull
    String contrasenia;
    @NotNull
    Persona persona;

}
