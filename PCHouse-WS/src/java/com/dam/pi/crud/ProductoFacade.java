/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.crud;

import com.dam.pi.model.Producto;
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
public class ProductoFacade extends AbstractFacade<Producto> {
    @PersistenceContext(unitName = "PCHouse-WSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    public List<Producto> findByCategoria(String categoria){
        Query q = getEntityManager().createNamedQuery("Producto.findByCategoria");
        q.setParameter("categoria", "%"+categoria+"%");
        return q.getResultList();
    }
    
    public List<Producto> findByTipo(String categoria, String tipo){
            Query q = getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.categoria LIKE :categoria AND p.tipo LIKE :tipo");
            q.setParameter("categoria", "%"+categoria+"%");
            q.setParameter("tipo","%"+tipo+"%");
            return q.getResultList();
        }
}
