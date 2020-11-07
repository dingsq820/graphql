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

	@Autowired
	BookDao bookDao;
	
	@Autowired
	AuthorDao authorDao;
	
	public Book book(String id) {
		
		Book book = bookDao.getBookById(id);
		return book;
	}
	
	public List<Book> books() {
		
		return bookDao.getAllBooks();
	}
	
	public Author author(String id) {
		
		return authorDao.getAuthorById(id);
	}
	
	public List<Author> authors() {
		
		return authorDao.getAllAuthors();
	}

	public List<Book> booksOfAuthorById(String id) {
		return bookDao.getAllBooksOfAuthorById(id);
	}
}
