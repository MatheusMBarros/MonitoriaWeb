package lucas.rayzer.Exercicio1.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties({"fornecedor"})
public class Cliente {
    @Id
    @GeneratedValue
    private Integer id_cliente;
    @Size(max = 45)
    private String name;
    @Size(max = 45)
    private String cpf;
    @Size(max = 45)
    private String cnpj;
    @Size(max = 45)
    private String tipo_cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Produto produto;


    public Cliente(Integer id_cliente, String name, String cpf, String cnpj, String tipo_cliente) {
        this.id_cliente = id_cliente;
        this.name = name;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.tipo_cliente = tipo_cliente;
    }

    public Cliente() {
    }

    public Integer getId_cliente() {
        return id_cliente;
    }
    public void setId_cliente(Integer id){
        this.id_cliente = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto () {
        return this.produto;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente=" + id_cliente +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", tipo_cliente='" + tipo_cliente + '\'' +
                '}';
    }
}

