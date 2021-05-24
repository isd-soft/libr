package com.isd.libr.web.dto;

import com.isd.libr.web.entity.BookAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookActionDto {
    private Long id;
    private BookDto books;
    private UserDto users;
    private String status;
    private LocalDateTime actionDate;

    public static BookActionDto from(BookAction bookAction, UserDto userDto, BookDto bookDto) {
        BookActionDto result = new BookActionDto();
        result.setId(bookAction.getId());
        result.setUsers(userDto);
        result.setBooks(bookDto);
        result.setStatus(String.valueOf(bookAction.getStatus()));
        result.setActionDate(bookAction.getActionDate());
        return result;
    }
}
