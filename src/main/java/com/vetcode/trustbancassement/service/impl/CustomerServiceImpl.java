package com.vetcode.trustbancassement.service.impl;

import com.vetcode.trustbancassement.dto.CustomerRequestDto;
import com.vetcode.trustbancassement.dto.CustomerResponseDto;
import com.vetcode.trustbancassement.exceptions.CustomerNotFoundException;
import com.vetcode.trustbancassement.model.Customer;
import com.vetcode.trustbancassement.repository.CustomerRepository;
import com.vetcode.trustbancassement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return convertToResponseDto(customerOptional.get());
        } else {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = convertToEntity(customerRequestDto);
        customer = customerRepository.save(customer);
        return convertToResponseDto(customer);
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto updatedCustomerRequestDto) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            existingCustomer.setFirstName(updatedCustomerRequestDto.getFirstName());
            existingCustomer.setLastName(updatedCustomerRequestDto.getLastName());
            existingCustomer.setEmail(updatedCustomerRequestDto.getEmail());
            existingCustomer.setPhoneNumber(updatedCustomerRequestDto.getPhoneNumber());
            existingCustomer.setOtherName(updatedCustomerRequestDto.getOtherName());
            existingCustomer.setState(updatedCustomerRequestDto.getState());
            existingCustomer = customerRepository.save(existingCustomer);
            return convertToResponseDto(existingCustomer);
        } else {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
    }

    private CustomerResponseDto convertToResponseDto(Customer customer) {
        CustomerResponseDto responseDto = new CustomerResponseDto();
        responseDto.setId(customer.getId());
        responseDto.setFirstName(customer.getFirstName());
        responseDto.setLastName(customer.getLastName());
        responseDto.setEmail(customer.getEmail());
        responseDto.setPhoneNumber(customer.getPhoneNumber());
        responseDto.setOtherName(customer.getOtherName());
        responseDto.setState(customer.getState());
        return responseDto;
    }

    private Customer convertToEntity(CustomerRequestDto requestDto) {
        Customer customer = new Customer();
        customer.setFirstName(requestDto.getFirstName());
        customer.setLastName(requestDto.getLastName());
        customer.setEmail(requestDto.getEmail());
        customer.setPhoneNumber(requestDto.getPhoneNumber());
        customer.setOtherName(requestDto.getOtherName());
        customer.setState(requestDto.getState());
        return customer;
    }
}
