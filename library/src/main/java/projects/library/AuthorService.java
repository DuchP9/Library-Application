package projects.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.library.exception.AuthorNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> saveAuthorList(List<Author> authors) {
        List<Author> addedAuthors = new ArrayList<>();
        for (Author author : authors) {
            if (authorRepository.findByName(author.getName()).isPresent()) {
                continue;
            }
            Author addedAuthor = authorRepository.save(author);
            addedAuthors.add(addedAuthor);
        }
        return  addedAuthors;
    }
    public Optional<Author> getAuthorByID(Long id) {
        return authorRepository.findById(id);
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setName(updatedAuthor.getName());

            return authorRepository.save(author);
        } else {
            throw new AuthorNotFoundException(id);
        }
    }

    public void deleteAuthorByID(Long id) {
        authorRepository.deleteById(id);
    }

    public void deleteAllAuthors() {
        authorRepository.deleteAll();
    }

}
