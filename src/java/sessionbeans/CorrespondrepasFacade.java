/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import beans.Correspondrepas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author machd
 */
@Stateless
public class CorrespondrepasFacade extends AbstractFacade<Correspondrepas> {
    @PersistenceContext(unitName = "AppTI1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorrespondrepasFacade() {
        super(Correspondrepas.class);
    }
    
}