package lucas.rayzer.Exercicio1.Users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import lucas.rayzer.Exercicio1.jpa.FornecedorRepository;
import lucas.rayzer.Exercicio1.jpa.Produtorepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class FornecedorResources {

    private FornecedorRepository fornecedorRepository;
    private Produtorepository produtorepository;

    public FornecedorResources(FornecedorRepository fornecedorRepository, Produtorepository produtorepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.produtorepository = produtorepository;
    }

    @GetMapping("/fornecedores")
    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.findAll();
    }

    @GetMapping("/fornecedores/{id}/produtos")
    public List<Produto> listarProdutosDeForn(@PathVariable int id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            return fornecedor.get().getProdutos();
        } else {
            return List.of();
        }
    }

    @PostMapping("/fornecedores")
    public ResponseEntity<Fornecedor> createFornecedor(@Valid @RequestBody Fornecedor forn) {
        Fornecedor savedFornecedor = fornecedorRepository.save(forn);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ID}")
                .buildAndExpand(savedFornecedor.getId_fornecedor())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/fornecedores/{id}/produtos")
    public ResponseEntity<Object> criarProdutoDeForn(@PathVariable int id, @Valid @RequestBody Produto prod) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            prod.setForn(fornecedor.get());
            Produto prodSaved = produtorepository.save(prod);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{ID}")
                    .buildAndExpand(prodSaved.getId_produto())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/fornecedores/{id}")
    public ResponseEntity<Object> deletarFornecedor(@PathVariable int id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            fornecedorRepository.delete(fornecedor.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/fornecedores/{fornecedorId}/produtos/{produtoId}")
    public ResponseEntity<Object> deletarProdutoDeFornecedor(@PathVariable int fornecedorId, @PathVariable int produtoId) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(fornecedorId);
        if (fornecedor.isPresent()) {
            Optional<Produto> produto = fornecedor.get().getProdutos().stream()
                    .filter(p -> p.getId_produto() == produtoId)
                    .findFirst();
            if (produto.isPresent()) {
                produtorepository.delete(produto.get());
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/fornecedores/{id}")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable int id, @Valid @RequestBody Fornecedor fornecedorAtualizado) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            fornecedorAtualizado.setId_fornecedor(id);
            fornecedorRepository.save(fornecedorAtualizado);
            return ResponseEntity.ok(fornecedorAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/fornecedores/{fornecedorId}/produtos/{produtoId}")
    public ResponseEntity<Object> atualizarProdutoDeFornecedor(@PathVariable int fornecedorId, @PathVariable int produtoId, @Valid @RequestBody Produto produtoAtualizado) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(fornecedorId);
        if (fornecedor.isPresent()) {
            Optional<Produto> produto = fornecedor.get().getProdutos().stream()
                    .filter(p -> p.getId_produto() == produtoId)
                    .findFirst();
            if (produto.isPresent()) {
                produtoAtualizado.setId_produto(produtoId);
                produtoAtualizado.setForn(fornecedor.get());
                produtorepository.save(produtoAtualizado);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
