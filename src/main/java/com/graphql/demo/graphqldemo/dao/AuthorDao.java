package com.graphql.demo.graphqldemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.graphql.demo.graphqldemo.dto.Author;

@Component
@Mapper
public interface AuthorDao {

	/**
	 * Get Author Info By Author Id
	 * 
	 * @param id
	 * @return Author Info
	 */
	public Author getAuthorById(@Param("id") String id);

	/**
	 * Get All Authors
	 * 
	 * @return the list of Author
	 */
	public List<Author> getAllAuthors();
}
