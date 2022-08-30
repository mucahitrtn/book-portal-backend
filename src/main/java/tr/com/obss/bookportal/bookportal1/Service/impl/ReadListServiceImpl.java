package tr.com.obss.bookportal.bookportal1.Service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.obss.bookportal.bookportal1.Service.ReadListService;
import tr.com.obss.bookportal.bookportal1.Service.UserService;
import tr.com.obss.bookportal.bookportal1.model.Book;
import tr.com.obss.bookportal.bookportal1.model.FavouriteList;
import tr.com.obss.bookportal.bookportal1.model.ReadList;
import tr.com.obss.bookportal.bookportal1.model.User;
import tr.com.obss.bookportal.bookportal1.repo.BookRepo;
import tr.com.obss.bookportal.bookportal1.repo.FavouriteListRepo;
import tr.com.obss.bookportal.bookportal1.repo.ReadListRepo;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ReadListServiceImpl implements ReadListService {

    @Autowired
    private final UserService userService;
    @Autowired
    private final BookRepo bookRepository;
    @Autowired
    private final ReadListRepo readListRepo;

    @Override
    public List<Book> findReadListById(Long userId) {

        User user = userService.findUserById(userId);
        ReadList readList = user.getReadList();
        return readList.getBooks();

    }

    @Override
    public void addBookToReadList(Long userId, Long bookId) {

        if(userId==null || bookId==null){
            log.info("User id or Book is null");
            return;
        }

        Book book  = bookRepository.findById(bookId).get();
        User user = userService.findUserById(userId);

        if(user==null || book==null){
            log.info("User or Book Not found in your read list");
            return;
        }

        ReadList readList = user.getReadList();
        List<Book> books = readList.getBooks();
        books.add(book);
        readList.setBooks(books);
        readListRepo.save(readList);
    }

    @Override
    public void deleteFromReadList(Long userId, Long bookId) {

        if(userId==null || bookId==null){
            log.info("User id or Book is null");
            return;
        }

        Book book  = bookRepository.findById(bookId).get();
        User user = userService.findUserById(userId);

        if(user==null || book==null){
            log.info("User or Book Not found in your read list");
            return;
        }

        ReadList readList = user.getReadList();
        List<Book> books = readList.getBooks();
        books.removeIf(book1 -> book1.getId().equals(bookId));
        readList.setBooks(books);
        readListRepo.save(readList);
    }

}
