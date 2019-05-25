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
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.pojo.Locadora;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("LocadoraConverter")
public class LocadoraConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Long id_locadora = Long.parseLong(string);
        LocadoraDAO dao = new LocadoraDAO();
        return dao.get(id_locadora);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Locadora locadora = (Locadora) o;
        return locadora.getId().toString();
    }
}
