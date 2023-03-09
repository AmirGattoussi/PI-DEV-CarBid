package Api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * 
 * @author neil
 */
public class ReservationInformMailApi {

    /**
     * This method sends an email to user regarding reservations updates.
     * 
     * @param recipientEmail e-mail of the recipient.
     * @param msg            the e-mail body.
     */
    public static void sendEmailToUser(String recipientEmail, String name, String car_brand, String car_model) {
        try {
            String senderEmail = "neil.monastiri@esprit.tn";
            String senderPassword = "223JMT0532";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            /* Setting the Email subject */
            message.setSubject("Reservation Canceled");

            /* Setting the Email body */
            message.setText(replaceWordsInTmplt(name, car_brand, car_model));

            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println("****************");
            e.printStackTrace();
            System.out.println("****************");
        }
    }

    /**
     * This method opens the email template file, reads it and replaces words with
     * information provided as parameters.
     * 
     * @param name      the name of the user that did the reservation.
     * @param car_brand brand of the car that is reserved.
     * @param car_model model of the car that is reserved.
     * @return Email body msg as a string.
     */
    public static String replaceWordsInTmplt(String name, String car_brand, String car_model) {

        try {
            File file = new File("./src/Api/canceledEmailTemplate.txt");

            /* Create a BufferedReader object to read the file line by line */
            BufferedReader reader = new BufferedReader(new FileReader(file));

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            /* Loop through each line in the input file */
            while ((line = reader.readLine()) != null) {
                if (line.contains("[Customer Name]")) {
                    line = line.replace("[Customer Name]", name);
                }
                if (line.contains("[Car Brand]")) {
                    line = line.replace("[Car Brand]", car_brand);
                }
                if (line.contains("[Car Model]")) {
                    line = line.replace("[Car Model]", car_model);
                }

                line += "\n";
                stringBuilder.append(line);
            }

            reader.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            System.out.println("****************");
            e.printStackTrace();
            System.out.println("****************");
            return "";
        }
    }
}
