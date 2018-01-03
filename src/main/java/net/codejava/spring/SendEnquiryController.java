package net.codejava.spring;

import net.codejava.spring.model.Appointment;
import net.codejava.spring.service.mail.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEnquiryController {
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/urology/send-enquiry", method = RequestMethod.POST ,consumes="application/json")
	public @ResponseBody String getEmployee(@RequestBody Appointment appointment) throws Exception {
		System.out.println("patients EmailId:"+appointment.getEmailId());
		String subject = "Enquiry Request Received!";
		String doctorMsgBody = getDoctorMsgBody(appointment);
		//doctor's emaol in place of nagesh.bhad
		boolean doctorMessageSent = mailService.sendMail("", "info@urology-care.co.uk", doctorMsgBody,subject,
				appointment.getEmailId());
		
		return doctorMessageSent+"";
		
	}

	private String getDoctorMsgBody(Appointment appointment) {
		return 	String.join(
	    	    System.getProperty("line.separator"),
	    	    "Dear Hemant,",
	    	    System.getProperty("line.separator"),
	    	    "<h4>You have received Enquiry request from "+appointment.getName()+"!</h4>",
	    	    System.getProperty("line.separator"),
	    	    "<p>Query Details from patient are: </p> ", 
	    	    System.getProperty("line.separator"),
	    	    "<h6>"+appointment.getEnquiryQuery()+"</h6>",
	    	   
	    	    "</br>",
	    	    "<p>Kindly reply this mail to send information to the patient on his number "+".</p></br>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "<h5>Regards, </h5>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "Urology-Care"
	    	);
	}

}
