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
//import javax.activation.*;

public class MailApi {

    public static void sendMail(String recepient) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        String myAccountEmail = "";
        String password = "";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient);
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Congratulations, You Won the Auction!");
            String body = "Dear Bidder,\n"
                    + "It is with great pleasure that we announce that you have won the auction.\n"
                    + "Attached to this email, you will find all the details. Please feel free to download, print, and share the PDF.\n"
                    + "Best regards,\n"
                    + "CarBid team";

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            //QRCodeExample qr = new QRCodeExample();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart attachmentPart = new MimeBodyPart();
            //DataSource source = new FileDataSource("/PI-DEV/nbproject/qr_code.pdf");
            //FileDataSource source = new FileDataSource("C:\\Users\\gtsia\\Documents\\NetBeansProjects\\PI-DEV/qr_code.pdf");
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            //attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("Details.pdf");
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("message sent successfully....");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ;
        }
        return null;

    }

}
