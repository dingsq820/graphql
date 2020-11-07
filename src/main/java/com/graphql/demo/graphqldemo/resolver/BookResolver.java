package com.graphql.demo.graphqldemo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.demo.graphqldemo.dao.AuthorDao;
import com.graphql.demo.graphqldemo.dao.BookDao;
import com.graphql.demo.graphqldemo.dto.Author;
import com.graphql.demo.graphqldemo.dto.Book;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookResolver implements GraphQLResolver<Book> {

	@Autowired
	BookDao bookDao;
	
	@Autowired
	AuthorDao authorDao;
	
	public Author author(Book book) {
		
		return authorDao.getAuthorById(String.valueOf(book.getAuthorId()));
	}
}
