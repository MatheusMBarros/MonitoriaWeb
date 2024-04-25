package br.udesc.ceavi.eso.dsw.primeiroprojeto.postagem;

import br.udesc.ceavi.eso.dsw.primeiroprojeto.user.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Postagem {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2)
    private String mensagem;
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private Usuario usuario;


    public Postagem(Integer id, String mensagem, Usuario usuario) {
        super();
        this.id = id;
        this.mensagem = mensagem;
        this.usuario = usuario;
    }

    public Postagem() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
