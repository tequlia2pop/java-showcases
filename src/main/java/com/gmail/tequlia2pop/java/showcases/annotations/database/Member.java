package com.gmail.tequlia2pop.java.showcases.annotations.database;

@Table(name = "MEMBERS")
public class Member {
	@SQLLong(constraints = @Constraints(primaryKey = true))
	Long id;

	@SQLString(length = 30)
	String firstName;

	@SQLString(length = 50)
	String lastName;

	@SQLInteger
	Integer age;

	@SQLString(length = 18, constraints = @Constraints(unique = true))
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
