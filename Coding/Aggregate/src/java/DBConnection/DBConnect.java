package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;





public class DBConnect
{
    static Connection con;
    
            public static Connection getCon() throws Exception
            {
                String url="jdbc:mysql://localhost:3306/";
                String dbName="aggregate";
                String driver="com.mysql.jdbc.Driver";
                String userName="root";
                String password="password";
                
                Class.forName(driver).newInstance();
                con=DriverManager.getConnection(url + dbName,userName,password);
                return con;
                
                
                
                
            }
    }
