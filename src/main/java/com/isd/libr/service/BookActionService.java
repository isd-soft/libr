package com.isd.libr.service;

import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookActionInfoDto;
import com.isd.libr.web.dto.requests.BookInfoRequest;
import com.isd.libr.web.dto.requests.UpdateBooksStatusRequest;
import com.isd.libr.web.entity.BookAction;
import com.isd.libr.web.entity.Status;

import java.util.List;
import java.util.Map;

/**
 * BookActionService is an interface for {@link BookAction} entity.
 *
 * <p>Preferred implementation {@code BookActionImpl}</p>
 *
 * @author Grosu Kirill
 */
public interface BookActionService {
    /**
     * Return all BookActions that have last status of provided one.
     *
     * @param status desired last status to be returned.
     * @return list of all BookActions that have provided status converted into Data Transfer Objects.
     */
    List<BookActionDto> getByStatus(Status status);

    /**
     * Creates a new row in database with provided book and user. Sets the status to provided one.
     *
     * @param request object of type {@link UpdateBooksStatusRequest} containing fields: <ul>
     *                <li>Book ID</li>
     *                <li>User ID</li>
     *                <li>New status</li>
     *                </ul>
     * @return a newly created instance of class {@link BookAction} in database converted into Data Transfer Object.
     */
    BookActionDto updateStatus(UpdateBooksStatusRequest request);

    /**
     * Returns last BookAction on a specific book by providing Book ID and status.
     *
     * @param request object of type {@link BookInfoRequest} containing fields: <ul>
     *                <li>Book ID</li>
     *                <li>Status</li>
     *                </ul>
     * @return {@link BookAction} converted into {@link BookActionInfoDto}.
     */
    BookActionInfoDto getInfo(BookInfoRequest request);

    /**
     * Used for creating statistics endpoint. Returns all {@link BookAction} instances present in database created in a period of last month.
     *
     * @return list a {@link BookAction} converted into Data Transfer Objects.
     */
    List<BookActionDto> getAllSubmissionActionsLastMonthForDashboard();

    /**
     * Used for creating statistics endpoint. Returns amount of books with statuses <i>SUBMITTED</i> and <i>IN_LIBRARY</i>
     *
     * @return Map containing Status as String Key and amount of books with this status as value
     */
    Map<String, Integer> getAllSubmittedAndInLibrary();
}
