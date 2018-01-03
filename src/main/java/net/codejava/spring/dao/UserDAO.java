package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.DoctorWhereAbouts;

public interface UserDAO {
	public List<DoctorWhereAbouts> getDocWhereAboutslist();

	public void updateDocWhereAbouts(DoctorWhereAbouts whereAbouts);
}
