package com.asj.forestala2.controller;

import com.asj.forestala2.negocio.domain.Persona;
import com.asj.forestala2.negocio.domain.Usuario;
import com.asj.forestala2.negocio.dto.ActualizarFormDTO;
import com.asj.forestala2.negocio.dto.RegistroDTO;
import com.asj.forestala2.negocio.dto.UsuarioCompletoDTO;
import com.asj.forestala2.negocio.dto.UsuarioDTO;
import com.asj.forestala2.negocio.mapper.UsuarioMapper;
import com.asj.forestala2.service.PersonaServicio;
import com.asj.forestala2.service.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioServicio usuarioServicio;
    private final UsuarioMapper usuarioMapper;
    private final PersonaServicio personaServicio;


    public UsuarioController(UsuarioServicio usuarioServicio, UsuarioMapper usuarioMapper, PersonaServicio personaServicio) {
        this.usuarioServicio = usuarioServicio;
        this.usuarioMapper = usuarioMapper;
        this.personaServicio = personaServicio;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody RegistroDTO registroDTO){

        try {
            Usuario usuario = usuarioMapper.registroDtoToEntity(registroDTO);
            Usuario creado = usuarioServicio.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (RuntimeException ex){
            throw ex;
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios(){
        List<Usuario> usuarios =this.usuarioServicio.getAll();
        List<UsuarioDTO> usuariosDTO = usuarios
                .stream()
                .map(usuario -> {

                    UsuarioDTO usuarioDTO = usuarioMapper.entityToDto(usuario);
                    usuarioDTO.setIdUsuario(usuario.getIdUsuario());
                    usuarioDTO.setIdPersonas(usuario.getPersona().getIdPersonas());
                    return usuarioDTO;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable("id") Integer id){
        Usuario usuario = usuarioServicio.findById(id);
        UsuarioDTO usuarioDTO = usuarioMapper.entityToDto(usuario);
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setIdPersonas(usuario.getPersona().getIdPersonas());
        return usuarioDTO;
    }

    @GetMapping("/usuario-form/{id}")
    public UsuarioCompletoDTO obtenerCompletoPorId(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioServicio.findById(id);
        UsuarioCompletoDTO usuarioCompletoDTO = usuarioMapper.entityToDTOCompleto(usuario);
        usuarioCompletoDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioCompletoDTO.setPersona(usuario.getPersona());
        return usuarioCompletoDTO;
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioDTO usuarioDTO){
        try{
            Usuario usuario = usuarioMapper.dtoToEntity(usuarioDTO);
            Usuario actualizado = usuarioServicio.updateUsuario(id, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(actualizado);
        } catch (RuntimeException ex){
            throw ex;
        }

    }

    @PutMapping("/update-form/{id}")
    public ResponseEntity<?> actualizarForm(@PathVariable("id") Integer id, @RequestBody ActualizarFormDTO actualizarFormDTO){
        try {
            Usuario usuario = usuarioMapper.actualizarDtoToEntity(actualizarFormDTO);
            Usuario actualizado =usuarioServicio.updateUsuario(id, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(actualizado);

            } catch (RuntimeException ex){
                throw ex;
            }
    }

    @DeleteMapping("borrar/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable("id") Integer id){
        this.usuarioServicio.deleteUsuario(id);
        //con esto retorno 204 siempre, para que no se queden esperando
        return ResponseEntity.status(HttpStatus.OK).body("El usuario ha sido eliminado con éxito.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario){
        Optional<Usuario> validado = this.usuarioServicio.validarUsuario(usuario, Optional.empty());
        if (validado.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(validado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Las credenciales son incorrectas.");
        }

    }
}
