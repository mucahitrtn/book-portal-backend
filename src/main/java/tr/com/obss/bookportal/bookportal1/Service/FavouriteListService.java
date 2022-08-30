package tr.com.obss.bookportal.bookportal1.Service;

import tr.com.obss.bookportal.bookportal1.model.Book;
import tr.com.obss.bookportal.bookportal1.model.FavouriteList;

import java.util.List;

public interface FavouriteListService {


    List<Book> findFavouriteListById(Long id);

    void addBookToFavouriteList(Long userId, Long bookId);

    void deleteFromFavouriteList(Long userId, Long bookId);

}
