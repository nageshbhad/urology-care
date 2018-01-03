/**
 * 
 */
package net.codejava.spring.service.mail;

/**
 * @author Admin
 *
 */
public interface MailService {
	
	boolean sendMail(String from,String to,String body,String subject,String replyTo) throws Exception;

}
