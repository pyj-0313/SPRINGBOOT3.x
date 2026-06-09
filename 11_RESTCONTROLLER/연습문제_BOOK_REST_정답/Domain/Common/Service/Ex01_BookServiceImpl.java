package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dtos.Ex01_BookDTO;
import com.example.demo.Domain.Common.Dtos.PageBlock;
import com.example.demo.Domain.Common.Dtos.PageDTO;
import com.example.demo.Domain.Common.Entity.Book;
import com.example.demo.Domain.Common.Repository.Ex01_BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * [Ex01-정답] 도서 REST 실습 - BookServiceImpl
 */
@Service
public class Ex01_BookServiceImpl implements Ex01_BookService {

    @Autowired
    private Ex01_BookRepository bookRepository;

    //도서등록
    @Override
    @Transactional(rollbackFor = SQLException.class, transactionManager = "jpaTransactionManager")
    public boolean bookRegistration(Ex01_BookDTO dto) throws Exception {
        bookRepository.save(dto.toEntity());
        return true;
    }

    //도서리스트(+페이징)
    @Override
    @Transactional(rollbackFor = SQLException.class, transactionManager = "jpaTransactionManager")
    public Map<String, Object> getBookList(PageDTO pageDTO) throws Exception {

        Map<String, Object> returnValue = new HashMap<>();

        int pageNo = 0;     //현재 pageNo
        int amount = 10;    //한 페이지 표시 건수
        if (pageDTO.getPageNo() != null)
            pageNo = pageDTO.getPageNo();
        else
            pageDTO.setPageNo(0);

        if (pageDTO.getAmount() != null)
            amount = pageDTO.getAmount();
        else
            pageDTO.setAmount(10);

        Pageable pageable = PageRequest.of(pageNo, amount, Sort.by("bookCode").descending());
        Page<Book> page = bookRepository.findAll(pageable);
        PageBlock pageBlock = new PageBlock(pageDTO, page);
        List<Book> list = page.getContent();

        returnValue.put("page", page);
        returnValue.put("pageBlock", pageBlock);
        returnValue.put("list", list);

        return returnValue;
    }

    //단건조회
    @Override
    @Transactional(rollbackFor = SQLException.class, transactionManager = "jpaTransactionManager")
    public Ex01_BookDTO getBook(Long bookCode) throws Exception {
        Optional<Book> bookOptional = bookRepository.findById(bookCode);
        if (bookOptional.isPresent()) {
            return Ex01_BookDTO.from(bookOptional.get());
        }
        return null;
    }

    //수정
    @Override
    @Transactional(rollbackFor = SQLException.class, transactionManager = "jpaTransactionManager")
    public boolean updateBook(Ex01_BookDTO dto) throws Exception {
        bookRepository.save(dto.toEntity());
        return dto.getBookCode() > 0;
    }

    //삭제
    @Override
    @Transactional(rollbackFor = SQLException.class, transactionManager = "jpaTransactionManager")
    public boolean removeBook(Long bookCode) throws Exception {
        bookRepository.deleteById(bookCode);
        return true;
    }
}
