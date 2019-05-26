package br.ufscar.dc.dsw.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cliente extends Usuario implements Serializable {

    private Long id;
    
    private String email;
    private String senha;
    private String cpf;
    private String nome;
    private String telefone;
    private String sexo;
    private String nascimento;

    public Long getId() {
        return super.getId();
    }

    public void setId_cliente(Long id) {
        this.id= id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone_cliente(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento_cliente(String nascimento) {
        this.nascimento = nascimento;
    }

}