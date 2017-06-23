/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.ws;

import com.dam.pi.crud.ProductoFacade;
import com.dam.pi.lista.ListaProductos;
import com.dam.pi.model.Producto;
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
@Path("producto")
@RequestScoped
public class ProductoResource {

    @Context
    private UriInfo context;
    @EJB ProductoFacade pf;
    /**
     * Creates a new instance of ProductoResource
     */
    public ProductoResource() {
    }

    /**
     * Retrieves representation of an instance of com.dam.pi.ws.ProductoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public ListaProductos getJson() {
        return new ListaProductos(pf.findAll());
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Producto findByPk(@PathParam("id") Integer id) {
       return pf.find(id);
    }
    
    @GET
    @Path("/categoria/{categoria}")
    @Produces("application/json")
    public ListaProductos findByCategoria(@PathParam("categoria") String categoria) {
        return new ListaProductos(pf.findByCategoria(categoria));
    }
    @GET
    @Path("/categoria/{categoria}/{tipo}")
    @Produces("application/json")
    public ListaProductos findByTipo(@PathParam("categoria") String categoria,@PathParam("tipo") String tipo) {
        return new ListaProductos(pf.findByTipo(categoria, tipo));
    }

    @PUT
    @Consumes("application/json")
    public void putJson(Producto content){
        if(pf.find(content.getIdP()) == null){
            pf.create(content);
        }
    }
    @POST
    @Consumes("application/json")
    public void postJson(Producto content){
        pf.edit(content);
    }
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") Integer id){
        pf.remove(pf.find(id));
    }
}
