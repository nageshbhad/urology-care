package net.codejava.spring.model;

public class DoctorWhereAbouts {
	private int id;
	private String location;
	private String date;
	
	

	public DoctorWhereAbouts() {
		// TODO Auto-generated constructor stub
	}
	
	public DoctorWhereAbouts(int id, String location, String date) {
		super();
		this.id = id;
		this.location = location;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
}
