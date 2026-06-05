package com.kodnest.app.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	int userId;
	@Column(name = "password")
	String password;
	@Column(name = "role")
	String role;
	@Column(name = "email")
	String email;
	@Column(name = "phone")
	String phone;
	@Column(name = "username")
	String userName;

	public User() {

	}

	public User(String password, String role, String email, String phone, String userName) {
		super();
		this.password = password;
		this.role = role;
		this.email = email;
		this.phone = phone;
		this.userName = userName;
	}

	public User(int userId, String password, String role, String email, String phone, String userName) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
		this.email = email;
		this.phone = phone;
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", role=" + role + ", email=" + email + ", phone="
				+ phone + ", userName=" + userName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password, phone, role, userId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone) && Objects.equals(role, other.role) && userId == other.userId
				&& Objects.equals(userName, other.userName);
	}

}
