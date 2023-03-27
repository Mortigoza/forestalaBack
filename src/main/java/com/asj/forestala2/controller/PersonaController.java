package com.asj.forestala2.controller;

import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.dto.ActualizarPersonaDTO;
import com.asj.forestala2.negocio.dto.PersonaDTO;
import com.asj.forestala2.negocio.mapper.PersonaMapper;
import com.asj.forestala2.service.PersonaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaServicio personaServicio;
    private final PersonaMapper personaMapper;

    public PersonaController(PersonaServicio personaServicio, PersonaMapper personaMapper) {
        this.personaServicio = personaServicio;
        this.personaMapper = personaMapper;
    }

    @PutMapping ("update/{id}")
    public ResponseEntity<?> actualizarTodo(@PathVariable("id") Integer id, @RequestBody ActualizarPersonaDTO  actualizarPersonaDTO) {

        try {
            Persona persona = personaMapper.actualizarPersonaDtoToEntity(actualizarPersonaDTO);
            Persona personaActualizada = personaServicio.updatePersona(id, persona);

            return ResponseEntity.ok().body(personaActualizada);

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> getAllPersonas(){
        List<Persona> personas = this.personaServicio.getAll();
        List<PersonaDTO> dtos = personas
                .stream()
                //.map(p -> PersonajeMapper.entityToDto(p))
                .map(personaMapper::entityToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/con-plantaciones")
    public List<PersonaDTO> getAllPersonasConPlantaciones(){
        List<Persona> personas = this.personaServicio.getAllConPlant();
        List<PersonaDTO> dtos = personas
                .stream()
                .map(personaMapper::entityToDto)
                .collect(Collectors.toList());
        return dtos;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonasPorId(@PathVariable int id){
        Optional<Persona> personas = this.personaServicio.findById(id);
        PersonaDTO personaDTO = new PersonaDTO();
        if(personas.isPresent()){
            personaDTO = personaMapper.entityToDto(personas.get());

        }
        return ResponseEntity.ok(personaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") Integer id){
        this.personaServicio.deletePersona(id);
        //con esto retorno 204 siempre, para que no se queden esperando
        return ResponseEntity.status(HttpStatus.OK).body("La persona ha sido eliminado con éxito.");
    }

    @PostMapping
    public ResponseEntity<?> createPersona(@RequestBody PersonaDTO personaDTO){

        try {
            Persona persona = personaMapper.dtoToEntity(personaDTO);
            Persona personaCreada = personaServicio.crearPersona(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body(personaCreada);
        } catch (RuntimeException ex){
            throw ex;
        }
    }

}
