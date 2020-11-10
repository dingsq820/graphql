package com.graphql.demo.graphqldemo.resolver;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.demo.graphqldemo.dao.AuthorDao;
import com.graphql.demo.graphqldemo.dao.BookDao;
import com.graphql.demo.graphqldemo.dataLoader.AuthorLoader;
import com.graphql.demo.graphqldemo.dto.Author;
import com.graphql.demo.graphqldemo.dto.Book;

import kotlin.contracts.ReturnsNotNull;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookResolver implements GraphQLResolver<Book> {

	@Autowired
	BookDao bookDao;

	@Autowired
	AuthorDao authorDao;

	@Autowired
	private AuthorLoader authorLoader;

	public CompletableFuture<Author> author(Book book) {

//		try {
//			authorLoader.dataLoader.dispatch();
//			return authorLoader.dataLoader.load(String.valueOf(book.getAuthorId())).get();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
		
//		authorLoader.mmm();
		return authorLoader.dataLoader.load(String.valueOf(book.getAuthorId()));

//		return authorDao.getAuthorById(String.valueOf(book.getAuthorId()));
	}
}
