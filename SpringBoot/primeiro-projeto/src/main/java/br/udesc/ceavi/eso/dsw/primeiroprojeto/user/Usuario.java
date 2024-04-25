package br.udesc.ceavi.eso.dsw.primeiroprojeto.user;

import br.udesc.ceavi.eso.dsw.primeiroprojeto.postagem.Postagem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity(name="user_details")
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 3,message = "o nome deve conter pelo menos 3 letras")
    private String nome;

    @OneToMany(mappedBy="usuario")
    @JsonIgnore
    private List<Postagem> postagens;



    public Usuario() {}
    public Usuario(String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }

    @Override
    public String toString() {
        return "Usuario[" +"id=" + id + ", nome=" + nome + ']';
    }


}
