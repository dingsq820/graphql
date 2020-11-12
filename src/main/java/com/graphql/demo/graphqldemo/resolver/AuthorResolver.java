package com.graphql.demo.graphqldemo.resolver;

import java.util.List;
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
public class AuthorResolver implements GraphQLResolver<Author> {

	public CompletableFuture<List<Book>> books(Author author, DataFetchingEnvironment dataFetchingEnvironment) {

		DataLoader<String, List<Book>> bookDataLoader = dataFetchingEnvironment.getDataLoaderRegistry()
				.getDataLoader("bookLoader");
		return bookDataLoader.load(String.valueOf(author.getId()));
	}

//	public List<Book> books(Author author) {
//		return bookDao.getAllBooksOfAuthorById(String.valueOf(author.getId()));
//	}
}
