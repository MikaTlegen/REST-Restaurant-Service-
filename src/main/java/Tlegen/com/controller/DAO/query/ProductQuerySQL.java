package Tlegen.com.controller.DAO.query;

public final class ProductQuerySQL {
    private static final String INSERT_INTO = "INSERT INTO Product(id, name, quantity, available, price, productCategories) VALUES(?,?,?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM Product";
    private static final String SELECT_ID = "SELECT * FROM Product WHERE id = ?";
    private static final String DELETE = "DELETE FROM Product WHERE id = ?";

    public static String getInsertInto() {
        return INSERT_INTO;
    }

    public static String getAllSelect() {
        return SELECT_ALL;
    }

    public static String getIdSelect() {
        return SELECT_ID;
    }

    public static String DeleteId() {
        return DELETE;
    }
}
