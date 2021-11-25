package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Book;
import com.isd.libr.web.entity.Comment;
import com.isd.libr.web.entity.ReactionType;
import com.isd.libr.web.entity.Status;
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
    private Long id;
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private Integer pageCount;
    private List<String> categories;
    private Integer averageRating;
    private Integer ratingsCount;
    private String language;
    private Status status;
    private String maturityRating;
    private List<HashMap<String, String>> industryIdentifiers;
    private Map<String, String> imageLinks;
    private List<CommentDto> comments;
    private Integer vote;
    private Map<ReactionType, Long> reactions;
    private String previewLink;

    public static BookDto from(Book book, List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = CommentDto.from(comment);
            commentDtos.add(commentDto);
        }
        int votes = book.getSumOfVotes();
        Map<ReactionType, Long> reactions = book.getBookReactions();
        BookDto result = from(book);
        result.setComments(commentDtos);
        result.setVote(votes);
        result.setReactions(reactions);
        return result;
    }

    public static BookDto from(Book book) {
        BookDto result = new BookDto();
        result.setId(book.getId());
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
        result.setStatus(book.getLastStatus());
        result.setPublisher(book.getPublisher());
        result.setMaturityRating(book.getMaturityRating());
        result.setRatingsCount(book.getRatingCount());
        result.setPreviewLink(book.getPreviewLink());
        return result;
    }
}
