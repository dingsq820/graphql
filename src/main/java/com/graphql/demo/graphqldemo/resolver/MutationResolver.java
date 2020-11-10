package com.graphql.demo.graphqldemo.resolver;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphql.demo.graphqldemo.dao.AuthorDao;
import com.graphql.demo.graphqldemo.dao.BookDao;
import com.graphql.demo.graphqldemo.dto.Author;
import com.graphql.demo.graphqldemo.dto.Book;
import com.graphql.demo.graphqldemo.model.AuthorInput;
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

		return bookDao.getBookById(book.getId());
	}

	/**
	 * Delete Book
	 * 
	 * @param id
	 * @return
	 */
	public Book deleteBook(Integer id) {
		Book book = bookDao.getBookById(String.valueOf(id));
		bookDao.deleteBook(String.valueOf(id));
		return book;
	}

	/**
	 * Add Author
	 * 
	 * @param authorInput
	 * @return The New Author
	 */
	public Author addAuthor(AuthorInput authorInput) {
		Author author = new Author();
		author.setAge(authorInput.getAge());
		author.setSex(authorInput.getSex());
		author.setAuthorName(authorInput.getAuthorName());

		authorDao.addAuthor(author);

		Integer id = author.getId();
		Author newAuthor = authorDao.getAuthorById(String.valueOf(id));
		return newAuthor;
	}

	/**
	 * Update Author
	 * 
	 * @param id          AuthorId
	 * @param authorInput
	 * @return The New Author
	 */
	public Author updateAuthor(Integer id, AuthorInput authorInput) {

		Author oldAuthor = authorDao.getAuthorById(String.valueOf(id));

		if (authorInput.getAge() != null) {
			oldAuthor.setAge(authorInput.getAge());
		}

		if (StringUtils.isNotEmpty(authorInput.getSex())) {
			oldAuthor.setSex(authorInput.getSex());
		}

		if (StringUtils.isNotEmpty(authorInput.getAuthorName())) {
			oldAuthor.setAuthorName(authorInput.getAuthorName());
		}

		authorDao.updateAuthor(oldAuthor);

		Author newAuthor = authorDao.getAuthorById(String.valueOf(oldAuthor.getId()));
		return newAuthor;
	}

	/**
	 * Delete Author
	 * 
	 * @param id
	 * @return
	 */
	public Author deleteAuthor(Integer id) {
		Author author = authorDao.getAuthorById(String.valueOf(id));
		authorDao.deleteAuthor(String.valueOf(id));
		return author;
	}
}
