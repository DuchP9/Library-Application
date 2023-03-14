package projects.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public List<Author> getAll() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Optional<Author> getByID(@PathVariable("id") Long id) {
        return authorService.getAuthorByID(id);
    }

    @PostMapping("")
    public Author add(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @PostMapping("/list")
    public List<Author> addAuthors(@RequestBody List<Author> authors) {
        return authorService.saveAuthorList(authors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Long id, @RequestBody Author updatedAuthor) {

        Author author = authorService.updateAuthor(id, updatedAuthor);
        return ResponseEntity.ok(author);
    }
/*
    @PatchMapping("/{id}")
    public ResponseEntity<Author> partiallyUpdate(@PathVariable("id") Long id, @RequestBody Author updatedAuthor) {

        Author author = authorService.partiallyUpdateAuthor(id, updatedAuthor);
        return ResponseEntity.ok(author);
    }
    */
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") Long id) {
        authorService.deleteAuthorByID(id);
        return 1;
    }

    @DeleteMapping("")
    public int deleteAll() {
        authorService.deleteAllAuthors();
        return 1;
    }


}
