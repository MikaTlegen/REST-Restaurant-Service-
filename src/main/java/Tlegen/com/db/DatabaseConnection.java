package Tlegen.com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвера загруженны");
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер PostgreSQL JDBC не найден: " + e.getMessage());
            throw new RuntimeException("Драйвер PostgreSQL JDBC не найден");
        }
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;

        String url = DatabaseConfig.getDbUrl();
        String user = DatabaseConfig.getDbUser();
        String password = DatabaseConfig.getDbPassword();

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Успешно подключен к базе данных.");
        } catch (SQLException e) {
            System.err.println("Не удалось подключиться к базе данных");
            e.printStackTrace();
        }

        return connection;
    }
}


