package Tlegen.com.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties");
        ) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить конфигурацию базы данных" + e.getMessage());
        }
    }

    public static String getDbUrl() {
        return PROPERTIES.getProperty("db.url");
    }

    public static String getDbUser() {
        return PROPERTIES.getProperty("db.user");
    }

    public static String getDbPassword() {
        return PROPERTIES.getProperty("db.password");
    }
}
