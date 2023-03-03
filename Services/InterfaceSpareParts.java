/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;
import Entities.*;
//import Entities.SpareParts;
//import Entities.SpareParts;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entities.SpareParts;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yasmine
 */
public interface InterfaceSpareParts<T> {
   
    public void add(T  u) throws SQLException;
    
    public void delete(int id) throws SQLException;
    
       public void modify(T  u) throws SQLException;
       
       
           public SpareParts GetSparePartsById(int id) throws SQLException;
    public ArrayList<T> display();
    
}
