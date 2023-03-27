package com.asj.forestala2.repository;

import com.asj.forestala2.negocio.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    public Optional<Usuario> findByIdUsuario(int id);
    public Optional<Usuario> findByEmail(String email);

    public Optional<Usuario> findByEmailAndContrasenia(String email, String contrasenia);
}
