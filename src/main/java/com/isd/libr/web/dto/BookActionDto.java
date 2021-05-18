package com.isd.libr.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookActionDto {
    private Long id;
    private BookDto books;
    private PersonDto persons;
    private String status;
    private LocalDateTime actionDate;
}
