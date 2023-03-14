package projects.library;

import lombok.Data;

@Data
public class BookWithAuthorDTO {

    private Long book_id;
    private String title;

    private int year;

    private String authorName;

    public BookWithAuthorDTO(Long id, String title, int year, String authorName) {
        this.book_id = id;
        this.title = title;
        this.year = year;
        this.authorName = authorName;
    }
}
