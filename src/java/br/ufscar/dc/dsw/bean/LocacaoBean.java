/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.bean;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.pojo.Cliente;
import br.ufscar.dc.dsw.pojo.Locacao;
import static br.ufscar.dc.dsw.pojo.Locacao_.data;
import br.ufscar.dc.dsw.pojo.Locadora;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

/**
 *
 * @author Giulia
 */
@ManagedBean
@SessionScoped
public class LocacaoBean implements Serializable {

    private Locacao locacao;
    private String cpf;
    private String cnpj;

    //Lista de locacoes é realizado no index.html
    public String lista() {
        return "locacao/index.xhtml?faces-redirect=true";
    }

    //Cadastro de locacoes é realizado no form
    public String cadastra() {
        locacao = new Locacao();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String email = req.getUserPrincipal().getName();
        Cliente cliente = new ClienteDAO().getbyEmail(email);
        //Locadora locadora = new LocadoraDAO().getbyEmail(email);
        locacao.setCpf(cliente.getCpf());

        return "form.xhtml";
    }

    //Edição de locacoes é realizado no form
    public String edita(Long id_locacao) {
        LocacaoDAO dao = new LocacaoDAO();
        locacao = dao.get(id_locacao);
        return "form.xhtml";
    }

    //Após salvar, o site é direcionado para o index.
    public String salva() throws SQLException {
        LocacaoDAO locacaoDao = new LocacaoDAO();
        ClienteDAO clienteDao = new ClienteDAO();
        boolean locacaoPermitida = true;

        List<Locacao> listaLocacoes = getLocacoes();
        //se existir locacoes na lista verifica se tem alguma ja feita naquele dia e hora para a locadora e cliente selecionado
        if (listaLocacoes.size() != 0) {
            for (int i = 0; i < listaLocacoes.size(); i++) {
                //nao permite locacao de clientes iguais na mesma hora e dia
                if (listaLocacoes.get(i).getCpf().equals(locacao.getCpf()) && listaLocacoes.get(i).getData().equals(locacao.getData())) {
                    int locacaoNova = Integer.parseInt(listaLocacoes.get(i).getHora().substring(0, 2));
                    int locacaoAtual = Integer.parseInt(locacao.getHora().substring(0, 2));
                    if (locacaoAtual == locacaoNova) {
                        locacaoPermitida = false;
                        //JOptionPane.showMessageDialog(null, "Não é permitido o cadastro dessa locacao: Cliente já em uso neste dia e hora.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
                        FacesMessage message = new FacesMessage("Não é permitido este cadastro.");
                        FacesContext.getCurrentInstance().addMessage("data", message);
                    }

                } else {
                    //nao permite locacao de locadoras iguais na mesma hora e dia
                    if (listaLocacoes.get(i).getCnpj().equals(locacao.getCnpj()) && listaLocacoes.get(i).getData().equals(locacao.getData())) {
                        int locacaoNova = Integer.parseInt(listaLocacoes.get(i).getHora().substring(0, 2));
                        int locacaoAtual = Integer.parseInt(locacao.getHora().substring(0, 2));
                        if (locacaoAtual == locacaoNova) {
                            locacaoPermitida = false;
                            //JOptionPane.showMessageDialog(null, "Não é permitido o cadastro dessa locacao: Locadora já em uso neste dia e hora.", "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
                            FacesMessage message = new FacesMessage("Não é permitido este cadastro.");
                            FacesContext.getCurrentInstance().addMessage("data", message);
                        }

                    }
                }
            }
        }

        if (locacaoPermitida == true) {
            if (locacao.getId() == null) {
                locacaoDao.save(locacao);
            } else {
                locacaoDao.update(locacao);
            }
            return "index.xhtml";
        }
        return "form.xhtml";
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
        return dao.getByCliente(cpf);
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public List<Locacao> getLocacoes() throws SQLException {
        LocacaoDAO dao = new LocacaoDAO();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String email = req.getUserPrincipal().getName();
        Cliente cliente = new ClienteDAO().getbyEmail(email);
        Locadora locadora = new LocadoraDAO().getbyEmail(email);
        if (cliente == null) {
            cnpj = locadora.getNome();
            return dao.getByLocadora(cnpj);
        } else {
            cpf = cliente.getCpf();
            return dao.getByCliente(cpf);
        }
    }

}
