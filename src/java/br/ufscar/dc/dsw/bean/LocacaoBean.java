/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.pojo.Locacao;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Giulia
 */
@ManagedBean
@SessionScoped
public class LocacaoBean implements Serializable {
    private Locacao locacao;
    private String cpf;
    
    
    //Lista de locacoes é realizado no index.html
    public String lista() {
        return "locacao/index.xhtml";
    }
    
     //Cadastro de locacoes é realizado no form
    public String cadastra() {
        locacao = new Locacao();
        return "form.xhtml";
    }
    
     //Edição de locacoes é realizado no form
    public String edita(Long id_locacao) {
        LocacaoDAO dao = new LocacaoDAO();
        locacao = dao.get(id_locacao);
        return "form.xhtml";
    }
    
    //Após salvar, o site é direcionado para o index.
    public String salva() {
        LocacaoDAO dao = new LocacaoDAO();
        if (locacao.getId() == null) {
            dao.save(locacao);
        } else {
            dao.update(locacao);
        }
        return "index.xhtml";
    }
    
    //Após deletar uma locacao, o site é direcionado para o index
    public String delete(Locacao locacao) {
        LocacaoDAO dao = new LocacaoDAO();
        dao.delete(locacao);
        return "index.xhtml";
    }

    public String volta() {
        return "/index.xhtml?faces-redirect=true";
    }
    
    
    //  public List<Locacao> getLocacaoes() throws SQLException {
    //    LocacaoDAO dao = new LocacaoDAO();
    //    return dao.getAll();
    //}
    
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
     
    public String getCpf() {
        return cpf;
    }
    
    public List<Locacao> getLocadorabyCliente() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        return dao.getbyCliente(cpf);
    }

    public Locacao getLocacao() {
        return locacao;
    }
    
    public List<Locacao> getLocacoes() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        if (cpf == null || cpf.equals(" "))
            return dao.getAll();     
        else
            return dao.getbyCliente(cpf);
    }
    
}
