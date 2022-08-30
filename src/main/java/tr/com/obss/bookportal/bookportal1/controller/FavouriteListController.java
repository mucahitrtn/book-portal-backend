package tr.com.obss.bookportal.bookportal1.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.bookportal.bookportal1.Service.FavouriteListService;
import tr.com.obss.bookportal.bookportal1.model.Book;

import java.util.List;

@RestController
@RequestMapping("api/favouritelist")
@RequiredArgsConstructor
public class FavouriteListController {

    @Autowired
    private final FavouriteListService favouriteListService;

    @GetMapping("/{userId}")
    public List<Book> getFavouriteBooks(@PathVariable("userId") Long userId){
        return favouriteListService.findFavouriteListById(userId);
    }

    @PostMapping("/{id}/{bookId}")
    public Boolean addToFavourite(@PathVariable("id") Long id, @PathVariable("bookId") Long bookId){
        favouriteListService.addBookToFavouriteList(id, bookId);
        return Boolean.TRUE;
    }

    @DeleteMapping("/{id}/{bookId}")
    public Boolean deleteFromFavourite(@PathVariable("id") Long id,@PathVariable("bookId") Long bookId){
        favouriteListService.deleteFromFavouriteList(id, bookId);
        return Boolean.TRUE;
    }

}
