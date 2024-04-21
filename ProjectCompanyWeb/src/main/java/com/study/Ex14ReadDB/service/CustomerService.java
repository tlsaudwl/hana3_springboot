package com.study.Ex14ReadDB.service;

import com.study.Ex14ReadDB.domain.customer.Customer;
import com.study.Ex14ReadDB.domain.customer.CustomerRepository;
import com.study.Ex14ReadDB.dto.CustomerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Long save(CustomerSaveRequestDto dto) {
        Customer customer = customerRepository.save(dto.toSaveEntity());
        return customer.getOne2oneIdx();
    }

    @Transactional
    public boolean existsById(Long newOne2oneIdx){
        return customerRepository.existsById(newOne2oneIdx);
    }

}
