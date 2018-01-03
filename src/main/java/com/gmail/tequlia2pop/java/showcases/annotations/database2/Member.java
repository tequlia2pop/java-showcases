package com.gmail.tequlia2pop.java.showcases.annotations.database2;

@Table(name = "MEMBERS")
public class Member {
	@Id
	Long id;

	@Column(length = 30)
	String firstName;

	@Column(length = 50)
	String lastName;

	@Column
	Integer age;

	@Column(length = 18, unique = true)
	String idCardNo;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Integer getAge() {
		return age;
	}

	public String getIdCardNo() {
		return idCardNo;
	}
}
