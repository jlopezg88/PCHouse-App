/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.ws;

import com.dam.pi.crud.LineapedidoFacade;
import com.dam.pi.lista.ListaLineaPedidos;
import com.dam.pi.model.Lineapedido;
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
@Path("lineapedido")
@RequestScoped
public class LineapedidoResource {

    @Context
    private UriInfo context;
    @EJB LineapedidoFacade lf;

    /**
     * Creates a new instance of LineapedidoResource
     */
    public LineapedidoResource() {
    }

    /**
     * Retrieves representation of an instance of com.dam.pi.ws.LineapedidoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public ListaLineaPedidos getJson() {
        return new ListaLineaPedidos(lf.findAll());
    }
     @GET
    @Path("/correo/{correo}")
    @Produces("application/json")
    public ListaLineaPedidos findByCategoria(@PathParam("correo") String correo) {
        return new ListaLineaPedidos(lf.findByCorreo(correo));
    }
     
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Lineapedido findByPk(@PathParam("id") Integer id) {
       return lf.find(id);
    }

    @PUT
    @Consumes("application/json")
    public void putJson(Lineapedido content){
        if(lf.find(content.getIdLp()) == null){
            lf.create(content);
        }
    }
    @POST
    @Consumes("application/json")
    public void postJson(Lineapedido content){
        lf.edit(content);
    }
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") Integer id){
        lf.remove(lf.find(id));
    }
}
