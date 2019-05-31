/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.converter;

/**
 *
 * @author Bianca
 */
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.pojo.Cliente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("ClienteConverter")
public class ClienteConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Long id = Long.parseLong(string);
        ClienteDAO dao = new ClienteDAO();
        return dao.get(id);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Cliente cliente = (Cliente) o;
        return cliente.getId().toString();
    }
}
