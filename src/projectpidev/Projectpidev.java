/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectpidev;
import entities.SpareParts;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServicesSpareParts;
import tools.MyConnection;

/**
 *
 * @author Yasmine
 */
public class Projectpidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyConnection db=MyConnection.getInstance();
        
        ServicesSpareParts sp2 = new ServicesSpareParts();
        System.out.println(sp2.afficher());
         SpareParts p2 = new SpareParts(2,"moteur",3,"occasion",1200,"volswagen");
        SpareParts p3 = new SpareParts(3,"moteur",2,"en très bon état",2200,"kia");
         SpareParts p4 = new SpareParts(4,"moteur",2,"en très bon état",3500,"kia");
         SpareParts p5 = new SpareParts(5,"decor_volan",1,"de couleur bleu",110,"kia");
       SpareParts p6 = new SpareParts(6,"decor_volan",2,"rose fushia ",80,"kia");
       SpareParts p7 = new SpareParts(7,"decor_volan",2,"rose fushia ",80,"kia");
        try{
          sp2.ajouter2(p2);
          // sp2.ajouter2(p6);
       } catch(SQLException ex){
           System.out.println("Probleme insertion: "+ex.getMessage());
       }


        sp2.supprimer(7);
       
     //sp2.modifier(p7);
       
       
       
        System.out.println(sp2.GetSparePartsById(1));
        // TODO code application logic here
    }
    
    
    
}


