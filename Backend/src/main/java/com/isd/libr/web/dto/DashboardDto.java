package com.isd.libr.web.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DashboardDto {

     private Integer usersCount;
     private Integer booksCount;
     private Integer votesCount;
     private Integer heartsPerBook;
     private Integer laughsPerBook;
     private Integer sadPerBook;
     private Map<Integer, Integer> submittedPerWeek;
     private List<Map<String, Integer>> booksPerCategory;
     private BookDto mostUpVotedBook;
     private BookDto mostDownVotedBook;
     private Map<String, Integer> allSubmittedAndInLibraryBooks;


     }










