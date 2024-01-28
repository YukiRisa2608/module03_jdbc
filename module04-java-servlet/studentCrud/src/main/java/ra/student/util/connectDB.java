package ra.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDB {
     private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
         private final static String URL = "jdbc:mysql://localhost:3306/student_crud";
         private final static String USERNAME = "root";
         private final static String PASSWORD = "Nham1992";


         public static Connection openConnection(){
             Connection conn ;
             try {
                 Class.forName(DRIVER) ; // khai bao Driver
                 conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                 return conn;
             } catch (ClassNotFoundException | SQLException e) {
                 throw new RuntimeException(e);
             }
         }
         public static void closeConnection(Connection conn){
             try {
                 if (!conn.isClosed()){
                     conn.close();
                 }
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             }
         }
}
