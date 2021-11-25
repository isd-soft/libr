package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Comment;
import com.isd.libr.web.entity.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
    private UserDto userDto;
    private Long id;
    private BookDto bookDto;
    private String comment;
    private String date;
    private Map<ReactionType, Long> reactions;

    public static CommentDto from(Comment comment) {
        UserDto userDto = UserDto.from(comment.getUser());
        BookDto bookDto = BookDto.from(comment.getBook());
        Map<ReactionType, Long> reactions = comment.getCommentReaction();

        CommentDto result = new CommentDto();
        result.setId(comment.getId());
        result.setBookDto(bookDto);
        result.setUserDto(userDto);
        result.setComment(comment.getComment());
        result.setDate(comment.getDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        result.setReactions(reactions);
        return result;
    }


}
