package tr.com.obss.bookportal.bookportal1.Service;

import tr.com.obss.bookportal.bookportal1.model.Book;
import tr.com.obss.bookportal.bookportal1.model.ReadList;

import java.util.List;

public interface ReadListService {

    List<Book> findReadListById(Long id);

    void addBookToReadList(Long userId, Long bookId);

    void deleteFromReadList(Long userId, Long bookId);

}
