package com.asj.forestala2.negocio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarPersonaDTO {

    private String nombre;

    private String apellido;

    private String calle;

    private String altura;

    private String localidad;

    private String telContacto;
}
