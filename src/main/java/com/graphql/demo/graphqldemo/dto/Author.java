package com.graphql.demo.graphqldemo.dto;

public class Author {

	private Integer id;

	private Integer age;

	private String sex;

	private String authorName;

	private Integer delFlg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

}
