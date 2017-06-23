/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.crud;

import com.dam.pi.model.Administrador;
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
public class AdministradorFacade extends AbstractFacade<Administrador> {
    @PersistenceContext(unitName = "PCHouse-WSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    public List<Administrador> findByCorreoAndPassAdmin(String correo, String pass){
            Query q = getEntityManager().createQuery("SELECT a FROM Administrador a WHERE a.correo LIKE :correo AND a.pass LIKE :pass");
            q.setParameter("correo", "%"+correo+"%");
            q.setParameter("pass","%"+pass+"%");
            return q.getResultList();
        }
}
