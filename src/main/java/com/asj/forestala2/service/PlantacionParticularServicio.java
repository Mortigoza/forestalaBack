package com.asj.forestala2.service;

import com.asj.forestala2.exception.ErrorProcessException;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.PlantacionParticular;
import com.asj.forestala2.negocio.dto.PlantacionParticularDTO;

import java.util.List;

public interface PlantacionParticularServicio {

    public void deletePlantacionParticular(int id);

    public List<PlantacionParticularDTO> getAll() throws ErrorProcessException;

    public PlantacionParticularDTO findById(int id) throws ErrorProcessException;

    public PlantacionParticular updatePlantacionParticular(int id, PlantacionParticular plantacionParticular);

    public PlantacionParticular crearPlantacionParticular(PlantacionParticular plantacionParticular);

    boolean existePersona(Persona persona);

}
