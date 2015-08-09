/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import beans.Personnel;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author machd
 */
@Stateless
public class PersonnelFacade extends AbstractFacade<Personnel> {
    @PersistenceContext(unitName = "AppTI1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public Personnel FindByMatricule(String matricule){
        Query qr=em.createNamedQuery("Personnel.findByMatricule");
        qr.setParameter("matricule", matricule);
        return (Personnel)qr.getSingleResult();
    }
            
            
            
    public PersonnelFacade() {
        super(Personnel.class);
    }
    
}
