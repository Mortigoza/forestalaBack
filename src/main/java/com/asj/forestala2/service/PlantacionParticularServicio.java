package com.asj.forestala2.service;

import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.PlantacionParticular;

import java.util.List;

public interface PlantacionParticularServicio {

    public void deletePlantacionParticular(int id);

    public List<PlantacionParticular> getAll();

    public PlantacionParticular findById(int id);

    public PlantacionParticular updatePlantacionParticular(int id, PlantacionParticular plantacionParticular);

    public PlantacionParticular crearPlantacionParticular(PlantacionParticular plantacionParticular);

    boolean existePersona(Persona persona);

}
