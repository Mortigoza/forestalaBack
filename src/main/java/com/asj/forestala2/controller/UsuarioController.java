package com.asj.forestala2.controller;

import com.asj.forestala2.negocio.domain.Usuario;
import com.asj.forestala2.negocio.dto.RegistroDTO;
import com.asj.forestala2.negocio.dto.UsuarioCompletoDTO;
import com.asj.forestala2.negocio.dto.UsuarioDTO;
import com.asj.forestala2.negocio.mapper.UsuarioMapper;
import com.asj.forestala2.service.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioServicio usuarioServicio;
    private final UsuarioMapper usuarioMapper;



    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody RegistroDTO registroDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            Map<String, String> validaciones = new HashMap<>();
            bindingResult.getFieldErrors().forEach(v -> validaciones.put(v.getField(), v.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }


        Map<String, Object> response = new HashMap<>();

        try {
            Usuario usuario = usuarioMapper.registroDtoToEntity(registroDTO);
            Usuario creado = usuarioServicio.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (RuntimeException ex){
            response.put("sucess", Boolean.FALSE);
            response.put("mensaje", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body(response);
        }
    }

    @GetMapping
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

    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable("id") Integer id){
        Usuario usuario = usuarioServicio.findById(id);
        UsuarioDTO usuarioDTO = usuarioMapper.entityToDto(usuario);
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setIdPersonas(usuario.getPersona().getIdPersonas());
        return usuarioDTO;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioDTO usuarioDTO){
        try{
            Usuario usuario = usuarioMapper.dtoToEntity(usuarioDTO);
            Usuario actualizado = usuarioServicio.updateUsuario(id, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(actualizado);
        } catch (RuntimeException ex){
            throw ex;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable("id") Integer id){
        this.usuarioServicio.deleteUsuario(id);
        //con esto retorno 204 siempre, para que no se queden esperando
        return ResponseEntity.status(HttpStatus.OK).body("El usuario ha sido eliminado con éxito.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result){

        if(result.hasErrors()){
            Map<String, String> validaciones = new HashMap<>();
            result.getFieldErrors().forEach(v -> validaciones.put(v.getField(), v.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }


        Map<String, Object> response = new HashMap<>();

        Usuario usuario = usuarioMapper.dtoToEntity(usuarioDTO);

        try {
            Optional<Usuario> validado = this.usuarioServicio.validarUsuario(usuario, Optional.empty());
            if (validado.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(validado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Las credenciales son incorrectas.");
            }
        } catch (RuntimeException ex){
            response.put("sucess", Boolean.FALSE);
            response.put("mensaje", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body(response);
        }




    }
}
