package br.udesc.ceavi.eso.dsw.primeiroprojeto.user;

import br.udesc.ceavi.eso.dsw.primeiroprojeto.jpa.PostagemRepository;
import br.udesc.ceavi.eso.dsw.primeiroprojeto.jpa.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioResource {

    private UsuarioRepository repository;

    public UsuarioResource(UsuarioRepository repository) {
        this.repository = repository;
    }


    public ResponseEntity<Usuario> criarUsuario(Usuario u) {
        try {
            Usuario savedUser = repository.save(u);
            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<Usuario>> listAll() {
        try {
            List<Usuario> users = repository.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    public ResponseEntity<Usuario> findById(Integer id) {
        Usuario user = null;
        try {
            user = repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity<Void> deleteUser(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
