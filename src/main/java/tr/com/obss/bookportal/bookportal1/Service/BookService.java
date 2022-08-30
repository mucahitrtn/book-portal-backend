package tr.com.obss.bookportal.bookportal1.Service;

import tr.com.obss.bookportal.bookportal1.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    List<Book> findByName(String name);

    Boolean createNewBook(Book book);

    Book updateBook(Long id, Book book);

    Boolean deleteBookById(Long bookId);

    Book findBookById(Long bookId);

}