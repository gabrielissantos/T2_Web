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
import br.ufscar.dc.dsw.dao.PapelDAO;
import br.ufscar.dc.dsw.pojo.Locadora;
import br.ufscar.dc.dsw.pojo.Papel;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ManagedBean
@SessionScoped
public class LocadoraBean implements Serializable {

     private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
     
    private String cidade;
    private Locadora locadora;

    //Lista de locadora é realizado no index.html
    public String lista() {
        return "locadora/index.xhtml";
    }

    public String listaCidade() {
        return "index.xhtml";
    }

    //Cadastro de locadora é realizado no form
    public String cadastra() {
        locadora = new Locadora();
        return "form.xhtml";
    }

    //Edição de locadora é realizado no form
    public String edita(Long id) {
        LocadoraDAO dao = new LocadoraDAO();
        locadora = dao.get(id);
        return "form.xhtml";
    }

    //Após salvar, o site é direcionado para o index.
    public String salva() {
        LocadoraDAO dao = new LocadoraDAO();
        PapelDAO papelDAO = new PapelDAO();

        locadora.setSenha(encoder.encode(locadora.getSenha()));
        locadora.setAtivo(1);

        Papel p1 = new Papel();
        p1.setNome("ROLE_LOCADORA");
        papelDAO.save(p1);
        if (locadora.getId() == null) {
            dao.save(locadora);
             locadora.getPapel().add(p1);
            dao.update(locadora);
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
        if (cidade == null || cidade.equals(" ")) {
            return dao.getAll();
        } else {
            return dao.getbyCidade(cidade);
        }
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }

//     public List<Locadora> getLocadorabyCidade() throws SQLException {
//        LocadoraDAO dao = new LocadoraDAO();
//        return dao.getbyCidade(cidade);
//    }
    public Locadora getLocadora() {
        return locadora;
    }

    public List<String> autoComplete(String query) {
        LocadoraDAO dao = new LocadoraDAO();
        List<Locadora> locadoras = dao.getAll();
        List<String> filteredCidades = new ArrayList<>();

        for (int i = 0; i < locadoras.size(); i++) {
            Locadora skin = locadoras.get(i);
            if (skin.getCidade().toLowerCase().contains(query)) {
                filteredCidades.add(skin.getCidade());
            }
        }

        return filteredCidades;
    }

}
