package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Comment;
import com.isd.libr.web.entity.Status;
import com.isd.libr.web.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String description;
    private int pageCount;
    private List<String> categories;
    private int averageRating;
    private String language;
    private Status status;
    private List<HashMap<String, String>> industryIdentifiers;
    private Map<String, String> imageLinks;
    private List<CommentDto> comments;
    private Integer vote;

    public static BookDto from(Book book, List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = CommentDto.from(comment);
            commentDtos.add(commentDto);
        }
        int votes = book.getVotes().stream().map(Vote::getVote).mapToInt(Integer::intValue).sum();
        BookDto result = from(book);
        result.setComments(commentDtos);
        result.setVote(votes);
        return result;
    }

    public static BookDto from(Book book) {
        BookDto result = new BookDto();
        result.setTitle(book.getTitle());
        result.setAuthors(book.getAuthors());
        result.setPublishedDate(book.getPublishedDate());
        result.setDescription(book.getDescription());
        result.setPageCount(book.getPageCount());
        result.setCategories(book.getCategories());
        result.setAverageRating(book.getAverageRating());
        result.setLanguage(book.getLanguage());
        result.setIndustryIdentifiers(book.getIndustryIdentifiers());
        result.setImageLinks(book.getImageLinks());
        return result;
    }
}
