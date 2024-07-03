package Tlegen.com.connection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionTest {

    private static Connection connection;

    @Before
    public void setUp() throws SQLException {
        Properties properties = new Properties();
        try {
            InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("/application.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла свойств");
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        connection = DriverManager.getConnection(url, user, password);
    }

    @AfterAll
    public void tearDown() throws SQLException {
        // Закрываем соединение после теста
        if (connection!= null) {
            connection.close();
        }
    }


    @Test
    public void testDatabaseConnection() {
//        // Проверяем, что метод возвращает ожидаемый результат
//        Connection result = databaseConnection.getConnection();
//        Assert.assertNotNull(result);
//        Assert.assertEquals(mockConnection, result);
    }
}
