package com.asj.forestala2.negocio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @Column(name="id_personas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersonas;
    @Column(name="nombre", length = 45, nullable = false)
    private String nombre;

    @Column(name="apellido", length = 45, nullable = false)
    private String apellido;

    @Column(name="calle", length = 45, nullable = false)
    private String calle;

    @Column(name="altura", length = 45, nullable = false)
    private String altura;

    @Column(name="localidad", length = 45, nullable = false)
    private String localidad;

    @Column(name="tel_contacto", length = 45, nullable = false)
    private String telContacto;

    @OneToOne (mappedBy="persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name="plantaciones_particulares",
            joinColumns = @JoinColumn(name="id_personas"))
    @JsonManagedReference
    private PlantacionParticular plantacionParticular;

}
