package com.isd.libr.service;

import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.requests.CreateBookRequest;
import com.isd.libr.web.dto.requests.UpdateBookRequest;
import com.isd.libr.web.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * BookService is an interface for {@link Book} entity.
 *
 * <p>Preferred implementation {@code BookServiceImpl}</p>
 *
 * @author Grosu Kirill
 */
public interface BookService {
    /**
     * Returns all the books which doesn't have status Rejected. Filters result List by votes.
     *
     * @return a list of {@link BookDto} a Data Transfer Object for Book entity
     * @since 1.0
     */
    List<BookDto> listBooksWithNotStatusRejected();

    /**
     * Returns all instances of class {@link Book} regardless of it's status.
     *
     * @return a list of all books converted into {@link BookDto}
     * @since 1.0
     */
    List<BookDto> findAll();

    /**
     * Returns a {@link Book} in format of {@link BookDto} if present in database, in other case returns an empty object.
     *
     * @param id unique identifier for Book, used to find specific instance in database
     * @return a {@link Book} in format of {@link BookDto} if present in database, in other case returns an empty object.
     * @since 1.0
     */
    BookDto getById(long id);

    /**
     * Creates an object of class {@link Book} from provided data and inserts it into database. Used for creating Book from data provided from Google API.
     *
     * @param request object of type {@link CreateBookRequest} with fields: <ul>
     *                <li>Title</li>
     *                <li>Authors</li>
     *                <li>Publisher</li>
     *                <li>PublishedDate</li>
     *                <li>Description</li>
     *                <li>PageCount</li>
     *                <li>ImageLinks</li>
     *                <li>etc.</li>
     *                </ul>
     * @see <a href="https://developers.google.com/books">Google Book API</a>
     * @since 1.0
     */
    void save(CreateBookRequest request);

    /**
     * Deletes an instance of Book in database if present by providing it's id.
     *
     * @param id unique identifier for {@link Book} that will be used to delete a specific Book instance.
     * @since 1.0
     */
    void deleteBookById(long id);

    /**
     * Updates a specific Book by providing it's ID.
     *
     * @param id      unique identifier for {@link Book} that will be used to find a specific Book instance.
     * @param request object of type {@link UpdateBookRequest} containing fields: <ul>
     *                <li>Title</li>
     *                <li>Authors</li>
     *                <li>Publisher</li>
     *                <li>PublishedDate</li>
     *                <li>Description</li>
     *                <li>PageCount</li>
     *                <li>ImageLinks</li>
     *                <li>etc.</li>
     *                </ul>
     * @return an updated {@link Book} in format of {@link BookDto}.
     * @since 1.0
     */
    BookDto updateBook(Long id, UpdateBookRequest request);

    /**
     * Return all categories present in database and amount of books that have this category. Sorts them by amount of books.
     *
     * @return Return all categories present in database and amount of books that have this category.
     */
    List<Map<String, Integer>> getAllSortedUniqueCategories();

    /**
     * Counts all books present in database.
     *
     * @return number of all books present in database.
     */
    Integer countBooks();
}
