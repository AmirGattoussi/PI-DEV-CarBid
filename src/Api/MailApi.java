/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Api;

/**
 *
 * @author asus
 */

import java.util.*;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
public class MailApi  
{  
 public static void sendMail(String recepient){  

 
      Properties properties = new Properties();  
      properties.put("mail.smtp.auth","true");
      properties.put("mail.smtp.starttls.enable","true");
      properties.put("mail.smtp.host","smtp.gmail.com");
       properties.put("mail.smtp.port","587");
       properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
       String myAccountEmail="yosr.moalla@esprit.tn";
       String password="";
       
      Session session = Session.getInstance(properties, new Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication(){
    return new PasswordAuthentication(myAccountEmail, password);
}
      });  
    Message message = prepareMessage(session,myAccountEmail,recepient); 
 }  
    
  private static Message prepareMessage(Session session,String myAccountEmail,String recepient){
          
     try {  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(myAccountEmail));
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(recepient));  
         message.setSubject("Congratulations ");  
         message.setText("Dear Bidder,</br> We are delighted to inform you that you have won the auction for the car ..");  
         Transport.send(message);  
         System.out.println("message sent successfully...."); 
     } catch (Exception ex) {
         System.out.println(ex.getMessage());;
     }
      return null;
      
  }
    
   }  
