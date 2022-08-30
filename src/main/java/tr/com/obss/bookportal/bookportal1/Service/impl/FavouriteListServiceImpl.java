package tr.com.obss.bookportal.bookportal1.Service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.obss.bookportal.bookportal1.Service.FavouriteListService;
import tr.com.obss.bookportal.bookportal1.Service.UserService;
import tr.com.obss.bookportal.bookportal1.model.Book;
import tr.com.obss.bookportal.bookportal1.model.FavouriteList;
import tr.com.obss.bookportal.bookportal1.model.User;
import tr.com.obss.bookportal.bookportal1.repo.BookRepo;
import tr.com.obss.bookportal.bookportal1.repo.FavouriteListRepo;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavouriteListServiceImpl implements FavouriteListService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final BookRepo bookRepository;
    @Autowired
    private final FavouriteListRepo favouriteListRepository;


    @Override
    public List<Book> findFavouriteListById(Long userId) {
        User user = userService.findUserById(userId);
        FavouriteList favouriteList = user.getFavouriteList();
        return favouriteList.getBooks();
    }

    @Override
    public void addBookToFavouriteList(Long userId, Long bookId) {

        if(userId==null || bookId==null){
            log.info("User id or Book is null");
            return;
        }

        Book book  = bookRepository.findById(bookId).get();
        User user = userService.findUserById(userId);

        if(user==null || book==null){
            log.info("User or Book Not found in your favourite list");
            return;
        }

        FavouriteList favouriteList = user.getFavouriteList();
        List<Book> books = favouriteList.getBooks();
        books.add(book);
        favouriteList.setBooks(books);
        favouriteListRepository.save(favouriteList);
    }

    @Override
    public void deleteFromFavouriteList(Long userId, Long bookId) {

        if(userId==null || bookId==null){
            log.info("User id or Book is null");
            return;
        }

        Book book  = bookRepository.findById(bookId).get();
        User user = userService.findUserById(userId);
        if(user==null || book==null){
            log.info("User or Book Not found in your favourite list");
            return;
        }
        FavouriteList favouriteList = user.getFavouriteList();
        List<Book> books = favouriteList.getBooks();
        books.removeIf(book1 -> book1.getId().equals(bookId));
        favouriteList.setBooks(books);
        favouriteListRepository.save(favouriteList);
    }
}