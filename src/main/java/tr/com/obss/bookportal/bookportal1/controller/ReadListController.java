package tr.com.obss.bookportal.bookportal1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.bookportal.bookportal1.Service.ReadListService;
import tr.com.obss.bookportal.bookportal1.model.Book;

import java.util.List;

@RestController
@RequestMapping("api/readlist")
@RequiredArgsConstructor
public class ReadListController {

    @Autowired
    private final ReadListService readListService;
    @GetMapping("/{userId}")
    public List<Book> getReadListOfUser(@PathVariable("userId") Long userId){
        return readListService.findReadListById(userId);
    }

    @PostMapping("/{id}/{bookId}")
    public Boolean addToReadList(@PathVariable("id") Long id, @PathVariable("bookId") Long bookId){
        readListService.addBookToReadList(id, bookId);
        return Boolean.TRUE;
    }

    @DeleteMapping("/{id}/{bookId}")
    public Boolean deleteFromReadList(@PathVariable("id") Long id,@PathVariable("bookId") Long bookId){
        readListService.deleteFromReadList(id, bookId);
        return Boolean.TRUE;
    }


}
