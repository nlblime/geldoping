package erstesProjekt;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

    private static final String ABSENDER_EMAIL = "geldoping@gmail.com";
    private static final String ABSENDER_PASSWORT = "ngnc sygb jfeg zjik";
    private static final String EMPFAENGER_EMAIL = "emilsoriginal@gmail.com";

    public static void sendMail(String betreff, String inhalt) {
        Properties props = new Properties();
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(ABSENDER_EMAIL, ABSENDER_PASSWORT);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ABSENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMPFAENGER_EMAIL));
            message.setSubject(betreff);
            message.setText(inhalt);

            System.out.println("E-Mail wird jetzt gesendet...");
            
            Transport.send(message);

            System.out.println("E-Mail erfolgreich gesendet.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
