package Tlegen.com.controller;

import Tlegen.com.entity.OrderDetail;
import Tlegen.com.service.OrderDetailService;

import javax.inject.Inject;
import javax.ws.rs.PATCH;
import java.util.List;

@PATCH("/order")
public class OrderDetailController {
    @Inject
    OrderDetailService orderDetailService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        OrderDetail orderDetail = OrderDetailService.readId(id);
        if (orderDetail != null)
            return Response.ok(orderDetail).build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<OrderDetail> orderDetails = orderDetailService.getAll();
        return Response.ok(orderDetails).build();
    }


}
