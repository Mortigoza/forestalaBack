package com.asj.forestala2.service.impl;

import com.asj.forestala2.exception.Excepciones;
import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.Usuario;
import com.asj.forestala2.negocio.mapper.UsuarioMapper;
import com.asj.forestala2.repository.PersonaRepository;
import com.asj.forestala2.repository.UsuarioRepository;
import com.asj.forestala2.service.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    private final UsuarioRepository repository;
    private final UsuarioMapper usuarioMapper;
    private final PersonaRepository personaRepository;

    public UsuarioServicioImpl(UsuarioRepository repository, UsuarioMapper usuarioMapper, PersonaRepository personaRepository){
        this.repository = repository;
        this.usuarioMapper = usuarioMapper;
        this.personaRepository = personaRepository;
    }

    public Usuario crearUsuario(Usuario usuario){
        if (existeEmail(usuario.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }

        Persona persona = usuario.getPersona();
        persona.setNombre(usuario.getPersona().getNombre());
        persona.setApellido(usuario.getPersona().getApellido());
        persona.setCalle(usuario.getPersona().getCalle());
        persona.setAltura(usuario.getPersona().getAltura());
        persona.setLocalidad(usuario.getPersona().getLocalidad());
        persona.setTelContacto(usuario.getPersona().getTelContacto());
        persona.setPlantacionParticular(usuario.getPersona().getPlantacionParticular());


        usuario.setPersona(persona);

        return this.repository.save(usuario);
    }

    public List<Usuario> getAll(){
        return this.repository.findAll();
    }

    public Usuario findById(int id){
        Usuario encontrado;
        Optional<Usuario> optionalUsuario = this.repository.findByIdUsuario(id);

        if(optionalUsuario.isPresent()){
            encontrado = optionalUsuario.get();
        } else {
            throw new RuntimeException("El usuario no existe");
        }
        return encontrado;
    }

    @Transactional
    public Usuario updateUsuario(int id, Usuario usuario){

        Usuario usuarioActualizado;
        Optional<Usuario> optionalUsuario = this.repository.findById(id);

        if(optionalUsuario.isPresent()){
            usuarioActualizado = optionalUsuario.get();
        } else {
            throw new RuntimeException("El usuario con id " + id + " no existe");
        }

        usuarioActualizado.setEmail(usuario.getEmail());
        usuarioActualizado.setContrasenia(usuario.getContrasenia());
        usuarioActualizado.setPersona(usuario.getPersona());


        return this.repository.save(usuarioActualizado);

    }

    @Transactional
    public void deleteUsuario(int id){
        Optional<Usuario> optionalUsuario = this.repository.findById(id);
        if (optionalUsuario.isEmpty()){
            throw new Excepciones("Usuario no encontrado", HttpStatus.NOT_FOUND);
        } else{
            Usuario usuario = optionalUsuario.get();

            this.repository.delete(usuario);
        }

    }

    @Override
    public boolean existeEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    public Optional<Usuario> validarUsuario(Usuario u, Optional<Usuario> usuario){
        Optional<Usuario> aValidar = this.repository.findByEmailAndContrasenia(u.getEmail(), u.getContrasenia());
        return aValidar;
    }
}
