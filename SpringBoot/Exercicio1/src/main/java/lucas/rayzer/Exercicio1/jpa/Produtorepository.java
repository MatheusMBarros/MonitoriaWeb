package lucas.rayzer.Exercicio1.jpa;

import lucas.rayzer.Exercicio1.Users.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtorepository extends JpaRepository<Produto, Integer> {
}
