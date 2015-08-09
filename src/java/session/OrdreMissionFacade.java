/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import beans.OrdreMission;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author machd
 */
@Stateless
public class OrdreMissionFacade extends AbstractFacade<OrdreMission> {
    @PersistenceContext(unitName = "AppTI1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public void Create(OrdreMission o){
        em.persist(o);
    }


    public OrdreMissionFacade() {
        super(OrdreMission.class);
    }
    
}
