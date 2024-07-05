package Tlegen.com.controller;

import Tlegen.com.entity.Product;
import Tlegen.com.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Product product = productService.readId(id);
        if (product != null)
            return Response.ok(product).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Product> products = productService.read();
        return Response.ok(products).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Product product, @Context UriInfo uriInfo) {
        int id = productService.create(product).getId();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(id));
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Product productToUpdate, @PathParam("id") int id) {
        productService.update(productToUpdate);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        productService.delete(id);
        return Response.noContent().build();
    }
}
