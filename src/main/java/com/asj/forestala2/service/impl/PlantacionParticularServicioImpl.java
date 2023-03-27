package com.asj.forestala2.service.impl;

import com.asj.forestala2.exception.Excepciones;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.PlantacionParticular;
import com.asj.forestala2.repository.PersonaRepository;
import com.asj.forestala2.repository.PlantacionParticularRepository;
import com.asj.forestala2.service.PersonaServicio;
import com.asj.forestala2.service.PlantacionParticularServicio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantacionParticularServicioImpl implements PlantacionParticularServicio {

    private final PlantacionParticularRepository plantacionParticularRepository;
    private final PersonaRepository personaRepository;
    public PlantacionParticularServicioImpl(PlantacionParticularRepository plantacionParticularRepository, PersonaRepository personaRepository) {
        this.plantacionParticularRepository = plantacionParticularRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public void deletePlantacionParticular(int id) {
        Optional<PlantacionParticular> optionalPlantacionParticular = this.plantacionParticularRepository.findById(id);
        if (optionalPlantacionParticular.isEmpty()) {
            throw new Excepciones("Plantacion particular no encontrada", HttpStatus.NOT_FOUND);
        }


        this.plantacionParticularRepository.deleteById(id);
    }

    @Override
    public List<PlantacionParticular> getAll() {
        return this.plantacionParticularRepository.findAll();
    }

    @Override
    public PlantacionParticular findById(int id) {
        return this.plantacionParticularRepository.findByIdPlantacionesParticulares(id);
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
