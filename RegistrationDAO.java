/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.UserDTO;

/**
 *
 * @author Aftab
 */
public class RegistrationDAO {
    private static PreparedStatement ps1,ps2;
    static
    {
        try
        {
            ps1=DBConnection.getConnection().prepareStatement("select * from members where username=? and membertype=?");
            ps2=DBConnection.getConnection().prepareStatement("insert into members values(?,?,?)");
        }
        catch(Exception ex)
        {
            System.out.println("Exception in registrationDAO :"+ex);
        }
    }
    public static boolean searchUser(String username)throws SQLException
    {
        ps1.setString(1, username);
        ps1.setString(2,"CUSTOMER");
        ResultSet rs=ps1.executeQuery();
        return rs.next();
        
    }

    /**
     *
     * @param user
     * @return
     * @throws SQLException
     */
    public static boolean registerUser(UserDTO user)throws SQLException
    {
        boolean done=false;
        ps2.setString(1, user.getUsername());
        ps2.setString(2, user.getPassword());
        ps2.setString(3, user.getUsertype());
        int ans=ps2.executeUpdate();
        if(ans!=0)
                done=true;
        return done;
    }
    
}
