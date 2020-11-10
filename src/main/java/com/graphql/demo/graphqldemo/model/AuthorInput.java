package com.graphql.demo.graphqldemo.model;

public class AuthorInput {

	/** Author Age */
	private Integer age;
	
	/** Author Sex */
	private String sex;
	
	/** Author Name */
	private String authorName;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
