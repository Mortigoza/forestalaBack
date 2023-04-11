package com.asj.forestala2.service.impl;

import com.asj.forestala2.exception.ErrorProcessException;
import com.asj.forestala2.exception.Excepciones;
import com.asj.forestala2.exception.NotFoundException;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.PlantacionParticular;
import com.asj.forestala2.negocio.dto.PlantacionParticularDTO;
import com.asj.forestala2.negocio.mapper.PlantacionesParticularesMapper;
import com.asj.forestala2.repository.PersonaRepository;
import com.asj.forestala2.repository.PlantacionParticularRepository;
import com.asj.forestala2.service.PlantacionParticularServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlantacionParticularServicioImpl implements PlantacionParticularServicio {

    private final PlantacionParticularRepository plantacionParticularRepository;
    private final PersonaRepository personaRepository;
    private final PlantacionesParticularesMapper plantacionesParticularesMapper;

    private final String ERROR_NOT_FOUND = "An error occurred in the process: ";

    @Override
    public void deletePlantacionParticular(int id) {
        Optional<PlantacionParticular> optionalPlantacionParticular = this.plantacionParticularRepository.findById(id);
        if (optionalPlantacionParticular.isEmpty()) {
            throw new Excepciones("Plantacion particular no encontrada", HttpStatus.NOT_FOUND);
        }


        this.plantacionParticularRepository.deleteById(id);
    }

    @Override
    public List<PlantacionParticularDTO> getAll() throws ErrorProcessException {

        try {
            return plantacionParticularRepository.findAll().stream()
                    .map(plantacionesParticularesMapper::entityToDto)
                    .collect(Collectors.toList());
        } catch (RuntimeException e){
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
    }

    @Override
    public PlantacionParticularDTO findById(int id) throws ErrorProcessException{
        PlantacionParticular plantacionParticular = plantacionParticularRepository.findById(id).orElseThrow(()->
                new NotFoundException("The particular plantation was not found in the database."));

        try {
            return plantacionesParticularesMapper.entityToDto(plantacionParticular);
        } catch (RuntimeException e){
            throw new ErrorProcessException(ERROR_NOT_FOUND + e.getMessage());
        }
        //        PlantacionParticular plantacionParticular = plantacionParticularServicio.findById(id);
//        PlantacionParticularDTO plantacionParticularDTO = plantacionesParticularesMapper.entityToDto(plantacionParticular);
//        plantacionParticularDTO.setCodigo(plantacionParticular.getIdPlantacionesParticulares());
//        return plantacionParticularDTO;
    }

    @Override
    public PlantacionParticular updatePlantacionParticular(int id, PlantacionParticular plantacionParticular) {
        PlantacionParticular plantacionParticularActualizada;
        Optional<PlantacionParticular> optionalPlantacionParticular = this.plantacionParticularRepository.findById(id);

        if (optionalPlantacionParticular.isPresent()) {
            plantacionParticularActualizada = optionalPlantacionParticular.get();
        } else {
            throw new RuntimeException("La plantacion particular con id " + id + " no existe");
        }

        plantacionParticularActualizada.setArbol(plantacionParticular.isArbol());
        plantacionParticularActualizada.setArbusto(plantacionParticular.isArbusto());
        plantacionParticularActualizada.setEnredadera(plantacionParticular.isEnredadera());

        return this.plantacionParticularRepository.save(plantacionParticularActualizada);
    }

    @Override
    public PlantacionParticular crearPlantacionParticular(PlantacionParticular plantacionParticular) {
        return this.plantacionParticularRepository.save(plantacionParticular);
    }

    @Override
    public boolean existePersona(Persona persona) {
        return plantacionParticularRepository.findByPersona(persona.getIdPersonas()).isPresent();
    }


}
