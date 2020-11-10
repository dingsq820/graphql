package com.graphql.demo.graphqldemo.dataLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.demo.graphqldemo.dao.AuthorDao;
import com.graphql.demo.graphqldemo.dto.Author;

@Component
public class DataLoaderInit {

	// Author Dao
	@Autowired
	AuthorDao authorDao;

	// Data Loader Registry
	private DataLoaderRegistry dataLoaderRegistry;

	public DataLoaderRegistry initDataLoaderRegistry() {

		DataLoader<String, Author> authorDataLoader = this.initAuthorLoader();
		dataLoaderRegistry.register("authorLoader", authorDataLoader);
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

		DataLoader<String, Author> userLoader = DataLoader.newDataLoader(authorBatchLoader);

		return userLoader;
	}
}
