package com.czl.spring5.service;

import com.czl.spring5.dao.BookDao;
import com.czl.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;

	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	public void deleteBook(String bookId) {
		bookDao.deleteBook(bookId);
	}

	public int findCount() {
		return bookDao.selectCount();
	}

	public Book findOne(String bookId) {
		return bookDao.findBookInfo(bookId);
	}

	public List<Book> findAll() {
		return bookDao.findAllBook();
	}

	public void batchAdd(List<Object[]> batchArgs) {
		bookDao.batchAddBook(batchArgs);
	}

	public void batchUpdateBook(List<Object[]> batchArgs) {
		bookDao.batchUpdateBook(batchArgs);
	}

	public void batchDeleteBook(List<Object[]> batchArgs) {
		bookDao.batchDeleteBook(batchArgs);
	}
}
