package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    private UserDto userDto;
    private Long id;
    private BookDto bookDto;
    private String comment;

    public static CommentDto from(Comment comment) {
        UserDto userDto = UserDto.from(comment.getUser());
        BookDto bookDto = BookDto.from(comment.getBook());
        CommentDto result = new CommentDto();
        result.setId(comment.getId());
        result.setBookDto(bookDto);
        result.setUserDto(userDto);
        result.setComment(comment.getComment());
        return result;
    }

}