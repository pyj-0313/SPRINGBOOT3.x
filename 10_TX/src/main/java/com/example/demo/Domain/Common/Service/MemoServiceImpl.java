package com.example.demo.Domain.Common.Service;


import com.example.demo.Domain.Common.Dtos.MemoDTO;
import com.example.demo.Domain.Common.Repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemoServiceImpl implements MemoService {
    @Autowired
    private MemoRepository memoRepository;

    //메모등록
    @Override
    public boolean memoRegistration(MemoDTO memoDTO)throws Exception{
        memoDTO.setCreateAt(LocalDateTime.now());
        memoRepository.save(memoDTO.toEntity());
        return true;
    }


}
