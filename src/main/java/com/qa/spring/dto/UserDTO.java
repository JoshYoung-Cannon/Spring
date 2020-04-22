package com.qa.spring.dto;

public class UserDTO {

	private Long id;

	private String forename;

	private String surname;

	public UserDTO() {
			super();
		}

	public UserDTO(String forename, String surname) {
			super();
			this.forename = forename;
			this.surname = surname;
		}

	public UserDTO(Long id, String forename, String surname) {
			super();
			this.id = id;
			this.forename = forename;
			this.surname = surname;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
