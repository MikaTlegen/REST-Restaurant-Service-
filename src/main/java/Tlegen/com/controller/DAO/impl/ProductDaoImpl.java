package Tlegen.com.controller.DAO.impl;

import Tlegen.com.connection.DatabaseConfig;
import Tlegen.com.controller.DAO.ProductDao;
import Tlegen.com.model.entity.OrderDetail;
import Tlegen.com.model.entity.Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final String INSERT_INTO = "INSERT INTO Product(id, name, quantity, available, price, productCategories) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM Product";
    private static final String UPDATE = "UPDATE Product SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Product WHERE id = ?";
    private static final String deleteReferencingRecordsSql = "DELETE FROM orderdetail_product WHERE product_id = ?";

    private static String url = DatabaseConfig.getDbUrl();
    private static String user = DatabaseConfig.getDbUser();
    private static String password = DatabaseConfig.getDbPassword();


    @Override
    public List<Product> create(Product product) {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {

                preparedStatement.setInt(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setBoolean(3, product.isAvailable());
                preparedStatement.setDouble(4, product.getPrice());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return products;
    }


    @Override
    public List<Product> readAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    boolean available = rs.getBoolean("available");
                    double price = rs.getDouble("price");

                    products.add(new Product(id, name, quantity, available, price));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return products;
    }

    @Override
    public List<Product> update(int id, String name) {
        List<Product> updateProduct = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return updateProduct;
    }

    @Override
    public boolean delete(int Id) {
        boolean deleteProduct = false;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection established");

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteReferencingRecordsSql)) {
                preparedStatement.setInt(1, Id);
                preparedStatement.executeUpdate();
            }

            String DELETE = "DELETE FROM orderdetail WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
                preparedStatement.setInt(1, Id);
                deleteProduct = preparedStatement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return deleteProduct;
    }
}
