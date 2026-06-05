package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dtos.MemoDTO;

public interface MemoService {
    //메모등록
    boolean memoRegistration(MemoDTO memoDTO) throws Exception;
}
