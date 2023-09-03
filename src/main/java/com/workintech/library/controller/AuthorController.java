package com.workintech.library.controller;

import com.workintech.library.entity.Author;
import com.workintech.library.entity.Book;
import com.workintech.library.entity.Category;
import com.workintech.library.mapping.AuthorResponse;
import com.workintech.library.mapping.BookResponse;
import com.workintech.library.service.AuthorService;
import com.workintech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<AuthorResponse> findAll() {
        List<Author> authors = authorService.findAll();
        List<AuthorResponse> authorResponses = new ArrayList<>();
        for(Author author: authors) {
            List<BookResponse>  bookResponses = new ArrayList<>();
            for(Book book: author.getBooks()) {
                bookResponses.add(new BookResponse(book.getId(), book.getName(), book.getCategory().getName()));
            }
            authorResponses.add(new AuthorResponse(author.getId(), author. getFirstName(), author.getLastName(), bookResponses));
        }

        return authorResponses;
    }

    @PostMapping("/")
    public AuthorResponse save(@RequestBody Author author) {
        Author savedAuthor = authorService.save(author);
        return new AuthorResponse(savedAuthor.getId(), savedAuthor.getFirstName(), savedAuthor.getLastName(), null);
    }

    @PostMapping("/{bookId}")
    public AuthorResponse save(@RequestBody Author author, @PathVariable int bookId) {
        Book book = bookService.findById(bookId);
        author.addBook(book);
        book.setAuthor(author);
        authorService.save(author);
        return new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName(), null);
    }
    @PutMapping("/{authorId}")
    public Author update(@RequestBody Author author, @PathVariable int authorId) {
        Author foundAuthor = authorService.findById(authorId);
        if(foundAuthor != null) {
            author.setId(authorId);
            return authorService.save(author);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public Author delete(@PathVariable int id) {
        Author foundAuthor = authorService.findById(id);
        if(foundAuthor != null) {
            authorService.delete(foundAuthor);
            return foundAuthor;
        }

        return null;
    }
}
