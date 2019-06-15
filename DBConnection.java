/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Aftab
 */
public class DBConnection {
    private static Connection conn;
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver successfully loaded");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","onlineshopping","shopping");
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception in opening connection in DBConnection:"+ex);
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            conn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Exception in closing connection In DBConnection:"+ex);
        }
    }
}
