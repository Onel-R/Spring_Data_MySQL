package com.project.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private final BookValidator bookValidator = new BookValidator();

    // Create a new book
    @PostMapping("/addBook")
    public ResponseEntity<Object> createBook(@Valid @RequestBody Book book, BindingResult result) {
        bookValidator.validate(book, result);
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation failed: " + result.getAllErrors());
        }

        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }

    // Get all books
    @GetMapping("/getBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Get a book by ID
    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a book
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book updatedBook = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete a book
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/createBookResponse")
    public ResponseEntity<ApiResponse<Book>> createBookResponse(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        ApiResponse<Book> response = new ApiResponse<>("success", "Book created successfully", savedBook);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}