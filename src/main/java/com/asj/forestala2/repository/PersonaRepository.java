package com.asj.forestala2.repository;

import com.asj.forestala2.negocio.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    @Query("select distinct p from Persona p join fetch p.plantacionParticular")
    List<Persona> findPersonaAndPlantacionParticular();
    Optional<Persona>  findByIdPersonas(int id);

}
