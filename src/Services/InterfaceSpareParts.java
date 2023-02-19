/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import entities.SpareParts;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Yasmine
 */
public interface InterfaceSpareParts<T> {
    public void ajouter(T  u) throws SQLException;
    public void ajouter2(T  u) throws SQLException;
    
    public void supprimer(int id) throws SQLException;
    
       public void modifier(T  u) throws SQLException;
       
       
           public SpareParts GetSparePartsById(int id) throws SQLException;
    public ArrayList<T> afficher();
    
}
