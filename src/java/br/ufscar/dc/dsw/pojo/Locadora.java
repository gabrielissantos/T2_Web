/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.pojo;

/**
 *
 * @author Bianca
 */

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable(value = false)
public class Locadora implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_locadora;
    
    private String cnpj_locadora;
    
    private String nome_locadora;
    
    private String cidade_locadora;


    public Long getId() {
        return id_locadora;
    }

    public void setId(Long id) {
        this.id_locadora = id;
    }

    public String getCnpj() {
        return cnpj_locadora;
    }

    public void setCnpj(String cnpj) {
        this.cnpj_locadora= cnpj;
    }
    
    public String getNome() {
        return nome_locadora;
    }

    public void setNome(String nome) {
        this.nome_locadora = nome;
    }    
    
    public String getCidade() {
        return cidade_locadora;
    }

    public void setCidade(String cidade) {
        this.cidade_locadora= cidade;
    }
}

