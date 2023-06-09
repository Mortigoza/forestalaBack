package com.asj.forestala2.controller;

import com.asj.forestala2.exception.ErrorProcessException;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.PlantacionParticular;
import com.asj.forestala2.negocio.dto.PersonaDTO;
import com.asj.forestala2.negocio.dto.PlantacionParticularDTO;
import com.asj.forestala2.negocio.mapper.PlantacionesParticularesMapper;
import com.asj.forestala2.service.PersonaServicio;
import com.asj.forestala2.service.PlantacionParticularServicio;
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
@RequestMapping("/plantaciones-particulares")
public class PlantacionParticularController {

    private final PlantacionParticularServicio plantacionParticularServicio;
    private final PlantacionesParticularesMapper plantacionesParticularesMapper;
    private final PersonaServicio personaServicio;

    @GetMapping
    public ResponseEntity<List<PlantacionParticularDTO>> getAllPlantacionesParticulares(){
        List<PlantacionParticular> plantacionesParticulares = this.plantacionParticularServicio.getAll();
        List<PlantacionParticularDTO> plantacionesParticularesDTOS = plantacionesParticulares
                .stream()
                .map(plantacionParticular -> {
                    PlantacionParticularDTO plantacionParticularDTO = plantacionesParticularesMapper.entityToDto(plantacionParticular);

                    return plantacionParticularDTO;
                }).collect(Collectors.toList());


        return ResponseEntity.ok(plantacionesParticularesDTOS);
    }

    @GetMapping("/{id}")
    public PlantacionParticularDTO obtenerPorId(@PathVariable("id") Integer id){
        PlantacionParticular plantacionParticular = plantacionParticularServicio.findById(id);
        PlantacionParticularDTO plantacionParticularDTO = plantacionesParticularesMapper.entityToDto(plantacionParticular);
        plantacionParticularDTO.setCodigo(plantacionParticular.getIdPlantacionesParticulares());
        return plantacionParticularDTO;
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPlantacionParticular(@PathVariable("id") Integer id, @RequestBody PlantacionParticularDTO plantacionParticularDTO){
        try{
            PlantacionParticular plantacionParticular = plantacionesParticularesMapper.dtoToEntity(plantacionParticularDTO);
            PlantacionParticular plantacionParticularActualizada = plantacionParticularServicio.updatePlantacionParticular(id, plantacionParticular);
            return ResponseEntity.status(HttpStatus.OK).body(plantacionParticularActualizada);
        } catch (RuntimeException ex){
            throw ex;
        }

    }

    @PostMapping("/{idPersonas}")
    public ResponseEntity<?> createPlantacionParticular(@Valid @RequestBody PlantacionParticularDTO plantacionParticularDTO, @PathVariable int idPersonas, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String, String> validaciones = new HashMap<>();
            bindingResult.getFieldErrors().forEach(v -> validaciones.put(v.getField(), v.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }

        Map<String, Object> response = new HashMap<>();

        try {
            PersonaDTO personaLogueada = personaServicio.findById(idPersonas);

            PlantacionParticular plantacionParticular = plantacionesParticularesMapper.dtoToEntity(plantacionParticularDTO);
//            plantacionParticular.setPersona(perpersonaLogueada);
            PlantacionParticular plantacionParticularCreada = plantacionParticularServicio.crearPlantacionParticular(plantacionParticular);
            PlantacionParticularDTO plantacionParticularDTO2 = plantacionesParticularesMapper.entityToDto(plantacionParticularCreada);

            return ResponseEntity.status(HttpStatus.CREATED).body(plantacionParticularDTO2);

        } catch (RuntimeException | ErrorProcessException ex){
            response.put("sucess", Boolean.FALSE);
            response.put("mensaje", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") int id){
        this.plantacionParticularServicio.deletePlantacionParticular(id);
        //con esto retorno 204 siempre, para que no se queden esperando
        return ResponseEntity.status(HttpStatus.OK).body("La plantacion particular ha sido eliminada con éxito.");
    }
}
