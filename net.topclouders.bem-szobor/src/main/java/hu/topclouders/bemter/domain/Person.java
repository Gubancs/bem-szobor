package hu.topclouders.bemter.domain;

public class Person extends MongoDocument {

	private String nick;
	
	private Gender gender;

	private Integer age;

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
