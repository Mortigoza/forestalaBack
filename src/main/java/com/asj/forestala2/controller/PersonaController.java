package com.asj.forestala2.controller;

import com.asj.forestala2.exception.ErrorProcessException;
import com.asj.forestala2.exception.ErrorResponse;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.dto.ActualizarPersonaDTO;
import com.asj.forestala2.negocio.dto.PersonaDTO;
import com.asj.forestala2.negocio.mapper.PersonaMapper;
import com.asj.forestala2.service.PersonaServicio;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/personas")
public class PersonaController {

    private final PersonaServicio personService;

    @PutMapping ("/{id}")
    @ApiOperation(value = "Update person", notes = "Returns response type person")
    public ResponseEntity<?> updatePerson(@PathVariable("id") Integer id,
                                            @RequestBody ActualizarPersonaDTO  actualizarPersonaDTO) throws ErrorProcessException {

        return ResponseEntity.ok().body(personService.updatePersona(id, actualizarPersonaDTO));
    }

    @GetMapping
    @ApiOperation(value = "Get all people", notes = "Returns a list of response")
    public ResponseEntity<List<?>> getAllPerson() throws ErrorProcessException {
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/con-plantaciones")
    @ApiOperation(value = "Get all people with plantations", notes = "Returns a list of response")
    public List<?> getAllPersonasConPlantaciones() throws ErrorProcessException{
        return personService.getAllConPlant();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "get person by id", notes = "Returns response type person")
    public ResponseEntity<?> getPersonById(@PathVariable int id) throws ErrorProcessException {
        return ResponseEntity.ok(personService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete person by id", notes = "Returns response type person")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Integer id) throws ErrorProcessException{
        return ResponseEntity.status(HttpStatus.OK).body(personService.deletePersona(id));
    }

    @PostMapping
    @ApiOperation(value = "create person", notes = "Returns response type person")
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonaDTO personaDTO) throws ErrorProcessException {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.crearPersona(personaDTO));

    }

}
