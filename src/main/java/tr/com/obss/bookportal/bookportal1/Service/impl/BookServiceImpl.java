package tr.com.obss.bookportal.bookportal1.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.obss.bookportal.bookportal1.Service.BookService;
import tr.com.obss.bookportal.bookportal1.model.Author;
import tr.com.obss.bookportal.bookportal1.model.Book;
import tr.com.obss.bookportal.bookportal1.repo.AuthorRepo;
import tr.com.obss.bookportal.bookportal1.repo.BookRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepo  bookRepository;
    private final AuthorRepo authorRepo;
    public BookServiceImpl(BookRepo bookRepository, AuthorRepo authorRepo) {
        this.bookRepository = bookRepository;
        this.authorRepo = authorRepo;
    }

    @Override
    public List<Book> getAllBooks() {
        final Iterable<Book> allBooks = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        allBooks.forEach(book -> {
            book.setNumber(book.getAuthor().getId());
            books.add(book);
        });
        return books;
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Boolean createNewBook(Book book) {
        if(book!=null){
            String name = book.getAuthor().getName();
            Author author = authorRepo.findByName(name).get();

            book.setAuthorName(author.getName());
            book.setAuthor(author);
            bookRepository.save(book);
        }
        return Boolean.TRUE;
    }

    public Book updateBook(Long id, Book book){
        Optional<Book> bookToBeUpdated  = bookRepository.findById(id);
        bookToBeUpdated.get().setAuthorName(book.getAuthorName());
        bookToBeUpdated.get().setName(book.getName());
        bookToBeUpdated.get().setDate(book.getDate());
        bookToBeUpdated.get().setPublisher(book.getPublisher());
        bookRepository.save(bookToBeUpdated.get());
        return bookToBeUpdated.get();
    }

    @Override
    public Boolean deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
        return Boolean.TRUE;
    }

    @Override
    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).get();
    }


}