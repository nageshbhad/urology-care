package net.codejava.spring;

import java.util.List;

import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.DoctorWhereAbouts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DoctorWhereAboutsController {
	
	@Autowired
	private UserDAO userDao;
	
	
	@RequestMapping(value = "/urology/getdocWhereabouts", method = RequestMethod.GET )
	public @ResponseBody List<DoctorWhereAbouts> getDocWhereAbouts() throws Exception {
		
		userDao.getDocWhereAboutslist();
		return userDao.getDocWhereAboutslist();
		
	}
	

	@RequestMapping(value = "/urology/updatedocWhereabouts", method = RequestMethod.POST,consumes="application/json" )
	public @ResponseBody boolean upDateDocWhereAbouts(@RequestBody DoctorWhereAbouts whereAbouts) throws Exception {
		
		userDao.updateDocWhereAbouts(whereAbouts);
		return false;
		
	}

}
