/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.SpareParts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import interfaces.InterfaceSpareParts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyConnection;

/**
 *
 * @author Yasmine
 */
public class ServicesSpareParts  {
    Connection connection;
    Statement ste;

    public ServicesSpareParts() {
        connection = (Connection) MyConnection.getInstance().getCnx();
    }
    public ArrayList<SpareParts> afficher() {
        ArrayList<SpareParts> listpieces = new ArrayList<>();
        try{
        ste= connection.createStatement();
        String req_select="SELECT * FROM `pidev`.`spareparts`";
        ResultSet res = ste.executeQuery(req_select);
        while(res.next()){
            int Id = res.getInt(1);
            String Type = res.getString(2);
            int Pou = res.getInt(3);
            String Description = res.getString(4);
            double Price = res.getDouble(5);
            String Typec = res.getString(6);
         //   SpareParts s = new SpareParts(Id,Type,Pou,Description,Price,Typec);
         SpareParts s = new SpareParts(Id, Type,Pou, Description,  Price, Typec);
            listpieces.add(s);
        }
        }catch(SQLException ex){
            System.out.println("SQLException "+ex.getMessage());
        }
        
        return listpieces;
    }
   
   /* public void ajouter(SpareParts u) {
        try {

            ste = connection.createStatement();
            String req = "INSERT INTO `pidev`.`spareparts` (`Id`,`Type`,`Pou`,`Description`,`Price`,`Typec`) VALUES ('" + u.getId() + "'+'" + u.getType() + "','" + u.getPou() + "','" + u.getDescription() + "','" + u.getPrice() + "','" + u.getTypec() + "');";
            ste.executeUpdate(req);

          
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

    }
*/
    
    public void ajouter2(SpareParts u) throws SQLException {
        PreparedStatement pre = connection.prepareStatement("INSERT INTO `pidev`.`spareparts` (`Id`,`Type`,`Pou`,`Description`,`Price`,`Typec`) VALUES (?,?,?,?,?,?)");

        pre.setInt(1, u.getId());
          pre.setString(2, u.getType());
           pre.setInt(3, u.getPou());
            pre.setString(4, u.getDescription());
        pre.setDouble(5, u.getPrice());
                 
        pre.setString(6, u.getTypec());

        pre.executeUpdate();

    }
    
     public void supprimer(int id) {

        try {
            PreparedStatement pre = connection.prepareStatement("delete from spareparts where Id = ?");
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }

    }
     
      public void modifier(SpareParts u) {

        try {
            PreparedStatement pre = connection.prepareStatement("Update spareparts set Type=?,Pou=?,Description=?,Price=?,Typec=? where Id = ?");

            pre.setInt(1, u.getId());

            pre.setString(2, u.getType());

            pre.setInt(3, u.getPou());

            pre.setString(4, u.getDescription());

            pre.setDouble(5, u.getPrice());

            pre.setString(6, u.getTypec());

            pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
      
       public SpareParts GetSparePartsById(int id) {
    

        try {
            
     PreparedStatement pre = connection.prepareStatement("select * from spareparts where Id = ?");

 
            pre.setInt(1, id);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
               
                SpareParts u = new SpareParts(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getDouble(5), result.getString(6));
                return u;
            }
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
      
      
     
}
    

