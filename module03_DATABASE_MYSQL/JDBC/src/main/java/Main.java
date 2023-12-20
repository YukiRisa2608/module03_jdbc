import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    static final String USERNAME = "root";
    static final String PASSWORD = "Nham1992";

    public static void main(String[] args) {
        Connection conn = getConnection();
        System.out.println(conn);
    }
    public static Connection getConnection () {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver tương thích");
        } catch (SQLException e) {
            System.err.println("Từ chối truy cập CSDL vì lỗi xác thực");
        }
        return connection;
    }
}
