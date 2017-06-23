/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.crud;

import com.dam.pi.model.Cliente;
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
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "PCHouse-WSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    public List<Cliente> findByCorreoAndPass(String correo, String pass){
            Query q = getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.correo LIKE :correo AND c.pass LIKE :pass");
            q.setParameter("correo", "%"+correo+"%");
            q.setParameter("pass","%"+pass+"%");
            return q.getResultList();
        }
}
