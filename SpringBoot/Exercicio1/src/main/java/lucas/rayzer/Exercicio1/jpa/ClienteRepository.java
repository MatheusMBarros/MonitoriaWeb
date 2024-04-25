package lucas.rayzer.Exercicio1.jpa;

import lucas.rayzer.Exercicio1.Users.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
