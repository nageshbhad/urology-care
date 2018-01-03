package net.codejava.spring.service.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServiceImpl  implements MailService{
	
	// Replace recipient@example.com with a "To" address. If your account 
    // is still in the sandbox, this address must be verified.
    static final String FROM = "info@urology-care.co.uk";
	
    // Replace sender@example.com with your "From" address. 
    // This address must be verified.
    static final String TO = "nagesh.bhad@gmail.com";
    
    // Replace smtp_username with your Amazon SES SMTP user name.
    static final String SMTP_USERNAME = "AKIAJCZZRZY7YRLGFYXQ";
    
    // Replace smtp_password with your Amazon SES SMTP password.
    static final String SMTP_PASSWORD = "Arsn3nzgNy1vfrwWyYM0oEWtPZq3rjCBrUdUSgxlmto0";
    
    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header on line 65.
    static final String CONFIGSET = "ConfigSet";
    
    // Amazon SES SMTP host name. This example uses the US West (Oregon) Region.
    static final String HOST = "email-smtp.us-west-2.amazonaws.com";
    
    // The port you will connect to on the Amazon SES SMTP endpoint. 
    static final int PORT = 2465;
    
    static final String SUBJECT = "Appointment Request Received!";
    static final String BODY = String.join(
    	    System.getProperty("line.separator"),
    	    "<h1>Amazon SES SMTP Email Test</h1>",
    	    "<p>This email was sent with Amazon SES using the ", 
    	    "<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
    	    " for <a href='https://www.java.com'>Java</a>."
    	);

	@Override
	public boolean sendMail(String from, String to, String body, String subject,String replyTo) throws Exception {
		// Create a Properties object to contain connection configuration information.
		boolean isSuccessful =false;
    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.port", 2465); 
    	
    	props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties. 
    	Session session = Session.getDefaultInstance(props);

        // Create a message with the specified information. 
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");
        Address [] addresses = { new InternetAddress(replyTo)};
        msg.setReplyTo(addresses);
        
        // Add a configuration set header. Comment or delete the 
        // next line if you are not using a configuration set
       // msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
            
        // Create a transport.
        Transport transport = session.getTransport();
                    
        // Send the message.
        try
        {
            System.out.println("Sending...");
            
            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
        	
            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
            isSuccessful = true;
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            isSuccessful = false;
            System.out.println("Error message: " + ex);
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();
        }
    
		return isSuccessful;
	}

}
