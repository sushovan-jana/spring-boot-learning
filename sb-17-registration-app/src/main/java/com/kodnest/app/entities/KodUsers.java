package com.kodnest.app.entities;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kodusers")
public class KodUsers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int kodid;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private int age;
	@Column
	private int marks;
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;

	public KodUsers() {

	}

	public KodUsers(String username, String password, int age, int marks, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.marks = marks;
		this.role = role;
	}

	public KodUsers(int kodid, String username, String password, int age, int marks, Role role) {
		super();
		this.kodid = kodid;
		this.username = username;
		this.password = password;
		this.age = age;
		this.marks = marks;
		this.role = role;
	}

	public int getKodid() {
		return kodid;
	}

	public void setKodid(int kodid) {
		this.kodid = kodid;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "KodUsers [kodid=" + kodid + ", username=" + username + ", password=" + password + ", age=" + age
				+ ", marks=" + marks + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, kodid, marks, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KodUsers other = (KodUsers) obj;
		return age == other.age && kodid == other.kodid && marks == other.marks
				&& Objects.equals(password, other.password) && role == other.role
				&& Objects.equals(username, other.username);
	}

}
