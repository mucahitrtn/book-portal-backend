package tr.com.obss.bookportal.bookportal1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.bookportal.bookportal1.Service.BookService;
import tr.com.obss.bookportal.bookportal1.model.Book;
import tr.com.obss.bookportal.bookportal1.model.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{name}")
    public List<Book> findBookByName(@PathVariable("name") String name){
        return bookService.findByName(name);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }
    @DeleteMapping("/delete/{id}")
    public Boolean deleteBook(@PathVariable("id") Long id){
        return bookService.deleteBookById(id);
    }

}