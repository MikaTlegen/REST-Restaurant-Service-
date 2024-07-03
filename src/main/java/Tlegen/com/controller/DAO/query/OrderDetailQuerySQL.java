package Tlegen.com.controller.DAO.query;

public final class OrderDetailQuerySQL {
    private static final String INSERT_INTO = "INSERT INTO OrderDetail(orderStatus, totalAmount) (?,?)";
    private static final String SELECT_ALL = "SELECT * FROM OrderDetail";
    private static final String SELECT_ID = "SELECT * FROM OrderDetail WHERE id = ?";
    private static final String DELETE = "DELETE FROM OrderDetail WHERE id = ?";

    public static String INSERT_INTO() {
        return INSERT_INTO;
    }

    public static String SELECT_ALL() {
        return SELECT_ALL;
    }

    public static String SELECT_IDt() {
        return SELECT_ID;
    }

    public static String DeleteId() {
        return DELETE;
    }
}
