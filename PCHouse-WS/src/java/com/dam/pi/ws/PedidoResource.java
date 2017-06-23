/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.ws;

import com.dam.pi.crud.PedidoFacade;
import com.dam.pi.lista.ListaPedidos;
import com.dam.pi.model.Pedido;
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

/**
 * REST Web Service
 *
 * @author Administrador
 */
@Path("pedido")
@RequestScoped
public class PedidoResource {

    @Context
    private UriInfo context;
    @EJB PedidoFacade pf;

    /**
     * Creates a new instance of PedidoResource
     */
    public PedidoResource() {
    }

    /**
     * Retrieves representation of an instance of com.dam.pi.ws.PedidoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public ListaPedidos getJson() {
        return new ListaPedidos(pf.findAll());
    }
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Pedido findByPk(@PathParam("id") Integer id) {
       return pf.find(id);
    }

    /**
     * PUT method for updating or creating an instance of PedidoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
