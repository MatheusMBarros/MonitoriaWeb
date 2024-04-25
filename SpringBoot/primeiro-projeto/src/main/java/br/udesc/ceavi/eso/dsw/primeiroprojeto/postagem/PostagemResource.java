package br.udesc.ceavi.eso.dsw.primeiroprojeto.postagem;

import br.udesc.ceavi.eso.dsw.primeiroprojeto.jpa.PostagemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostagemResource {

    private PostagemRepository postagemRepository;

    public PostagemResource(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public ResponseEntity<Postagem> criarPostagem(Postagem p) {
        if (p == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Postagem savedPost = postagemRepository.save(p);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<Postagem>> listAllPosts() {
        try {
            List<Postagem> posts = postagemRepository.findAll();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Postagem> findPost(Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Postagem post = postagemRepository.findById(id).orElse(null);
            if (post == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(post);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Void> deletePost(Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        if (postagemRepository.existsById(id)) {
            postagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
