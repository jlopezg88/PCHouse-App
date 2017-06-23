/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.crud;

import com.dam.pi.model.Sugerencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrador
 */
@Stateless
public class SugerenciaFacade extends AbstractFacade<Sugerencia> {
    @PersistenceContext(unitName = "PCHouse-WSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SugerenciaFacade() {
        super(Sugerencia.class);
    }
    public List<Sugerencia> findByTipoSu(String tipo){
        Query q = getEntityManager().createNamedQuery("Sugerencia.findByTipo");
        q.setParameter("tipo", "%"+tipo+"%");
        return q.getResultList();
    }
}
