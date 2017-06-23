/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.ws;

import com.dam.pi.crud.AdministradorFacade;
import com.dam.pi.lista.ListaAdministrador;
import com.dam.pi.model.Administrador;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;

/**
 * REST Web Service
 *
 * @author Administrador
 */
@Path("administrador")
@RequestScoped
public class AdministradorResource {

    @Context
    private UriInfo context;
     @EJB AdministradorFacade af;
    /**
     * Creates a new instance of AdministradorResource
     */
    public AdministradorResource() {
    }

    /**
     * Retrieves representation of an instance of com.dam.pi.ws.AdministradorResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public ListaAdministrador getJson() {
        return new ListaAdministrador(af.findAll());
    }
    @GET
    @Path("/comprobar/{correo}/{pass}")
    @Produces("application/json")
    public ListaAdministrador findByCorreoAndPassAdmin(@PathParam("correo") String correo,@PathParam("pass") String pass) {
        return new ListaAdministrador(af.findByCorreoAndPassAdmin(correo, pass));
    }
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Administrador findByPk(@PathParam("id") Integer id) {
       return af.find(id);
    }
    
    @PUT
    @Consumes("application/json")
    public void putJson(Administrador content){
        if(af.find(content.getIdA()) == null){
            af.create(content);
        }
    }
    @POST
    @Consumes("application/json")
    public void postJson(Administrador content){
        af.edit(content);
    }
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") Integer id){
        af.remove(af.find(id));
    }
}
