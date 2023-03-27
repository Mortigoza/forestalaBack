package com.asj.forestala2.negocio.dto;

import com.asj.forestala2.negocio.domain.PlantacionParticular;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

    @Column(name = "id_personas")
    private int idPersonas;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String calle;
    @NotNull
    private String altura;
    @NotNull
    private String localidad;
    @NotNull
    private String telContacto;

    private PlantacionParticular plantacionParticular;
}
