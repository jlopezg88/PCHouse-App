/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.crud;

import com.dam.pi.model.Lineapedido;
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
public class LineapedidoFacade extends AbstractFacade<Lineapedido> {
    @PersistenceContext(unitName = "PCHouse-WSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineapedidoFacade() {
        super(Lineapedido.class);
    }
    public List<Lineapedido> findByCorreo(String correo){
        Query q = getEntityManager().createNamedQuery("Lineapedido.findByCorreo");
        q.setParameter("correo", "%"+correo+"%");
        return q.getResultList();
    }
}
