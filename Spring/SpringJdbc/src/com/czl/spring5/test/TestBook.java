package com.czl.spring5.test;

import com.czl.spring5.entity.Book;
import com.czl.spring5.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestBook {

	@Test
	public void testJdbcTemplate() {
		ApplicationContext context =
			new ClassPathXmlApplicationContext("bean1.xml");

		BookService bookService = context.getBean("bookService", BookService.class);

		Book book = new Book();

		book.setBookId("1");
		book.setBookname("Spring");
		book.setBstatus("b");

		//bookService.addBook(book);

		//bookService.updateBook(book);

		//bookService.deleteBook(book.getBookId());

		//System.out.println(bookService.findCount());

//		Book result = bookService.findOne("1");
//
//		System.out.println(result.getBookId());
//		System.out.println(result.getBookname());
//		System.out.println(result.getBstatus());

//		List<Book> bookList = bookService.findAll();
//
//		System.out.println(bookList);

		List<Object[]> batchArgs = new ArrayList<>();

		Object[] o1 = {"1"};
		Object[] o2 = {"9"};
		Object[] o3 = {"10"};


		batchArgs.add(o1);
		batchArgs.add(o2);
		batchArgs.add(o3);

		bookService.batchDeleteBook(batchArgs);

	}
}
