package lucas.rayzer.Exercicio1.Users;

import jakarta.validation.Valid;
import lucas.rayzer.Exercicio1.jpa.ClienteRepository;
import lucas.rayzer.Exercicio1.jpa.Produtorepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ClienteResources {
    private ClienteRepository clienteRepository;
    private Produtorepository produtorepository;

    public ClienteResources(ClienteRepository clienteRepository, Produtorepository produtorepository) {
        this.clienteRepository = clienteRepository;
        this.produtorepository = produtorepository;
    }

    @GetMapping("/client")
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @GetMapping("/produtos/{id}/client")
    public List<Cliente> listarProdutosCliente(@PathVariable int id ){
        Optional<Produto> prod = produtorepository.findById(id);
        return prod.get().getClientes();
    }

    @PostMapping("/client")
    public ResponseEntity<Cliente> createClient(@Valid @RequestBody Cliente client){
        Cliente savedClient = clienteRepository.save(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{ID}")
                .buildAndExpand(savedClient.getId_cliente())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/produtos/{id}/client")
    public ResponseEntity<Object> criarProdutoCliente(@PathVariable int id, @Valid @RequestBody Cliente cliente){
        Optional <Produto> prod = produtorepository.findById(id);
        cliente.setProduto(prod.get());
        Cliente clienteSaved = clienteRepository.save(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteSaved.getId_cliente())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/produtos/{idProduto}/client/{idCliente}")
    public ResponseEntity<Void> deleteProdutoCliente(@PathVariable int idProduto, @PathVariable int idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (cliente.isPresent()) {
            Optional<Produto> produto = produtorepository.findById(idProduto);
            if (produto.isPresent()) {
                if (cliente.get().getProduto().equals(produto.get())) {
                    cliente.get().setProduto(null);
                    clienteRepository.save(cliente.get());
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/produtos/{idProduto}/client/{idCliente}")
    public ResponseEntity<Void> updateProdutoCliente(@PathVariable int idProduto, @PathVariable int idCliente, @Valid @RequestBody Produto produto) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if (cliente.isPresent()) {
            Optional<Produto> produtoExistente = produtorepository.findById(idProduto);
            if (produtoExistente.isPresent()) {
                if (cliente.get().getProduto().equals(produtoExistente.get())) {
                    produto.setId_produto(idProduto);
                    produto.setForn(produtoExistente.get().getForn());
                    produtorepository.save(produto);
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable int id, @Valid @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente clienteAtualizado = clienteExistente.get();
            clienteAtualizado.setName(cliente.getName());
            clienteAtualizado.setCpf(cliente.getCpf());
            clienteAtualizado.setCnpj(cliente.getCnpj());
            clienteAtualizado.setTipo_cliente(cliente.getTipo_cliente());
            clienteRepository.save(clienteAtualizado);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
