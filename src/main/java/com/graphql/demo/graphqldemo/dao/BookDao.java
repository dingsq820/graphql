package com.graphql.demo.graphqldemo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.graphql.demo.graphqldemo.dto.Book;

@Component
@Mapper
public interface BookDao {

	/**
	 * Get Book Info By Book Id
	 * 
	 * @param id
	 * @return Book
	 */
	public Book getBookById(@Param("id") String id);

	/**
	 * Get All Books
	 * 
	 * @return the list of Book
	 */
	public List<Book> getAllBooks();

	/**
	 * Get All The Books Of The Author By Id
	 * 
	 * @param id
	 * @return the list of Book
	 */
	public List<Book> getAllBooksOfAuthorById(@Param("id") String id);

	/**
	 * Add Book
	 * 
	 * @param book
	 * @return the new Book
	 */
	public void addBook(Book book);

	/**
	 * Update Book
	 * 
	 * @param book
	 */
	public void updateBook(Book book);

	/**
	 * Delete Book
	 * 
	 * @param id
	 */
	public void deleteBook(@Param("id") String id);
}
