package com.example.librarymanagement.DTO;

import lombok.Data;

@Data
public class BookDTO {

    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
    private boolean isAvailable;
}
