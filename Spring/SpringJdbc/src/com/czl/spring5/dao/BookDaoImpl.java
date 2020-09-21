package com.czl.spring5.dao;

import com.czl.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addBook(Book book) {

		String sql = "insert into t_book values(?,?,?)";

		Object[] args = {book.getBookId(), book.getBookname(), book.getBstatus()};

		int update = jdbcTemplate.update(sql, args);

		System.out.println(update);
	}

	@Override
	public void updateBook(Book book) {
		String sql = "Update t_book set bookname = ?, bstatus = ? where book_id = ?";

		Object[] args = {book.getBookname(), book.getBstatus(), book.getBookId()};

		int update = jdbcTemplate.update(sql, args);

		System.out.println(update);
	}

	@Override
	public void deleteBook(String bookId) {
		String sql = "delete from t_book where book_id = ?";

		int update = jdbcTemplate.update(sql, bookId);

		System.out.println(update);
	}

	@Override
	public int selectCount() {
		String sql = "Select count(*) from t_book";

		Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return count;
	}

	@Override
	public Book findBookInfo(String bookId) {

		String sql = "select * from t_book where book_id = ?";

		Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class),bookId);

		return book;
	}

	@Override
	public List<Book> findAllBook() {
		String sql = "select * from t_book";

		List<Book> bookList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));

		return bookList;
	}

	@Override
	public void batchAddBook(List<Object[]> batchArgs) {
		String sql = "insert into t_book values(?, ?, ?)";

		int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);

		System.out.println(Arrays.toString(ints));
	}

	@Override
	public void batchUpdateBook(List<Object[]> batchArgs) {
		String sql = "Update t_book set bookname = ?, bstatus = ? where book_id = ?";

		int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);

		System.out.println(Arrays.toString(ints));
	}

	@Override
	public void batchDeleteBook(List<Object[]> batchArgs) {
		String sql = "delete from t_book where book_id = ?";

		int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);

		System.out.println(Arrays.toString(ints));
	}
}
