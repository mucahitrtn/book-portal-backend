package tr.com.obss.bookportal.bookportal1.Service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.obss.bookportal.bookportal1.Service.AuthorService;
import tr.com.obss.bookportal.bookportal1.Service.BookService;
import tr.com.obss.bookportal.bookportal1.model.Author;
import tr.com.obss.bookportal.bookportal1.model.Book;
import tr.com.obss.bookportal.bookportal1.repo.AuthorRepo;

import java.util.List;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepository;
    @Autowired
    private final BookService bookService;

    public List<Author> getAllAuthors(){
        return (List<Author>) authorRepository.findAll();
    }

    public AuthorServiceImpl(AuthorRepo authorRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.bookService = bookService;
    }

    @Override
    public void createNewAuthor(Author author) {
        List<Book> books = author.getBooks();
        final Author author1 = authorRepository.save(author);
        if(books!= null){
            books.forEach(book -> {
                book.setAuthor(author1);
                book.setAuthorName(author1.getName());
                Boolean b = bookService.createNewBook(book);
                log.info("Book creation: "+ b);
            });
        }
        else{
            log.info("books are null");
        }
    }

    public Boolean deleteAuthorById(Long authorId){
        authorRepository.deleteById(authorId);
        return Boolean.TRUE;
    }

    @Override
    public Boolean removeBookFromAuthor(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId).get();
        List<Book> oldBooks = author.getBooks();

        oldBooks.removeIf(book -> book.equals(bookService.findBookById(bookId)));

        author.setBooks(oldBooks);
        authorRepository.save(author);

        return Boolean.TRUE;
    }

    public Author findAuthorById(Long id){
        return authorRepository.findById(id).get();
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name).get();
    }

    @Override
    public void updateExistingAuthor(Long authorId, Book newBook) {
        Author oldAuthor = authorRepository.findById(authorId).get();
        List<Book> oldBooks = oldAuthor.getBooks();

        newBook.setNumber(oldAuthor.getId());
        newBook.setAuthor(oldAuthor);
        newBook.setAuthorName(oldAuthor.getName());
        oldBooks.add(newBook);

        oldAuthor.setBooks(oldBooks);
        authorRepository.save(oldAuthor);
    }


}