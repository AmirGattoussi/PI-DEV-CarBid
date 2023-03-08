package Api;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class ReservationInformMailApi {

    public static void sendEmailToUser(String recipientEmail, String msg) throws MessagingException {
        String senderEmail = "carBidVerif@gmail.com";
        String senderPassword = "rocpddemnwdthnul";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(msg);
        message.setText("Your verification code is ");

        Transport.send(message);
    }
}
