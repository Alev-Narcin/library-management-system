package com.alevnarcin.librarymanagementsystem.validation;

import com.alevnarcin.librarymanagementsystem.dto.BookDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookValidation {

    public void validateBook(BookDto bookDto) {
        if (bookDto.getAuthorNames() == null || bookDto.getAuthorNames().equals("")) {
            throw new NullPointerException("Author can not be null");
        }
    }
}
