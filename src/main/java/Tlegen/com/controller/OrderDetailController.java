package Tlegen.com.controller;

import Tlegen.com.entity.OrderDetail;
import Tlegen.com.service.OrderDetailService;
import jakarta.ws.rs.core.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@PATCH("/orderDetail")
public class OrderDetailController {
    @Inject
    OrderDetailService orderDetailService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        OrderDetail orderDetail = orderDetailService.readId(id);
        if (orderDetail != null)
            return Response.ok(orderDetail).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<OrderDetail> orderDetails = orderDetailService.read();
        return Response.ok(orderDetails).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(OrderDetail orderDetail, @Context UriInfo uriInfo) {
        int id = orderDetailService.create(orderDetail).getId();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Long.toString(id));
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(OrderDetail updateorderDetail, @PathParam("id") int id) {
        orderDetailService.update(updateorderDetail);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        orderDetailService.delete(id);
        return Response.noContent().build();
    }
}
