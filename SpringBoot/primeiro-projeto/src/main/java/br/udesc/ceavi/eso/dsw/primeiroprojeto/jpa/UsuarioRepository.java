package br.udesc.ceavi.eso.dsw.primeiroprojeto.jpa;

import br.udesc.ceavi.eso.dsw.primeiroprojeto.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario , Integer> {

}
