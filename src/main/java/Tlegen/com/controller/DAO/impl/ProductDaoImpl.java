package Tlegen.com.controller.DAO.impl;

import Tlegen.com.connection.DatabaseConfig;
import Tlegen.com.controller.DAO.query.OrderDetailQuerySQL;
import Tlegen.com.controller.DAO.ProductDao;
import Tlegen.com.model.entity.OrderDetail;
import Tlegen.com.model.entity.Product;
import Tlegen.com.model.entity.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();

        String url = DatabaseConfig.getDbUrl();
        String user = DatabaseConfig.getDbUser();
        String password = DatabaseConfig.getDbPassword();

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");
            PreparedStatement preparedStatement = connection.prepareStatement(OrderDetailQuerySQL.SELECT_ALL());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                boolean available = rs.getBoolean("available");
                double price = rs.getDouble("price");
                int orderDetail_id = rs.getInt("OrderDetail.id");

                OrderDetail orderDetail = getOrderDetailById(orderDetailId);

                products.add(new Product(id,name, quantity, available, price, orderDetail_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return products;
    }

    @Override
    public Product read(int var1) {
        return null;
    }

    @Override
    public boolean update(Product product, int var2) {
        return false;
    }

    @Override
    public boolean delete(int var1) {
        return false;
    }
}
