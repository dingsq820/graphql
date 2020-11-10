package com.graphql.demo.graphqldemo.resolver;

import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.demo.graphqldemo.dto.Author;
import com.graphql.demo.graphqldemo.dto.Book;

import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BookResolver implements GraphQLResolver<Book> {

	/**
	 * Add The Key to Loader.
	 * 
	 * @param book
	 * @return
	 */
	public CompletableFuture<Author> author(Book book, DataFetchingEnvironment dataFetchingEnvironment) {

		DataLoader<String, Author> authorDataLoader = dataFetchingEnvironment.getDataLoaderRegistry()
				.getDataLoader("authorLoader");
		return authorDataLoader.load(String.valueOf(book.getAuthorId()));

//		return authorLoader.dataLoader.load(String.valueOf(book.getAuthorId()));
	}

//	public Author author(Book book) {
//		return authorDao.getAuthorById(String.valueOf(book.getAuthorId()));
//	}
}
