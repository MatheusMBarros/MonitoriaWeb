package lucas.rayzer.Exercicio1.Users;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue
    private Integer id_fornecedor;
    @Size(max = 45)
    private String nome;

    @OneToMany(mappedBy = "fornecedor")
    @JsonBackReference
    private List<Produto> produtos;

    public Fornecedor(Integer id_fornecedor, String nome) {
        this.id_fornecedor = id_fornecedor;
        this.nome = nome;
    }

    public Fornecedor() {
    }

    public Integer getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(Integer id_fornecedor){
        this.id_fornecedor = id_fornecedor;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }


    @Override
    public String toString() {
        return "Fornecedor{" +
                "id_fornecedor=" + id_fornecedor +
                ", nome='" + nome + '\'' +
                '}';
    }





}
