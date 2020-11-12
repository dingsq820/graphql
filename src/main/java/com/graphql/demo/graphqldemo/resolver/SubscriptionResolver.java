package com.graphql.demo.graphqldemo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.graphql.demo.graphqldemo.dto.Book;
import com.graphql.demo.graphqldemo.publisher.MyPublisher;

import io.reactivex.Flowable;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

	@Autowired
	private MyPublisher myPublisher;

	public Book newBookAdded() {

		Flowable<Book> book = myPublisher.getPublisher();
		return book.blockingFirst();
		
//		return dataFetchingEnvironment -> {return myPublisher.getPublisher();};
	}
}
