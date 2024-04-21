package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.community.Community;
import com.study.Ex14ReadDB.domain.customerqna.CustomerQna;
import com.study.Ex14ReadDB.domain.customerqna.CustomerQnaRepository;
import com.study.Ex14ReadDB.dto.CommunityResponseDto;
import com.study.Ex14ReadDB.dto.CustomerQnaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerQnaService {
    @Autowired
    private final CustomerQnaRepository customerQnaRepository;

    @Transactional(readOnly = true)
    public List<CustomerQnaDto> getAllNotices() {
        List<CustomerQna> qnas = customerQnaRepository.findAll();
        return qnas.stream()
                .map(CustomerQnaDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerQnaDto> searchByQnaTitle(String keyword) {
        List<CustomerQna> customers = customerQnaRepository.searchByQnaTitle(keyword);
        return customers.stream()
                .map(CustomerQnaDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerQnaDto> searchByQnaContent(String keyword) {
        List<CustomerQna> customers = customerQnaRepository.searchByQnaContent(keyword);
        return customers.stream()
                .map(CustomerQnaDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CustomerQnaDto> searchByQnaName(String keyword) {
        List<CustomerQna> customers = customerQnaRepository.searchByQnaName(keyword);
        return customers.stream()
                .map(CustomerQnaDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean checkPassword(Long qnaIdx, String password) {
        CustomerQna customerQna = customerQnaRepository.findById(qnaIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        return customerQna.getQnaPw().equals(password);
    }

    @Transactional(readOnly = true)
    public CustomerQnaDto findById(Long qnaIdx) {
        return customerQnaRepository.findById(qnaIdx)
                .map(customerQna -> CustomerQnaDto.builder()
                        .qnaIdx(customerQna.getQnaIdx())
                        .qnaName(customerQna.getQnaName())
                        .qnaPw(customerQna.getQnaPw())
                        .qnaTitle(customerQna.getQnaTitle())
                        .qnaContent(customerQna.getQnaContent())
                        .qnaDate(customerQna.getQnaDate())
                        .build())
                .orElse(null);
    }

}
