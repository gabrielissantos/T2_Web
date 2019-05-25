/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

/**
 *
 * @author Bianca
 */
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.pojo.Locadora;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LocadoraBean implements Serializable {

    private Locadora locadora;

    //Lista de locadora é realizado no index.html
    public String lista() {
        return "locadora/index.xhtml";
    }

    //Cadastro de locadora é realizado no form
    public String cadastra() {
        locadora = new Locadora();
        return "locadora/form.xhtml";
    }

    //Edição de locadora é realizado no form
    public String edita(Long id_locadora) {
        LocadoraDAO dao = new LocadoraDAO();
        locadora = dao.get(id_locadora);
        return "locadora/form.xhtml";
    }

    //Após salvar, o site é direcionado para o index.
    public String salva() {
        LocadoraDAO dao = new LocadoraDAO();
        if (locadora.getId() == null) {
            dao.save(locadora);
        } else {
            dao.update(locadora);
        }
        return "index.xhtml";
    }

    //Após deletar uma locadora, o site é direcionado para o index
    public String delete(Locadora locadora) {
        LocadoraDAO dao = new LocadoraDAO();
        dao.delete(locadora);
        return "index.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }
    
    public List<Locadora> getLocadoras() throws SQLException {
        LocadoraDAO dao = new LocadoraDAO();
        return dao.getAll();
    }

    public Locadora getLocadora() {
        return locadora;
    }
}
