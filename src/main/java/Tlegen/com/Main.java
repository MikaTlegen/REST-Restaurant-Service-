package Tlegen.com;

import Tlegen.com.controller.DAO.impl.OrderDetailDaoRealization;
import Tlegen.com.model.entity.OrderDetail;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<OrderDetail> orderDetails = OrderDetailDaoRealization.readAll();

        System.out.println(orderDetails);
    }
}
