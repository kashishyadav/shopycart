/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.ItemInfoDTO;
import shoppingcatalog.dto.OrderDTO;
import shoppingcatalog.dto.OrderDetailsDTO;

/**
 *
 * @author Aftab
 */
public class StoreDAO {
    
    private static Statement st1,st2,st3,st4,st5;
    private static PreparedStatement ps1,ps2;
    private static PreparedStatement psi1,psi2,psi3,pss,pod,ps8,ps9,ps10,ps11,ps12,ps13;
    static
    {
        try
        {
            st1=DBConnection.getConnection().createStatement();
            ps1=DBConnection.getConnection().prepareStatement("select id,item_name from store_items where item_type=?");
            ps2=DBConnection.getConnection().prepareStatement("select * from store_items where id=?");
            st2=DBConnection.getConnection().createStatement();
            psi1=DBConnection.getConnection().prepareStatement("insert into order_master values(?,?,?,?)");
            psi2=DBConnection.getConnection().prepareStatement("insert into order_details values(?,?,?)");
            pss=DBConnection.getConnection().prepareStatement("select order_id,order_amount,order_date from order_master where cust_name=?");
            pod=DBConnection.getConnection().prepareStatement("select item_name,item_price,order_master.order_id,order_date from order_master join order_details on order_master.order_id=order_details.order_id where order_master.order_id=?");
            st3=DBConnection.getConnection().createStatement();
            ps8=DBConnection.getConnection().prepareStatement("insert into store_Items values(?,?,?,?,?,?)");
            st4=DBConnection.getConnection().createStatement();
            ps9=DBConnection.getConnection().prepareStatement("update store_items set item_name=?,item_price=?,item_desc=?,item_image=? where id=?");
            ps10=DBConnection.getConnection().prepareStatement("select * from store_items where id=?");
            ps11=DBConnection.getConnection().prepareStatement("delete from store_items where id=?");
            ps12=DBConnection.getConnection().prepareStatement("select username from members where username!=? and membertype!=?");
            ps13=DBConnection.getConnection().prepareStatement("delete from members where username=?");
            st5=DBConnection.getConnection().createStatement();
        }
        catch(Exception ex)
        {
            System.out.println("Error in DB Comm:"+ex);
            ex.printStackTrace();
        }
    }
    public static List<String> getItemType()throws SQLException
    {
       ResultSet rs=st1.executeQuery("select distinct item_type from store_items");
       ArrayList<String> itemList=new ArrayList<String>();
       while(rs.next())
       {
           itemList.add(rs.getString(1));
       }
       return itemList;
    }
    

public static List<ItemInfoDTO> getItemByType(String itemType)throws SQLException
{
    ps1.setString(1, itemType);
     ArrayList<ItemInfoDTO> itemList=new ArrayList<ItemInfoDTO>();
     ResultSet rs=ps1.executeQuery();
while(rs.next())
{
ItemInfoDTO obj=new ItemInfoDTO();
obj.setItemId(rs.getInt(1));
obj.setItemName(rs.getString(2));
itemList.add(obj);
}
return itemList;
}

public static ItemDTO getItemDetails(int itemId)throws SQLException
{
    ps2.setInt(1, itemId);
    ItemDTO item=new ItemDTO();
    ResultSet rs=ps2.executeQuery();
    while(rs.next())
    {
        item.setItemName(rs.getString("item_Name"));
        item.setItemId(rs.getInt("id"));
        item.setItemType(rs.getString("item_type"));
        item.setItemPrice(rs.getDouble("item_price"));
        item.setItemDesc(rs.getString("item_desc"));
        item.setItemImage(rs.getString("item_image"));
        
        
    }
    return item;
}

public static boolean addOrder(String custName,ArrayList<ItemDTO>itemList,double totalAmount)throws SQLException
{
     ResultSet rs=st2.executeQuery("select count(*)  from order_master ");
     rs.next();
     int lastId=rs.getInt(1);
     String nextId="ORD-00"+(lastId+1);
     
     psi1.setString(1, nextId);
     psi1.setString(2, custName);
     psi1.setDouble(3, totalAmount);
     java.util.Date today=new java.util.Date();
     long ms=today.getTime();
     java.sql.Date currDate=new java.sql.Date(ms);
     psi1.setDate(4, currDate);
     int ans1=psi1.executeUpdate();
     int count=0;
     System.out.println("Record Inserted in order_master!!");
     for(ItemDTO item:itemList)
     {
         psi2.setString(1, nextId);
         psi2.setString(2, item.getItemName());
         psi2.setDouble(3, totalAmount);
         int ans2=psi2.executeUpdate();
         if(ans2==1)
             ++count;
     }
     return (ans1==1&&count==itemList.size());
            
}

public static ArrayList<OrderDTO> getOrdersByCustomer(String name)throws SQLException
{
    pss.setString(1, name);
    ResultSet rs=pss.executeQuery();
    ArrayList<OrderDTO> order=new ArrayList<OrderDTO>();
    while(rs.next())
    {
        OrderDTO od=new OrderDTO();
        od.setOrderId(rs.getString(1));
        od.setOrderAmount(rs.getDouble(2));
        od.setOrderDate(rs.getDate(3));
        order.add(od);
        
        
    }
    return order;
}

public static ArrayList<OrderDetailsDTO> getOrderDetails(String ordid)throws SQLException
{
    pod.setString(1, ordid);
    ResultSet rs=pod.executeQuery();
    ArrayList<OrderDetailsDTO> details=new ArrayList<>();
    while(rs.next())
    {
        OrderDetailsDTO d=new OrderDetailsDTO();
        d.setItemName(rs.getString(1));
        d.setItemPrice(rs.getDouble(2));
        d.setOrdId(rs.getString(3));
        d.setOrderDate(rs.getDate(4));
        details.add(d);
        
    }
    return details;
    
}

   

    public static boolean addNewProduct(ItemDTO obj)throws SQLException
    {
        ResultSet rs=st3.executeQuery("select max(id) as count from store_items");
        rs.next();
        int count=(rs.getInt(1)+1);
        System.out.println("count is :"+count);
        
        ps8.setInt(1, count);
        ps8.setString(2, obj.getItemType());
        ps8.setString(3, obj.getItemName());
        ps8.setDouble(4, obj.getItemPrice());
        ps8.setString(5, obj.getItemDesc());
        ps8.setString(6, obj.getItemImage());
        int result=ps8.executeUpdate();
        System.out.println("in store dao"+result);
        
        return (result!=0);
    }
    
    
    public static ArrayList<Integer> getAllProductId()throws SQLException
    {
        ArrayList<Integer> pid=new ArrayList<>();
        ResultSet rs=st4.executeQuery("select id from store_items");
        while(rs.next())
        {
            pid.add(rs.getInt(1));
        }
        return pid;
        
    }
    
    
    public static boolean updateProduct(ItemDTO obj)throws SQLException
    {
       
        ps9.setString(1, obj.getItemName());
        ps9.setDouble(2, obj.getItemPrice());
        ps9.setString(3, obj.getItemDesc());
        ps9.setString(4, obj.getItemImage());
        ps9.setInt(5, obj.getItemId());
        int result=ps9.executeUpdate();
        
        return (result!=0);
    }
    
    public static ItemDTO getItemDetailsById(int id)throws SQLException
    {
        ps10.setInt(1, id);
        ResultSet rs=ps10.executeQuery();
        ItemDTO obj=new ItemDTO();
        if(rs.next())
        {
            obj.setItemId(id);
            obj.setItemType(rs.getString(2));
            obj.setItemName(rs.getString(3));
            obj.setItemPrice(rs.getDouble(4));
            obj.setItemDesc(rs.getString(5));
            obj.setItemImage(rs.getString(6));
            
        }
        
        return obj;
        
    }
    
    public static boolean deleteItemById(int id)throws SQLException
    {
        ps11.setInt(1, id);
        int count=ps11.executeUpdate();
        return (count!=0);
        
    }
    
    
    public static ArrayList<String> getUsers(String AdminName)throws SQLException
    {
        ps12.setString(1, AdminName);
        ps12.setString(2, "ADMIN");
        ArrayList<String> user=new ArrayList<>();
        ResultSet rs=ps12.executeQuery();
        while(rs.next())
            user.add(rs.getString(1));
        
        return user;
    }
    
    public static boolean removeUser(String username)throws SQLException
    {
        ps13.setString(1, username);
        int count=ps13.executeUpdate();
        return (count!=0);
    }
    
    
    public static ArrayList<OrderDTO> getOrders()throws SQLException
    {
        ResultSet rs=st5.executeQuery("select order_id,order_amount,order_date from order_master");
        ArrayList<OrderDTO> orders=new ArrayList<>();
        while(rs.next())
        {
            OrderDTO order=new OrderDTO();
            order.setOrderId(rs.getString(1));
            order.setOrderAmount(rs.getDouble(2));
            order.setOrderDate(rs.getDate(3));
            orders.add(order);
        }
        return orders;
    }
    
    




}
