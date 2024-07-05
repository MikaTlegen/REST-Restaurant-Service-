package Tlegen.com.DAO.impl;

import Tlegen.com.db.DatabaseConfig;
import Tlegen.com.DAO.ProductDao;
import Tlegen.com.entity.OrderDetail;
import Tlegen.com.entity.Product;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final String INSERT_INTO = "INSERT INTO Product(id, name, quantity, available, price, productCategories) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM Product";
    private static final String SELECT_BY_ID = "SELECT * FROM OrderDetail WHERE id = ?";
    private static final String UPDATE = "UPDATE Product SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM Product WHERE id = ?";
    private static final String deleteReferencingRecordsSql = "DELETE FROM orderdetail_product WHERE product_id = ?";

    private static String url = DatabaseConfig.getDbUrl();
    private static String user = DatabaseConfig.getDbUser();
    private static String password = DatabaseConfig.getDbPassword();

    @Override
    public Product create(Product product) {
        Product result = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Соединение установленно");
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)) {

                preparedStatement.setInt(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setBoolean(3, product.isAvailable());
                preparedStatement.setDouble(4, product.getPrice());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    result = product;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Product> read() {
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
    public Product readById(int id) {
        Product product = null;

        try (Connection connection = DriverManager.getConnection(url, user, password);) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
                preparedStatement.setInt(1, id); // Устанавливаем значение параметра в запросе

                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    int productId = rs.getInt("id");
                    String name = rs.getString("name");
                    int quantity = rs.getInt("quantity");
                    boolean available = rs.getBoolean("available");
                    double price = rs.getDouble("price");

                    product = new Product(id, name, quantity, available, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }
        return product;
    }


    @Override
    public Product update(int id, String name) {
        Product updatedProduct = null;

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Соединение установленно");

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        id = resultSet.getInt("id");
                        name = resultSet.getString("name");

                        updatedProduct = new Product(id, name);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных: " + e.getMessage());
        }

        return updatedProduct;
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
