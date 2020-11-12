package com.graphql.demo.graphqldemo.publisher;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.demo.graphqldemo.dao.BookDao;
import com.graphql.demo.graphqldemo.dto.Book;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;

@Component
public class MyPublisher {

	@Autowired
	private BookDao bookDao;

	private final Flowable<Book> publisher;

	public Flowable<Book> getPublisher() {
		return publisher;
	}

	public MyPublisher() {
		Observable<Book> stockPriceUpdateObservable = Observable.create(emitter -> {

			ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
			executorService.scheduleAtFixedRate(newXXX(emitter), 0, 2, TimeUnit.SECONDS);
		});

		ConnectableObservable<Book> connectableObservable = stockPriceUpdateObservable.share().publish();
		connectableObservable.connect();
		publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
	}

	private Runnable newXXX(ObservableEmitter<Book> emitter) {

		return () -> {
			List<Book> books = bookDao.getAllBooks();
			if (books != null) {
				emitBook(emitter, books);
			}
		};
	}

	private void emitBook(ObservableEmitter<Book> emitter, List<Book> books) {

		for (Book book : books) {
			emitter.onNext(book);
		}
	}
}
