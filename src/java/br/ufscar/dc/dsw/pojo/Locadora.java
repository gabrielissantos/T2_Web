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

@Entity
@Cacheable(value = false)
public class Locadora extends Usuario implements Serializable {
    
    
    private Long id;
    
    private String cnpj;
    
    private String nome;
    
    private String cidade;

 
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj= cnpj;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }    
    
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade= cidade;
    }
    
//     @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//		return true;
//	if (obj == null)
//		return false;
//	if (!(obj instanceof Locadora))
//		return false;
//	Locadora other = (Locadora) obj;
//	return other.nome.equals(this.nome);
//    }
    
    
}

