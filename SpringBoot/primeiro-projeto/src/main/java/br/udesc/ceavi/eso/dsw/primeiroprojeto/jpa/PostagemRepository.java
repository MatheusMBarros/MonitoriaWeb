package br.udesc.ceavi.eso.dsw.primeiroprojeto.jpa;

import br.udesc.ceavi.eso.dsw.primeiroprojeto.postagem.Postagem;
import br.udesc.ceavi.eso.dsw.primeiroprojeto.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Integer> {

}