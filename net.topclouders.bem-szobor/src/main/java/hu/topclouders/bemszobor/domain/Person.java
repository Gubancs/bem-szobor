package hu.topclouders.bemszobor.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class Person implements Serializable {

	private static final long serialVersionUID = -6880235214137199827L;

	private String nick;

	@Enumerated
	private Gender gender;

	private Integer age;

	public Person() {
	}

	public Person(String nick, Gender gender, Integer age) {
		super();
		this.nick = nick;
		this.gender = gender;
		this.age = age;
	}

	public static enum Gender {
		MALE, FEMALE
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}
