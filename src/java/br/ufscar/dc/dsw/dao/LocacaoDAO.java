/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;
import br.ufscar.dc.dsw.pojo.Locacao;
import br.ufscar.dc.dsw.pojo.Locadora;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Giulia
 */
public class LocacaoDAO extends GenericDAO<Locacao> {
    
    //Salva locacao inserida
    @Override
    public void save(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(locacao);
        tx.commit();
        em.close();
    }
    
    //Pega todas as locacoes
    @Override
    public List<Locacao> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locacao l", Locacao.class);
        List<Locacao> locacoes = q.getResultList();
        em.close();
        return locacoes;
    }
    //Listar locacao por cliente
    public List<Locacao> getByCliente(String cpf) {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locacao l where l.cpf = '"+cpf+"'", Locacao.class);
        List<Locacao> locacoes = q.getResultList();
        em.close();
        return locacoes;
    }
    public List<Locacao> getByLocadora(String cnpj) {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locacao l where l.cnpj = '"+cnpj+"'", Locacao.class);
        List<Locacao> locacoes = q.getResultList();
        em.close();
        return locacoes;
    }
    
     //Deleta Locacao pelo id.
    @Override
    public void delete(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        locacao = em.getReference(Locacao.class, locacao.getId());
        tx.begin();
        em.remove(locacao);
        tx.commit();
    }

    //Atualiza locacao 
    @Override
    public void update(Locacao locacao) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(locacao);
        tx.commit();
        em.close();
    }

    //Pega locadora pelo id
    @Override
    public Locacao get(Long id_locacao) {
        EntityManager em = this.getEntityManager();
        Locacao locacao = em.find(Locacao.class, id_locacao);
        em.close();
        return locacao;
    }
}
