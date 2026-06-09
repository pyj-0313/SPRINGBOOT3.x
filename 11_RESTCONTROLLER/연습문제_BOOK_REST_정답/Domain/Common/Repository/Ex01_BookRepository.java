package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * [Ex01-정답] 도서 REST 실습 - BookRepository
 */
@Repository
public interface Ex01_BookRepository extends JpaRepository<Book, Long> {
    // 기본 CRUD/페이징은 JpaRepository 가 제공
}
