package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
public class User extends SuperEntity {
	private static final long serialVersionUID = 6587804352897768224L;

	@Column(name="username", length=30)
	private String username;
	
	@Column(name="password", length=128)
	private String password;
	
	@Column(name="email", length=100)
	private String email;
	
	@Column(name="phone", length=20)
	private String phone;
	
	public User() {
		super();
	}

	public User(String username, String password, String email, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", id=" + id + "]";
	}
	
}
