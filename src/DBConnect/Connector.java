/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnect;

import java.sql.*;

public class Connector {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_NAME = "thietbidientu";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; // XAMPP mặc định có password trống 
    
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rset = null;
    
    
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                // Đăng ký driver JDBC
                //Class.forName(JDBC_DRIVER);
                
                // Thiết lập kết nối
                conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                System.out.println("Kết nối đến database thành công!");
            }
        /*
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy driver JDBC: " + e.getMessage());
            e.printStackTrace();*/
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối đến database: " + e.getMessage());
            e.printStackTrace();
        }
        
        return conn;
    }
     
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
    
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đã đóng kết nối đến database!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi đóng kết nối: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Phương thức kiểm tra kết nối
     * @return boolean - true nếu kết nối thành công, false nếu thất bại
     */
    public static boolean testConnection() {
        try {
            Connection testConn = getConnection();
            return testConn != null && !testConn.isClosed();
        } catch (SQLException e) {
            System.err.println("Kiểm tra kết nối thất bại: " + e.getMessage());
            return false;
        }
    }
    
   
}
    
