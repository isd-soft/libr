package com.isd.libr.service;

import com.isd.libr.web.dto.BookActionDto;
import com.isd.libr.web.dto.BookDto;
import com.isd.libr.web.dto.DashboardDto;
import com.isd.libr.web.entity.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Implementation for {@link DashboardService}
 *
 * @author Grosu Kirill
 */
@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final UserService userService;
    private final BookService bookService;
    private final BookActionService bookActionService;
    private final VoteService voteService;
    private final BookReactionService bookReactionService;


    @Override
    @Transactional
    public DashboardDto getDashboardInfo() {
        Integer usersCount = userService.countUsers();
        Integer bookCount = bookService.countBooks();
        Integer voteCount = voteService.countVotes();
        Integer heartReactionsCount = bookReactionService.countReactionTypes(ReactionType.HEART);
        Integer laughReactionsCount = bookReactionService.countReactionTypes(ReactionType.LAUGH);
        Integer sadReactionsCount = bookReactionService.countReactionTypes(ReactionType.SAD);
        List<Map<String, Integer>> booksPerCategory = bookService.getAllSortedUniqueCategories();
        List<BookActionDto> bookActionsLastMonth = bookActionService.getAllSubmissionActionsLastMonthForDashboard();
        List<BookDto> bookDtos = bookService.listBooksWithNotStatusRejected();
        BookDto mostUpVoted = bookDtos.get(0);
        BookDto mostDownVoted = bookDtos.get(bookDtos.size() - 1);
        Map<String, Integer> submittedAndInLibrary = bookActionService.getAllSubmittedAndInLibrary();
        DashboardDto dashboardDto = new DashboardDto();

        dashboardDto.setUsersCount(usersCount);
        dashboardDto.setBooksCount(bookCount);
        dashboardDto.setVotesCount(voteCount);
        dashboardDto.setHeartsPerBook(heartReactionsCount);
        dashboardDto.setSadPerBook(sadReactionsCount);
        dashboardDto.setLaughsPerBook(laughReactionsCount);
        dashboardDto.setBooksPerCategory(booksPerCategory);
        dashboardDto.setSubmittedPerWeek(bookActionsLastMonth);
        dashboardDto.setMostUpVotedBook(mostUpVoted);
        dashboardDto.setMostDownVotedBook(mostDownVoted);
        dashboardDto.setAllSubmittedAndInLibraryBooks(submittedAndInLibrary);

        return dashboardDto;
    }
}
