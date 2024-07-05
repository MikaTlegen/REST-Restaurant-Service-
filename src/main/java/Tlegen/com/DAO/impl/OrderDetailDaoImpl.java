package Tlegen.com.DAO.impl;

import Tlegen.com.DAO.OrderDetailDao;
import Tlegen.com.db.DatabaseConfig;
;
import Tlegen.com.entity.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {

    private static final String INSERT_INTO = "INSERT INTO OrderDetail(id, order_status, total_amount) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM OrderDetail";
    private static final String SELECT_BY_ID = "SELECT * FROM OrderDetail WHERE id = ?";
    private static final String UPDATE = "UPDATE OrderDetail SET order_status = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM OrderDetail WHERE id = ?";
    private static final String deleteReferencingRecordsSql = "DELETE FROM orderdetail_product WHERE orderdetail_id = ?";

    private static String url = DatabaseConfig.getDbUrl();
    private static String user = DatabaseConfig.getDbUser();
    private static String password = DatabaseConfig.getDbPassword();

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        OrderDetail result = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Соединение установленно");
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {
                preparedStatement.setInt(1, orderDetail.getId());
                preparedStatement.setString(2, orderDetail.getOrderStatus());
                preparedStatement.setDouble(3, orderDetail.getTotalAmount());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    result = orderDetail;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<OrderDetail> read() {
        List<OrderDetail> orderDetails = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

                ResultSet rs = preparedStatement.executeQuery();
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

    public OrderDetail readById(int id) {
        OrderDetail orderDetail = null;

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
                preparedStatement.setInt(1, id); // Устанавливаем значение параметра в запросе

                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int detailId = rs.getInt("id");
                    String orderStatus = rs.getString("order_status");
                    double totalAmount = rs.getDouble("total_amount");

                    orderDetail = new OrderDetail(detailId, orderStatus, totalAmount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return orderDetail;
    }

    @Override
    public OrderDetail update(int id, String orderStatus) {
        OrderDetail updatedOrderDetail = null;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                preparedStatement.setString(1, orderStatus);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                        orderStatus = resultSet.getString("order_status");
                        // Создаем и возвращаем обновленный объект OrderDetail
                        updatedOrderDetail = new OrderDetail(id, orderStatus);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }

        return updatedOrderDetail;
    }


    @Override
    public boolean delete(int Id) {
        boolean deleteOrderDetail = false;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteReferencingRecordsSql)) {
                preparedStatement.setInt(1, Id);
                preparedStatement.executeUpdate();
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, Id);
                deleteOrderDetail = preparedStatement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return deleteOrderDetail;
    }
}

