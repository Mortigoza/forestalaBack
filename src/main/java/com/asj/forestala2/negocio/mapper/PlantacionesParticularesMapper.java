package com.asj.forestala2.negocio.mapper;

import com.asj.forestala2.negocio.domain.PlantacionParticular;
import com.asj.forestala2.negocio.dto.PlantacionParticularDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlantacionesParticularesMapper {
    @Mapping(source = "idPlantacionesParticulares", target = "codigo")
    PlantacionParticularDTO entityToDto(PlantacionParticular plantacionParticular);

    PlantacionParticular dtoToEntity(PlantacionParticularDTO plantacionParticularDTO);
}
