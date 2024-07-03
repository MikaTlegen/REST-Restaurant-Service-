package Tlegen.com.controller.DAO.impl;

import Tlegen.com.connection.DatabaseConfig;
import Tlegen.com.controller.DAO.query.OrderDetailQuerySQL;
import Tlegen.com.model.entity.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl {

    private static String url = DatabaseConfig.getDbUrl();
    private static String user = DatabaseConfig.getDbUser();
    private static String password = DatabaseConfig.getDbPassword();

    //    @Override
    public static List<OrderDetail> create(OrderDetail orderDetail) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailQuerySQL.INSERT_INTO());

            preparedStatement.setString(1, orderDetail.getOrderStatus());
            preparedStatement.setString(2, orderDetail.getTotalAmount());


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        }

     //   @Override
    public List<OrderDetail> readAll() {
        List<OrderDetail> orderDetails = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailQuerySQL.SELECT_ALL());

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String orderStatus = rs.getString("order_status");
                    double totalAmount = rs.getDouble("total_amount");

                    orderDetails.add(new OrderDetail(id, orderStatus, totalAmount));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return orderDetails;
    }

    //    @Override
    public OrderDetail read(int var1) {


        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailQuerySQL.getAllSelect());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String orderStatus = rs.getString("orderStatus");
                double totalAmount = rs.getDouble("totalAmount");
//                List<Product> products = (List<Product>) rs.getObject("products");

                orderDetail.add(new OrderDetail(id, orderStatus, totalAmount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return null;
    }

    //    @Override
    public boolean update(OrderDetail orderDetail, int var2) {
        return false;
    }

    //    @Override
    public boolean delete(int id) {
        return false;
    }
}
