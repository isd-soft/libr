package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BookDto {
    private String title;
    private List <String> authors;
    private String publishedDate;
    private String description;
    private int pageCount;
    private List <String> categories;
    private int averageRating;
    private String language;
    private Status status;
}
