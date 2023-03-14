package projects.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.library.exception.AuthorNotFoundException;
import projects.library.exception.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<BookWithAuthorDTO> getALlBooksWithAuthor() {

        List<Book> books = bookRepository.findAll();
        List<BookWithAuthorDTO> booksWithAuthor = new ArrayList<>();

        for (Book book : books) {
            BookWithAuthorDTO bookWithAuthorDTO = new BookWithAuthorDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getYear(),
                    book.getAuthor().getName());

            booksWithAuthor.add(bookWithAuthorDTO);
        }

        return booksWithAuthor;
    }

    public Book saveBook(BookRequest bookRequest) {

        Optional<Author> optionalAuthor = authorRepository.findByName(bookRequest.getAuthor_name());

        if (optionalAuthor.isPresent()) {

            Author author = optionalAuthor.get();
            Book book = new Book();
            book.setTitle(bookRequest.getTitle());
            book.setYear(bookRequest.getYear());
            book.setAuthor(author);

            return bookRepository.save(book);

        } else {
         throw new AuthorNotFoundException(bookRequest.getAuthor_name());
        }
    }

    public List<Book> saveBookList(List<BookRequest> bookRequests) {
        List<Book> addedBooks = new ArrayList<>();
        for (BookRequest bookRequest : bookRequests) {
            Book addedBook = saveBook(bookRequest);
            addedBooks.add(addedBook);
        }

        return addedBooks;
    }

    public Optional<Book> getBookByID(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
           Book book = optionalBook.get();
           book.setTitle(updatedBook.getTitle());
           book.setAuthor(updatedBook.getAuthor());
           book.setYear(updatedBook.getYear());

           return bookRepository.save(book);
        } else {
            throw new BookNotFoundException(id);
        }
    }

    public Book partiallyUpdateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if(updatedBook.getTitle() != null) book.setTitle(updatedBook.getTitle());
            if(updatedBook.getAuthor() != null) book.setAuthor(updatedBook.getAuthor());
            if(updatedBook.getYear() != 0) book.setYear(updatedBook.getYear());

            return bookRepository.save(book);
        } else {
            throw new BookNotFoundException(id);
        }
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

}
