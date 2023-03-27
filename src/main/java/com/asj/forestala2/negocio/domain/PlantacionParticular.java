package com.asj.forestala2.negocio.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plantaciones_particulares")
public class PlantacionParticular {
    @Id
    @Column(name = "id_plantaciones_particulares")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlantacionesParticulares;

    @Column(name="arbol")
    private boolean arbol;
    @Column(name="arbusto")
    private boolean arbusto;
    @Column(name="enredadera")
    private boolean enredadera;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "id_personas")
    @JsonBackReference
    private Persona persona;
}
