package com.vetcode.trustbancassement.service;

import com.vetcode.trustbancassement.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Long id, Customer updatedCustomer);

    void deleteCustomer(Long id);
}
