package com.graphql.demo.graphqldemo.dataLoader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.demo.graphqldemo.dao.AuthorDao;
import com.graphql.demo.graphqldemo.dto.Author;

@Component
public class AuthorLoader {

	@Autowired
	AuthorDao authorDao;

	public DataLoader<String, Author> dataLoader;

	public void mmm() {
		BatchLoader<String, Author> authorBatchLoader = new BatchLoader<String, Author>() {

			@Override
			public CompletionStage<List<Author>> load(List<String> keys) {

				return CompletableFuture.supplyAsync(() -> authorDao.getAuthorsByIds(keys));
			}
		};

		DataLoader<String, Author> userLoader = DataLoader.newDataLoader(authorBatchLoader);

		this.dataLoader = userLoader;

	}
}
