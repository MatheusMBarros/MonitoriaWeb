package lucas.rayzer.Exercicio1.Users;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@JsonIgnoreProperties({"fornecedor"})

public class Produto {
    @Id
    @GeneratedValue
    private Integer id_produto;

    @Size(max = 45)
    private String descricao;

    private float valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<Cliente> clientes;

    public Produto() {

    }

    public Integer getId_produto() {
        return id_produto;
    }
    public void setId_produto(Integer id_produto){
        this.id_produto = id_produto;
    }

    public void setForn(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedor getForn(){
        return this.fornecedor;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id_produto=" + id_produto +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
