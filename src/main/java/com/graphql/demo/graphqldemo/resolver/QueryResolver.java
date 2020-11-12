package com.graphql.demo.graphqldemo.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphql.demo.graphqldemo.dao.AuthorDao;
import com.graphql.demo.graphqldemo.dao.BookDao;
import com.graphql.demo.graphqldemo.dto.Author;
import com.graphql.demo.graphqldemo.dto.Book;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

	// Book DAO
	@Autowired
	BookDao bookDao;

	// Author DAO
	@Autowired
	AuthorDao authorDao;

	/**
	 * Get Book
	 * 
	 * @param id BookID
	 * @return Book
	 */
	public Book getBook(String id) {

		Book book = bookDao.getBookById(id);
		return book;
	}

	/**
	 * Get Books
	 * 
	 * @return Books
	 */
	public List<Book> getBooks() {

		return bookDao.getAllBooks();
	}

	/**
	 * Get Author
	 * 
	 * @param id AuthorID
	 * @return Author
	 */
	public Author getAuthor(String id) {

		return authorDao.getAuthorById(id);
	}

	/**
	 * Get Authors
	 * 
	 * @return Authors
	 */
	public List<Author> getAuthors() {

		List<Author> authors = authorDao.getAllAuthors();
		return authors;
	}

	/**
	 * Get Books of Author By Author ID
	 * 
	 * @param id AuthorID
	 * @return Books
	 */
	public List<Book> getBooksOfAuthorById(String id) {
		return bookDao.getAllBooksOfAuthorById(id);
	}
}
