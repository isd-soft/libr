package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.BookAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookActionDto {
    private Long id;
    private BookDto books;
    private PersonDto persons;
    private String status;
    private LocalDateTime actionDate;

    public static BookActionDto from(BookAction bookAction, PersonDto personDto, BookDto bookDto) {
        BookActionDto result = new BookActionDto();
        result.setId(bookAction.getId());
        result.setPersons(personDto);
        result.setBooks(bookDto);
        result.setStatus(String.valueOf(bookAction.getStatus()));
        result.setActionDate(bookAction.getActionDate());
        return result;
    }
}
