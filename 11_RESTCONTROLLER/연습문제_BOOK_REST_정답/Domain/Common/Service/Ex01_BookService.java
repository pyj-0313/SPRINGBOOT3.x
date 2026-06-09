package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dtos.Ex01_BookDTO;
import com.example.demo.Domain.Common.Dtos.PageDTO;

import java.util.Map;

/**
 * [Ex01-정답] 도서 REST 실습 - BookService
 */
public interface Ex01_BookService {
    //도서등록
    boolean bookRegistration(Ex01_BookDTO dto) throws Exception;

    //도서리스트(+페이징)
    Map<String, Object> getBookList(PageDTO pageDTO) throws Exception;

    //단건조회
    Ex01_BookDTO getBook(Long bookCode) throws Exception;

    //수정
    boolean updateBook(Ex01_BookDTO dto) throws Exception;

    //삭제
    boolean removeBook(Long bookCode) throws Exception;
}
