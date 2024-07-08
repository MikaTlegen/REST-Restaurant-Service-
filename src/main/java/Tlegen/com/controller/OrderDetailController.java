package Tlegen.com.controller;

import Tlegen.com.entity.OrderDetail;
import Tlegen.com.service.OrderDetailService;
import jakarta.ws.rs.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.net.URI;
import java.util.List;

@Path("/orderdetail")
public class OrderDetailController {

    private static final Logger log = LoggerFactory.getLogger(OrderDetailController.class);
    @Inject
    OrderDetailService orderDetailService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        try {
            OrderDetail detail = orderDetailService.get(id);
            if (detail == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Order detail not found").build();
            }
            return Response.ok(detail).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        log.info("get message by id: " );
        try {
            List<OrderDetail> details = orderDetailService.getAll();
            return Response.ok(details).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(OrderDetail detail) {
        try {
            int id = orderDetailService.create(detail).getId();
            return Response.created(URI.create("/orderdetail/" + id)).entity(detail).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(OrderDetail detailToUpdate, @PathParam("id") int id) {
        try {
            orderDetailService.update(detailToUpdate);
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
            orderDetailService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}

