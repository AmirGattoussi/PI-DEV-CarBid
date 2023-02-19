/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;
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
