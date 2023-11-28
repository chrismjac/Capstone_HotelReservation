package com.capstone.hotelCustomerservice.service;


import com.capstone.hotelCustomerservice.entity.Customer;
import com.capstone.hotelCustomerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public ResponseEntity<String> addNewCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
            return new ResponseEntity<>("Customer Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new customer" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        if (!allCustomers.isEmpty()) {
            return ResponseEntity.ok(allCustomers);
        }
        return new ResponseEntity<>("No Customers Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getCustomerById(Long id) {
        Optional<Customer> customerDetails = customerRepository.findById(id);
        if (customerDetails.isPresent()) {
            return ResponseEntity.ok(customerDetails.get());
        }
        return new ResponseEntity<>("No customer with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateCustomer(Customer customer, Long id) {
        ResponseEntity<?> customerDetails = getCustomerById(id);
        if (customerDetails.getStatusCode().is2xxSuccessful()) {
            try {
                customerRepository.save(customer);
                return new ResponseEntity<>("Customer Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating customer" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No customer with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>("Customer Details deleted successfully", HttpStatus.OK);
    }
}
