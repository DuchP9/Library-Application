package projects.library;

import lombok.Data;

@Data
public class BookRequest {

    private String title;

    private int year;

    private String author_name;
}
