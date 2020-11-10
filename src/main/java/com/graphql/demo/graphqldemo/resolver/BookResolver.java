package com.graphql.demo.graphqldemo.resolver;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.demo.graphqldemo.dataLoader.AuthorLoader;
import com.graphql.demo.graphqldemo.dto.Author;
import com.graphql.demo.graphqldemo.dto.Book;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookResolver implements GraphQLResolver<Book> {

//	@Autowired
//	AuthorDao authorDao;

	@Autowired
	private AuthorLoader authorLoader;

	/**
	 * Add The Key to Loader.
	 * 
	 * @param book
	 * @return
	 */
	public CompletableFuture<Author> author(Book book) {

		return authorLoader.dataLoader.load(String.valueOf(book.getAuthorId()));
	}

//	public Author author(Book book) {
//		return authorDao.getAuthorById(String.valueOf(book.getAuthorId()));
//	}
}
