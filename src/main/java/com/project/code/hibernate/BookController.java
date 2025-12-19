package com.project.code.hibernate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class BookController {

    @Autowired
    private BookService bookService;

    // Create a new book
    @PostMapping("/saveBook")
    public String saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return "Book saved with ID: " + book.getId();
    }

    // Get all books
    @GetMapping("/getBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Get book by ID
    @GetMapping("/getBook/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // Update a book by ID
    @PutMapping("/updateBook/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        bookService.updateBook(book);
        return "Book updated with ID: " + id;
    }

    // Delete a book by ID
    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book deleted with ID: " + id;
    }
}