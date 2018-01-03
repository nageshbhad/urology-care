package net.codejava.spring;

import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.Appointment;
import net.codejava.spring.service.mail.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/urology/book-appointment", method = RequestMethod.POST ,consumes="application/json")
	public @ResponseBody String getDummyEmployee(@RequestBody Appointment appointment) throws Exception {
		System.out.println("patients EmailId:"+appointment.getEmailId());
		String patientEmail = appointment.getEmailId();
		String subject = "Appointment Request Successfully Received!";
		String patientBody = getPatientMsgBody(appointment.getName());
		String doctorMsgBody = getDoctorMsgBody(appointment);
		boolean doctorMessageSent = mailService.sendMail("", "info@urology-care.co.uk", doctorMsgBody,subject,
				patientEmail);
		boolean patientMessageSent = false;
		if(doctorMessageSent){
			 patientMessageSent = mailService.sendMail("", patientEmail, patientBody,subject,
					"info@urology-care.co.uk");
		}
		return patientMessageSent+"";
		
	}

	private String getDoctorMsgBody(Appointment appointment) {
		return 	String.join(
	    	    System.getProperty("line.separator"),
	    	    "Dear Hemant,",
	    	    System.getProperty("line.separator"),
	    	    "<h4>You have received Appointment request from "+appointment.getName()+"!</h4>",
	    	    System.getProperty("line.separator"),
	    	    "<p>Preferred Details from patient are: </p> ", 
	    	    System.getProperty("line.separator"),
	    	    "<h6>1. Location:"+appointment.getPreferredLoc()+".</br></h6>",
	    	    System.getProperty("line.separator"),
	    	    "<h6>2. Date :"+appointment.getPreferredDate()+".</br></h6>",
	    	    System.getProperty("line.separator"),
	    	    "<h6>3. Time :"+appointment.getTime()+" .</br></h6>",
	    	    System.getProperty("line.separator"),
	    	    "<h6>4. Symptoms: "+appointment.getSymtoms()+" .</br></h6>",
	    	    System.getProperty("line.separator"),
	    	    "</br>",
	    	    "<p>Kindly confirm your availability to patient on his number "+appointment.getPhoneNo()+" .</p></br>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "<h5>Regards </h5>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "Urology-Care"
	    	);
	}

	private String getPatientMsgBody(String name) {
		return 	String.join(
	    	    System.getProperty("line.separator"),
	    	    "Dear "+name+",",
	    	    "<h2>Thank you for booking Appointment!</h2>",
	    	    "<p>My secretary will contact you soon based on availability. </p> ", 
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "<h5>Regards </h5>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "Mr. Hemant Nemade"
	    	);
		
	}
	
}
