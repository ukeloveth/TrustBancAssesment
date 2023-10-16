package com.vetcode.trustbancassement.service;

import com.vetcode.trustbancassement.dto.CustomerRequestDto;
import com.vetcode.trustbancassement.dto.CustomerResponseDto;

import java.util.List;

public interface
CustomerService {
    List<CustomerResponseDto> getAllCustomers();

    CustomerResponseDto getCustomerById(Long id);

    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);

    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto updatedCustomerRequestDto);

    void deleteCustomer(Long id);
}
