/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.dao;

/**
 *
 * @author Bianca
 */
import br.ufscar.dc.dsw.pojo.Locadora;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class LocadoraDAO extends GenericDAO<Locadora> {

    //Salva locadora inserida
    @Override
    public void save(Locadora locadora) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(locadora);
        tx.commit();
        em.close();
    }

    //Pega todas as locadora
    @Override
    public List<Locadora> getAll() {
        EntityManager em = this.getEntityManager();
        Query q = em.createQuery("select l from Locadora l", Locadora.class);
        List<Locadora> locadoras = q.getResultList();
        em.close();
        return locadoras;
    }

    //Deleta Locadora pelo id.
    @Override
    public void delete(Locadora locadora) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        locadora = em.getReference(Locadora.class, locadora.getId());
        tx.begin();
        em.remove(locadora);
        tx.commit();
    }

     //Atualiza locadora 
    @Override
    public void update(Locadora locadora) {
        EntityManager em = this.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(locadora);
        tx.commit();
        em.close();
    }

    //Pega locadora pelo id
    @Override
    public Locadora get(Long id_locadora) {
        EntityManager em = this.getEntityManager();
        Locadora locadora = em.find(Locadora.class, id_locadora);
        em.close();
        return locadora;
    }
}

