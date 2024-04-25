package br.udesc.ceavi.eso.dsw.primeiroprojeto.postagem;

import br.udesc.ceavi.eso.dsw.primeiroprojeto.user.Usuario;
import br.udesc.ceavi.eso.dsw.primeiroprojeto.user.UsuarioResource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostagemController {
    @Autowired
    private PostagemResource postagemResource;
    @Autowired
    private UsuarioResource userResource;

    @GetMapping("/posts")
    public List<Postagem> listarTodasPostagens() {
        try {
            ResponseEntity<List<Postagem>> posts = postagemResource.listAllPosts();
            return posts.getBody();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Postagem> getPost(@PathVariable int id) {
        try {
            ResponseEntity<Postagem> post = postagemResource.findPost(id);
            return post;
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{id}/posts")
    public List<Postagem> listarPostagensDeUsuario(@PathVariable int id) {
        try {
            ResponseEntity<Usuario> usuario = userResource.findById(id);
            return usuario.getBody().getPostagens();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return null;
        }
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> criarPostagensDeUsuario(@PathVariable int id, @Valid @RequestBody Postagem postagem) {
        try {
            ResponseEntity<Usuario> usuario = userResource.findById(id);
            postagem.setUsuario(usuario.getBody());
            Postagem postagemSalva = postagemResource.criarPostagem(postagem).getBody();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(postagemSalva.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable int id) {
        try {
            postagemResource.deletePost(id);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
