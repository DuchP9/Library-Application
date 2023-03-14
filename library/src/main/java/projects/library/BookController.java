package projects.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getById(@PathVariable("id") Long id) {
        return bookService.getBookByID(id);
    }

    @GetMapping("/books-with-authors")
    public List<BookWithAuthorDTO> getAllBooksWithAuthor() {
        return bookService.getALlBooksWithAuthor();
    }

    @PostMapping("")
    public Book add(@RequestBody BookRequest bookRequest) {
        return bookService.saveBook(bookRequest);
    }

    @PostMapping("/list")
    public List<Book> addBooks(@RequestBody List<BookRequest> bookRequests) {
        return bookService.saveBookList(bookRequests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Long id, @RequestBody Book updatedBook) {

        Book book = bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok(book);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> partiallyUpdate(@PathVariable("id") Long id, @RequestBody Book updatedBook) {

        Book book = bookService.partiallyUpdateBook(id, updatedBook);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return 1;
    }

    @DeleteMapping("")
    public int deleteALl() {
        bookService.deleteAllBooks();
        return 1;
    }

}
