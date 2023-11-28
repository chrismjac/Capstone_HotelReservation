package com.capstone.hotelCustomerservice.controller;

import com.capstone.hotelCustomerservice.entity.Customer;
import com.capstone.hotelCustomerservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping
    @Operation(summary = "Add a new Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Customer")})
    private ResponseEntity<String> addNewCustomer(@RequestBody Customer customer){
        return customerService.addNewCustomer(customer);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Customers Added")})
    private ResponseEntity<?> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Customer details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Customer with specified Id found")})
    private ResponseEntity<?> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Customer details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Customer details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Customer with specified Id found")})
    private ResponseEntity<String> updateCustomer(@RequestBody Customer customer,@PathVariable Long id){
        return customerService.updateCustomer(customer,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Customer details updated successfully")})
    private ResponseEntity<String> deleteCustomerById(@PathVariable Long id){
        return customerService.deleteCustomerById(id);
    }
}
