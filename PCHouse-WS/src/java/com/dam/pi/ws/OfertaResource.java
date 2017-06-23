/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.ws;

import com.dam.pi.crud.OfertaFacade;
import com.dam.pi.lista.ListaOfertas;
import com.dam.pi.model.Oferta;
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
@Path("oferta")
@RequestScoped
public class OfertaResource {

    @Context
    private UriInfo context;
    @EJB OfertaFacade of;

    /**
     * Creates a new instance of OfertaResource
     */
    public OfertaResource() {
    }

    /**
     * Retrieves representation of an instance of com.dam.pi.ws.OfertaResource
     * @return an instance of java.lang.String
     */
     @GET
    @Produces("application/json")
    public ListaOfertas getJson() {
        return new ListaOfertas(of.findAll());
    }
     
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Oferta findByPk(@PathParam("id") Integer id) {
       return of.find(id);
    }
    @PUT
    @Consumes("application/json")
    public void putJson(Oferta content){
        if(of.find(content.getIdO()) == null){
            of.create(content);
        }
    }
    @POST
    @Consumes("application/json")
    public void postJson(Oferta content){
        of.edit(content);
    }
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") Integer id){
        of.remove(of.find(id));
    }
}
