package com.example.demo.Domain.Common.Dtos;

import com.example.demo.Domain.Common.Entity.Book;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [Ex01-정답] 도서 REST 실습 - BookDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ex01_BookDTO {

    @NotNull(message = "BOOKCODE 는 필수 항목입니다.")
    private Long bookCode;

    @NotBlank(message = "BOOKNAME 는 필수 항목입니다.")
    private String bookName;

    @NotBlank(message = "PUBLISHER 는 필수 항목입니다.")
    private String publisher;

    @NotBlank(message = "ISBN 는 필수 항목입니다.")
    private String isbn;

    public Book toEntity() {
        return Book.builder()
                .bookCode(this.bookCode)
                .bookName(this.bookName)
                .publisher(this.publisher)
                .isbn(this.isbn)
                .build();
    }

    public static Ex01_BookDTO from(Book book) {
        return Ex01_BookDTO.builder()
                .bookCode(book.getBookCode())
                .bookName(book.getBookName())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .build();
    }
}
