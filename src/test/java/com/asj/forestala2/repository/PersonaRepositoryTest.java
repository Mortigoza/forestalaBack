package com.asj.forestala2.repository;

import com.asj.forestala2.datos.DatosDummy;
import com.asj.forestala2.negocio.domain.Persona;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
class PersonaRepositoryTest {

    @Autowired
    private PersonaRepository personaRepository;

    @BeforeEach
    void setUp(){
        personaRepository.save(DatosDummy.getPersonaAxel());
        personaRepository.save(DatosDummy.getPersonaDavid());
    }

    @AfterEach
    void tearDown(){
        personaRepository.deleteAll();
    }

    @Test
     void findByIdPersonas() {
        //GIVEN
        int test = 2;

        // WHEN
        Optional<Persona> optionalPersona = personaRepository.findByIdPersonas(2);
        // THEN
        assertThat(optionalPersona.isPresent()).isTrue();

        assertThat(optionalPersona.get().getIdPersonas()).isEqualTo(test);
    }

    @Test
    void findPersonaAndPlantacionParticular() {
        //GIVEN

        //WHEN
        List<Persona> personasYPlant = personaRepository.findPersonaAndPlantacionParticular();

        //THEN
        assertThat(personasYPlant.size()).isEqualTo(0);

        assertThat(personasYPlant.isEmpty()).isTrue();


    }
}