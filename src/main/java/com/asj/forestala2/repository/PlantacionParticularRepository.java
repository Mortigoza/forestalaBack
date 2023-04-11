package com.asj.forestala2.repository;

import com.asj.forestala2.negocio.domain.PlantacionParticular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantacionParticularRepository extends JpaRepository<PlantacionParticular, Integer> {
//    public PlantacionParticular findById(int id);

   Optional<PlantacionParticular> findByPersona(int idPersona);
}
