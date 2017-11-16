package start.web.listener.registration;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import start.web.common.Events;

@Component
@Configuration
@PropertySource("classpath:emailAccount.properties")
public class EmailAccountHandler {
	
	@Autowired
	private static Environment env;
	
	private static String businessemailAddress;
	private static String businessEmailPassword;
	private static String smtpHost;
	private static String smtpPort;
	
    @Autowired(required = true)
	public void setEnvironment(Environment e) {
    	EmailAccountHandler.env = e;
		businessemailAddress = env.getProperty("businessEmailAddress");
		businessEmailPassword = env.getProperty("businessEmailPassword");
		smtpHost = env.getProperty("smtp.host");
		smtpPort = env.getProperty("smtp.port");
	}
	
	public static void handler(Events event, Map<String, Object> userInfo) {
        switch (event) {
        case EmailAccountSuccessAck:
            sendAccountAckEmail(userInfo);
            break;
        case EmailVerifyAck:
            System.out.println("publish event 2" + userInfo);
            break;
        }
    }
	
	public static void sendAccountAckEmail(Map<String, Object> userInfo) {
		// Recipient's email ID needs to be mentioned.
	      String to = userInfo.get("email").toString();
	      
	      Properties properties = new Properties();
		  properties.put("mail.smtp.auth", "true");
		  properties.put("mail.smtp.starttls.enable", "true");
		  properties.put("mail.smtp.host", smtpHost);
		  properties.put("mail.smtp.port", smtpPort);

		  Session session = Session.getInstance(properties,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(businessemailAddress, businessEmailPassword);
					//return new PasswordAuthentication("", "");
			    }
		  });

	      try {
	    	  MimeMessage message = new MimeMessage(session);
	    	// Set From: header field of the header.
	          message.setFrom(new InternetAddress(businessemailAddress));

	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	          // Set Subject: header field
	          message.setSubject("Account successfully registered");
	          
	    	  Multipart multipart = new MimeMultipart("alternative");
		      
		      MimeBodyPart textPart = new MimeBodyPart();
		      String textContent = "Hi " + userInfo.get("username");
		      textPart.setText(textContent);
		      
		      MimeBodyPart htmlPart = new MimeBodyPart();
		      String htmlContent = "<html><p>Your account has been successfully registered</p></html>";
		      htmlPart.setContent(htmlContent, "text/html");
		      
		      multipart.addBodyPart(textPart);
		      multipart.addBodyPart(htmlPart);
		      message.setContent(multipart);
		      Transport.send(message);
	      } catch (MessagingException mex) {
	          mex.printStackTrace();
	      }
	      
	}
}
