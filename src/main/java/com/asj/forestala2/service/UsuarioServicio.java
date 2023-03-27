package com.asj.forestala2.service;

import com.asj.forestala2.negocio.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    public Usuario crearUsuario(Usuario usuario);
    public List<Usuario> getAll();
    public Usuario findById(int id);
    public Usuario updateUsuario(int id, Usuario usuario);
    public void deleteUsuario(int id);

    boolean existeEmail(String email);

    public Optional<Usuario> validarUsuario(Usuario u, Optional<Usuario> usuario);
}
