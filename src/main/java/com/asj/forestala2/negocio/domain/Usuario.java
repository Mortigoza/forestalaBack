package com.asj.forestala2.negocio.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @Column(name="email", length = 255, nullable = false)
    private String email;

    @Column(name="contrasenia", length = 32, nullable = false)
    private String contrasenia;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "id_personas")
    private Persona persona;

}
