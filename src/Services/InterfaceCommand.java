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
public interface InterfaceCommand<T> {
   
    public void addcommand(T  u) throws SQLException;
    
    public void deletecommand(int id) throws SQLException;
    
       public void modifycommand(T  u) throws SQLException;
       
       
           public Command GetcommandById(int id) throws SQLException;
    public ArrayList<T> displaycommand();
    
}

