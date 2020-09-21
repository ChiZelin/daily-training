package com.czl.spring5.dao;

import com.czl.spring5.entity.Book;

import java.util.List;

public interface BookDao {

	public void addBook(Book book);

	public void updateBook(Book book);

	public void deleteBook(String bookId);

	public int selectCount();

	public Book findBookInfo(String bookId);

	public List<Book> findAllBook();

	public void batchAddBook(List<Object[]> batchArgs);

	public void batchUpdateBook(List<Object[]> batchArgs);

	public void batchDeleteBook(List<Object[]> batchArgs);
}
