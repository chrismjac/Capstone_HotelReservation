package com.capstone.paymentservice.controller;

import com.capstone.paymentservice.entity.Payment;
import com.capstone.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping
    @Operation(summary = "Add a new Payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Payment")})
    private ResponseEntity<String> addNewPayment(@RequestBody Payment payment){
        return paymentService.addNewPayment(payment);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payments retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Payments Added")})
    private ResponseEntity<?> getAllPayments(){
        return paymentService.getAllPayments();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Payment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Payment details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Payment with specified Id found")})
    private ResponseEntity<?> getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Payment details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Payment details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Payment with specified Id found")})
    private ResponseEntity<String> updatePayment(@RequestBody Payment payment,@PathVariable Long id){
        return paymentService.updatePayment(payment,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Payment details updated successfully")})
    private ResponseEntity<String> deletePaymentById(@PathVariable Long id){
        return paymentService.deletePaymentById(id);
    }
}
