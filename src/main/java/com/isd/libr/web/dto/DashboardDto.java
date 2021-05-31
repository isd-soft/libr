package com.isd.libr.web.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DashboardDto {

     private int usersCount;
     private int booksCount;
     private int votesCount;
     private int heartsPerBook;
     private int laughsPerBook;
     private int sadPerBook;
     private int submittedPerWeek;
     private int booksPerCategory;
     private String mostUpVotedBook;
     private String mostDownVotedBook;
     private int allSubmittedBooks;
     private int allInLibraryBooks;


     }










