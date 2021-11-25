package com.isd.libr.web.dto;

import com.isd.libr.web.entity.BookAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookActionDto {
    private Long id;
    private BookDto book;
    private UserDto user;
    private String status;
    private String actionDate;


    public static BookActionDto from(BookAction bookAction, UserDto userDto, BookDto bookDto) {
        BookActionDto result = new BookActionDto();
        result.setId(bookAction.getId());
        result.setUser(userDto);
        result.setBook(bookDto);
        result.setStatus(String.valueOf(bookAction.getStatus()));
        result.setActionDate(bookAction.getActionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return result;
    }



}
