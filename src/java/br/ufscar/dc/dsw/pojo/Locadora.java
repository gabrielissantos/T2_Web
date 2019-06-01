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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Cacheable(value = false)
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"cnpj"})})
public class Locadora extends Usuario implements Serializable {
    
    
    private String cnpj;
    
    private String nome;
    
    private String cidade;

    
    public String toString(){
        return nome;
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

