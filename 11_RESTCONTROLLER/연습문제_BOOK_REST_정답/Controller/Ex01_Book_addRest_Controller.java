package com.example.demo.Controller;

import com.example.demo.Domain.Common.Dtos.Ex01_BookDTO;
import com.example.demo.Domain.Common.Dtos.PageDTO;
import com.example.demo.Domain.Common.Service.Ex01_BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * [Ex01-정답] 도서 REST 실습 - RestController
 */
@Controller
@Slf4j
@RequestMapping("/book")
public class Ex01_Book_addRest_Controller {

    @Autowired
    private Ex01_BookService bookService;

    @ExceptionHandler
    public String SQLExceptionHandler(Exception e, Model model) {
        log.error("BOOK SQLEXCEPTION.." + e);
        model.addAttribute("ex", e.getMessage());
        e.printStackTrace();
        return "book/error";
    }

    // ---------------------------------------------- REST HANDLER ----------------------------------------------

    @GetMapping("/rest/xhr")
    public String restIndex_xhr() {
        log.info("GET /book/rest/xhr");
        return "book/rest/xhr";
    }

    @GetMapping("/rest/fetch")
    public String restIndex_fetch() {
        log.info("GET /book/rest/fetch");
        return "book/rest/fetch";
    }

    @GetMapping("/rest/ajax")
    public String restIndex_ajax() {
        log.info("GET /book/rest/ajax");
        return "book/rest/ajax";
    }

    @GetMapping("/rest/axios")
    public String restIndex_axios() {
        log.info("GET /book/rest/axios");
        return "book/rest/axios";
    }

    @ResponseBody
    @GetMapping(value = "/rest/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> rest_list_get(PageDTO pageDTO) throws Exception {
        log.info("GET /book/rest/list..." + pageDTO);
        Map<String, Object> responseMap = new HashMap<>();

        Map<String, Object> r = bookService.getBookList(pageDTO);
        responseMap.put("page", r.get("page"));
        responseMap.put("list", r.get("list"));
        responseMap.put("pageBlock", r.get("pageBlock"));

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @ResponseBody
    @PostMapping(value = "/rest/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> bookAddPost_rest(@RequestBody @Valid Ex01_BookDTO dto, BindingResult bindingResult) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();
        log.info("POST /book/rest/add..." + dto);

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.info("Error Field : " + error.getField() + " Error Message : " + error.getDefaultMessage());
                responseMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }

        bookService.bookRegistration(dto);

        responseMap.put("message", "도서추가 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @ResponseBody
    @PutMapping(value = "/rest/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> bookUpdatePut_rest(@RequestBody @Valid Ex01_BookDTO dto, BindingResult bindingResult) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();
        log.info("PUT /book/rest/update..." + dto);

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.info("Error Field : " + error.getField() + " Error Message : " + error.getDefaultMessage());
                responseMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
        }

        bookService.updateBook(dto);

        responseMap.put("message", "도서수정 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @ResponseBody
    @DeleteMapping(value = "/rest/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> bookDelete_rest(@RequestBody Ex01_BookDTO dto) throws Exception {
        Map<String, Object> responseMap = new HashMap<>();
        log.info("DELETE /book/rest/delete..." + dto.getBookCode());

        bookService.removeBook(dto.getBookCode());

        responseMap.put("message", "도서삭제 성공!");
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
}
