/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dam.pi.ws;

import com.dam.pi.crud.ClienteFacade;
import com.dam.pi.lista.ListaClientes;
import com.dam.pi.model.Cliente;
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
@Path("cliente")
@RequestScoped
public class ClienteResource {

    @Context
    private UriInfo context;
    @EJB ClienteFacade cf;
    
    /**
     * Creates a new instance of ClienteResource
     */
    public ClienteResource() {
    }

    /**
     * Retrieves representation of an instance of com.dam.pi.ws.ClienteResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public ListaClientes getJson() {
        return new ListaClientes(cf.findAll());
    }
    @GET
    @Path("/comprobar/{correo}/{pass}")
    @Produces("application/json")
    public ListaClientes findByCorreoAndPass(@PathParam("correo") String correo,@PathParam("pass") String pass) {
        return new ListaClientes(cf.findByCorreoAndPass(correo, pass));
    }
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Cliente findByPk(@PathParam("id") Integer id) {
       return cf.find(id);
    }
    
    @PUT
    @Consumes("application/json")
    public void putJson(Cliente content){
        if(cf.find(content.getIdC()) == null){
            cf.create(content);
        }
    }
    @POST
    @Consumes("application/json")
    public void postJson(Cliente content){
        cf.edit(content);
    }
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void delete(@PathParam("id") Integer id){
        cf.remove(cf.find(id));
    }
}
