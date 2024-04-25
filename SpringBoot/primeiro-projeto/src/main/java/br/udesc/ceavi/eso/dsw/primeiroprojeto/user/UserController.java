package br.udesc.ceavi.eso.dsw.primeiroprojeto.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UsuarioResource usuarioResource;

    @PostMapping("/users")
    public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody String name) {
        try {
            Usuario u = new Usuario(name);
            usuarioResource.criarUsuario(u);
            return ResponseEntity.status(HttpStatus.CREATED).body(u);
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        try {
            return usuarioResource.listAll();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Usuario> listarUmUsuario(@PathVariable Integer id) {
        try {
            ResponseEntity<Usuario> user = usuarioResource.findById(id);
            return ResponseEntity.ok(user.getBody());
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            usuarioResource.deleteUser(id);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
