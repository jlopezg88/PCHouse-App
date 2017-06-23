/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.ws;

import com.dam.pi.crud.SugerenciaFacade;
import com.dam.pi.lista.ListaSugerencias;
import com.dam.pi.model.Sugerencia;
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
@Path("sugerencia")
@RequestScoped
public class SugerenciaResource {

    @Context
    private UriInfo context;
    @EJB SugerenciaFacade sf;

    /**
     * Creates a new instance of SugerenciaResource
     */
    public SugerenciaResource() {
    }

    /**
     * Retrieves representation of an instance of com.dam.pi.ws.SugerenciaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public ListaSugerencias getJson() {
        return new ListaSugerencias(sf.findAll());
    }
    
     @GET
    @Path("/tipo/{tipo}")
    @Produces("application/json")
    public ListaSugerencias findByTipoSu(@PathParam("tipo") String tipo) {
        return new ListaSugerencias(sf.findByTipoSu(tipo));
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Sugerencia findByPk(@PathParam("id") Integer id) {
       return sf.find(id);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(Sugerencia content){
        if(sf.find(content.getIdCon()) == null){
            sf.create(content);
        }
    }
    @POST
    @Consumes("application/json")
    public void postJson(Sugerencia content){
        sf.edit(content);
    }
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") Integer id){
        sf.remove(sf.find(id));
    }
}
