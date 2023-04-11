package com.asj.forestala2.negocio.dto;

import com.asj.forestala2.negocio.domain.PlantacionParticular;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

    @Column(name = "id_personas")
    private int idPersonas;
    @NotBlank(message = "El nonbre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "La calle es obligatoria")
    private String calle;
    @NotBlank(message = "La altura es obligatoria")
    private String altura;
    @NotBlank(message = "La localidad es obligatoria")
    private String localidad;
    @NotBlank(message = "El teléfono de contacto es obligatorio")
    private String telContacto;

    private PlantacionParticular plantacionParticular;
}
