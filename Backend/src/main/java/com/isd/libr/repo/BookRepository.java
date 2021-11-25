package com.isd.libr.repo;

import com.isd.libr.web.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getByTitle(String title);

    @Query(nativeQuery = true, value = "select b.* " +
            "from (select ba.*, ROW_NUMBER() OVER(PARTITION BY ba.book_id ORDER BY ba.action_date desc)  " +
            "from book_action ba " +
            "order by ba.action_date desc) as actions_ranked join book b on b.id=actions_ranked.book_id  " +
            "where actions_ranked.row_number=1  " +
            "and actions_ranked.status not in ('REJECTED')")
    List<Book> listBooksWithNotStatusRejected();

    @Query(nativeQuery = true, value = "select category, count(book_id) from categories group by category order by count(book_id) desc")
    List<Map<String, Integer>> getAllSortedUniqueCategories();


      Integer countBooksByIdGreaterThan(Long start);
}
