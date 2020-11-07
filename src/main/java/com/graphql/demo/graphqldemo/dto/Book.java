package com.graphql.demo.graphqldemo.dto;

public class Book {

	private String id;

	private String name;

	private Integer pageCount;

	private Integer authorId;

	private Integer delFlg;

	private Author author;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
