package com.graphql.demo.graphqldemo.dataLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import org.dataloader.BatchLoader;
import org.dataloader.CacheMap;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.demo.graphqldemo.dao.AuthorDao;
import com.graphql.demo.graphqldemo.dao.BookDao;
import com.graphql.demo.graphqldemo.dto.Author;
import com.graphql.demo.graphqldemo.dto.Book;

import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver;

@Component
public class DataLoaderInit {

	// Author Dao
	@Autowired
	private AuthorDao authorDao;

	// Book DAO
	@Autowired
	private BookDao bookDao;

	// Data Loader Registry

	private DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();

	public DataLoaderRegistry initDataLoaderRegistry() {

		DataLoader<String, Author> authorDataLoader = this.initAuthorLoader();
		dataLoaderRegistry.register("authorLoader", authorDataLoader);

		DataLoader<String, List<Book>> bookDataLoader = this.initBookLoader();
		dataLoaderRegistry.register("bookLoader", bookDataLoader);
		return this.dataLoaderRegistry;
	}

	/**
	 * Init Author Data Loader
	 * 
	 * @return Author Data Loader
	 */
	private DataLoader<String, Author> initAuthorLoader() {

		BatchLoader<String, Author> authorBatchLoader = new BatchLoader<String, Author>() {

			@Override
			public CompletionStage<List<Author>> load(List<String> keys) {

				return CompletableFuture.supplyAsync(() -> authorDao.getAuthorsByIds(keys));
			}
		};

		DataLoader<String, Author> authorLoader = DataLoader.newDataLoader(authorBatchLoader);

		return authorLoader;
	}

	/**
	 * Init Book Data Loader
	 * 
	 * @return Book Data Loader
	 */
	private DataLoader<String, List<Book>> initBookLoader() {

		BatchLoader<String, List<Book>> bookBatchLoader = new BatchLoader<String, List<Book>>() {

			@Override
			public CompletableFuture<List<List<Book>>> load(List<String> keys) {

				return CompletableFuture.supplyAsync(() -> {
					List<List<Book>> reBooks = new ArrayList<>();
					List<Book> books = bookDao.getAllBooksOfAuthorByIds(keys);
					for (String key : keys) {
						List<Book> tmpBooks = books.stream()
								.filter(book -> key.equals(String.valueOf(book.getAuthorId())))
								.collect(Collectors.toList());
						reBooks.add(tmpBooks);
					}

					return reBooks;
				});
			}
		};
//		
		CacheMap<String, Object> cacheMap = new CacheMap<String, Object>() {

			@Override
			public boolean containsKey(String key) {
				return true;
			}

			@Override
			public Object get(String key) {
				
				return null;
			}

			@Override
			public CacheMap<String, Object> set(String key, Object value) {
				return this;
			}

			@Override
			public CacheMap<String, Object> delete(String key) {
				return null;
			}

			@Override
			public CacheMap<String, Object> clear() {
				return null;
			}

		};
//		
//		
		DataLoaderOptions dataLoaderOptions = DataLoaderOptions.newOptions();
		dataLoaderOptions.setCacheMap(cacheMap);

//		DataLoader<String, List<Book>> bookLoader = DataLoader.newDataLoader(bookBatchLoader, dataLoaderOptions);
		DataLoader<String, List<Book>> bookLoader = DataLoader.newDataLoader(bookBatchLoader);

		return bookLoader;
	}
}
