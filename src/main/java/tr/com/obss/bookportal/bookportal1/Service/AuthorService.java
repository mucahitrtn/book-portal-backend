package tr.com.obss.bookportal.bookportal1.Service;


import tr.com.obss.bookportal.bookportal1.model.Author;
import tr.com.obss.bookportal.bookportal1.model.Book;

import java.util.List;

public interface AuthorService {

    void createNewAuthor(Author author);

    Author findAuthorById(Long id);
    Author findAuthorByName(String name);

    void updateExistingAuthor(Long authorId, Book newBook);

    List<Author> getAllAuthors();

    Boolean deleteAuthorById(Long authorId);

    Boolean removeBookFromAuthor(Long authorId, Long bookId);

}