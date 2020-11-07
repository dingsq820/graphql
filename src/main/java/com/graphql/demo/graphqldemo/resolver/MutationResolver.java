package com.graphql.demo.graphqldemo.resolver;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphql.demo.graphqldemo.dao.AuthorDao;
import com.graphql.demo.graphqldemo.dao.BookDao;
import com.graphql.demo.graphqldemo.dto.Book;
import com.graphql.demo.graphqldemo.model.BookInput;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private AuthorDao authorDao;

	/**
	 * Add Book
	 * 
	 * @param bookInput
	 * @return The New Book
	 */
	public Book addBook(BookInput bookInput) {
		Book book = new Book();
		book.setName(bookInput.getBookName());
		book.setPageCount(bookInput.getPageCount());
		book.setAuthorId(bookInput.getAuthorId());

		bookDao.addBook(book);
		String bookId = book.getId();
		Book newBook = bookDao.getBookById(bookId);
		return newBook;
	}

	/**
	 * Update Book
	 * 
	 * @param id        BookId
	 * @param bookInput
	 * @return The New Book
	 */
	public Book updateBook(Integer id, BookInput bookInput) {

		// Get Old Book
		Book book = bookDao.getBookById(String.valueOf(id));

		if (StringUtils.isNotEmpty(bookInput.getBookName())) {
			book.setName(bookInput.getBookName());
		}

		if (bookInput.getPageCount() != null) {
			book.setPageCount(bookInput.getPageCount());
		}

		// Update Book
		bookDao.updateBook(book);

		return book;
	}
}
