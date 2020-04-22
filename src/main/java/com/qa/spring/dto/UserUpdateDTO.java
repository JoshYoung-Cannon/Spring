package com.qa.spring.dto;

public class UserUpdateDTO {

	private String forename;

	private String surname;

	public UserUpdateDTO() {
			super();
		}

	public UserUpdateDTO(String forename, String surname) {
			super();
			this.forename = forename;
			this.surname = surname;
		}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
