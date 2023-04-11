package com.asj.forestala2.service.impl;

import com.asj.forestala2.datos.DatosDummy;
import com.asj.forestala2.exception.ErrorProcessException;
import com.asj.forestala2.exception.Excepciones;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.dto.PersonaDTO;
import com.asj.forestala2.repository.PersonaRepository;
import com.asj.forestala2.service.PersonaServicio;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonaServicioImplTest {

    @MockBean
    private PersonaRepository repository;
    @Autowired
    private PersonaServicio servicio;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById()  throws ErrorProcessException {
        //GIVEN
        Persona persona = DatosDummy.getPersonaAxel();

        //WHEN
        servicio.findById(persona.getIdPersonas());

        //THEN

        assertThat(persona.getIdPersonas()).isEqualTo(0);

        verify(repository).findById(persona.getIdPersonas());

    }


    @Test
    void deletePersona() {
        //GIVEN
        Persona persona = DatosDummy.getPersonaDavid();

        //WHEN
        servicio.deletePersona(persona.getIdPersonas());

        //THEN

        assertThat(persona.getIdPersonas()==2);

        verify(repository).deleteById(persona.getIdPersonas());

       }

    @Test
    void getAll() throws ErrorProcessException{
        //GIVEN
        when(repository.findAll())
                .thenReturn(Arrays.asList(DatosDummy.getPersonaAxel(),
                        DatosDummy.getPersonaDavid()));

        //WHEN
        List<PersonaDTO> personas = servicio.getAll();

        //THEN
        assertThat(personas.size()).isEqualTo(2);

        assertThat(personas.isEmpty()).isFalse();

        verify(repository).findAll();
    }

//    @Test
//    void crearPersona() {
//        //GIVEN
//        Persona persona = DatosDummy.getPersonaDavid();
//
//        //WHEN
//        servicio.crearPersona(persona);
//
//        //THEN
//
//        assertThat(persona.getNombre().equals("David"));
//
//        verify(repository).save(persona);
//
//    }


//    @Test
//    void updatePersona() {
//        //GIVEN
//        int id = 1;
//        Persona persona = DatosDummy.getPersonaDavid();
//        Persona personaActualizada = new Persona(1, "David", "Villareal", "lalala", "667", "Lomas", "99999999", null);
//
//
//        //WHEN
//        when(repository.findById(id)).thenReturn(Optional.of(persona));
//        when(repository.save(persona)).thenReturn(personaActualizada);
//
//        //THEN
//        Persona personaActualizadaObtenida = servicio.updatePersona(id, personaActualizada);
//
//        assertEquals(personaActualizada, personaActualizadaObtenida);
//
//        verify(repository, times(1)).findById(1);
//
//    }
//
//    @Test
//    void updatePersonaConExcepcion(){
//        //GIVEN
//        Persona persona = DatosDummy.getPersonaDavid();
//
//        given(repository.findById(persona.getIdPersonas()))
//                .willReturn(Optional.of(persona));
//
//        //WHEN
//        //THEN
//        assertThatThrownBy(() -> servicio.updatePersona(1, persona))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessageContaining("Persona no encontrada");
//    }


//    @Test
//    void getAllConPlant() {
//        //GIVEN
//        when(repository.findAll())
//                .thenReturn(Arrays.asList(DatosDummy.getPersonaAxel(),
//                        DatosDummy.getPersonaDavid()));
//
//        //WHEN
//        List<Persona> personas = servicio.getAllConPlant();
//
//        //THEN
//        assertThat(personas.size()).isEqualTo(0);
//
//        assertThat(personas.isEmpty()).isTrue();
//
//        verify(repository).findPersonaAndPlantacionParticular();
//    }
}