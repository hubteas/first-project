package phonebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
//   1. getConnection
   public static Connection getConnection() {
      Connection conn = null;
      
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String user = "ora_user";
      String password = "hong";
      
      try {
         conn = DriverManager.getConnection(url, user, password);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      return conn;
   }
   

   //   2. Connection close
   public static void close(Connection conn) {
      if(conn != null) {
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

   //   3. Statement close
   public static void close(Statement stmt) {
      if(stmt != null) {
         try {
            stmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
//   4. ResultSet close
   public static void close(ResultSet rs) {
      if(rs != null) {
         try {
            rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
}