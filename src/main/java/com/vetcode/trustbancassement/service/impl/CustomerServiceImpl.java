package com.vetcode.trustbancassement.service.impl;

import com.vetcode.trustbancassement.exceptions.CustomerNotFoundException;
import com.vetcode.trustbancassement.model.Customer;
import com.vetcode.trustbancassement.repository.CustomerRepository;
import com.vetcode.trustbancassement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;




        public List<Customer> getAllCustomers() {
            return customerRepository.findAll();
        }

        public Customer getCustomerById(Long id) {
            Optional<Customer> customerOptional = customerRepository.findById(id);
            if (customerOptional.isPresent()) {
                return customerOptional.get();
            } else {
                throw new CustomerNotFoundException("Customer with ID " + id + " not found");
            }
        }

        public Customer createCustomer(Customer customer) {
            return customerRepository.save(customer);
        }

        public Customer updateCustomer(Long id, Customer updatedCustomer) {
            if (customerRepository.existsById(id)) {
                updatedCustomer.setId(id);
                return customerRepository.save(updatedCustomer);
            } else {
                throw new CustomerNotFoundException("Customer with ID " + id + " not found");
            }
        }

        public void deleteCustomer(Long id) {
            if (customerRepository.existsById(id)) {
                customerRepository.deleteById(id);
            } else {
                throw new CustomerNotFoundException("Customer with ID " + id + " not found");
            }
        }
    }


//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//    public Customer getCustomerById(Long id) {
//        Optional<Customer> customerOptional = customerRepository.findById(id);
//        if (customerOptional.isPresent()) {
//            return customerOptional.get();
//        } else {
//            throw new EntityNotFoundException("Customer with ID " + id + " not found");
//        }
//    }
//
//    public Customer createCustomer(Customer customer) {
//        return customerRepository.save(customer);
//    }
//
//    public Customer updateCustomer(Long id, Customer updatedCustomer) {
//        if (customerRepository.existsById(id)) {
//            updatedCustomer.setId(id);
//            return customerRepository.save(updatedCustomer);
//        } else {
//            throw new EntityNotFoundException("Customer with ID " + id + " not found");
//        }
//    }
//
//    public void deleteCustomer(Long id) {
//        if (customerRepository.existsById(id)) {
//            customerRepository.deleteById(id);
//        } else {
//            throw new EntityNotFoundException("Customer with ID " + id + " not found");
//        }
//    }

