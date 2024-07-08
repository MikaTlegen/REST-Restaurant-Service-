package Tlegen.com.controller;

import Tlegen.com.entity.Product;
import Tlegen.com.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.ws.rs.*;
import java.net.URI;
import java.util.List;


@Path("/product")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        try {
            Product product = productService.get(id);
            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Product not found").build();
            }
            return Response.ok(product).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            List<Product> products = productService.getAll();
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Product product) {
        try {
            int id = productService.create(product).getId();
            return Response.created(URI.create("/product/" + id)).entity(product).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Product productToUpdate, @PathParam("id") int id) {
        try {
            productService.update(productToUpdate);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        try {
            productService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
