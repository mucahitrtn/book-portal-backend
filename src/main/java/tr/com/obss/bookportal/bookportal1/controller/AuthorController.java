package tr.com.obss.bookportal.bookportal1.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.bookportal.bookportal1.Service.AuthorService;
import tr.com.obss.bookportal.bookportal1.model.Author;
import tr.com.obss.bookportal.bookportal1.model.Book;

import javax.validation.constraints.Past;
import java.util.List;

@RestController
@RequestMapping("api/author")
@AllArgsConstructor
public class AuthorController {

    @Autowired
    private final AuthorService authorService ;
    @GetMapping("/authors")
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping("/create")
    public Boolean createNewAuthor(@RequestBody Author author){
        authorService.createNewAuthor(author);
        return Boolean.TRUE;
    }

    @PutMapping("/createBook/{authorId}")
    public Boolean addBookToAuthor(@PathVariable("authorId") Long id,@RequestBody Book book){
        authorService.updateExistingAuthor(id, book);
        return Boolean.TRUE;
    }

    @DeleteMapping("/delete/{authorId}")
    public Boolean deleteAuthor(@PathVariable("authorId") Long id){
        return authorService.deleteAuthorById(id);
    }

}