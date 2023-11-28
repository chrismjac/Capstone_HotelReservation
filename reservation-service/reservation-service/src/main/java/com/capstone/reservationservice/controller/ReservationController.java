package com.capstone.reservationservice.controller;

import com.capstone.reservationservice.entity.Reservation;
import com.capstone.reservationservice.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    @Operation(summary = "Add a new Reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Reservation")})
    private ResponseEntity<String> addNewReservation(@RequestBody Reservation reservation){
        return reservationService.addNewReservation(reservation);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Reservations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservations retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Reservations Added")})
    private ResponseEntity<?> getAllReservations(){
        return reservationService.getAllReservations();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Reservation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Reservation details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Reservation with specified Id found")})
    private ResponseEntity<?> getReservationById(@PathVariable Long id){
        return reservationService.getReservationById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Reservation details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Reservation details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Reservation with specified Id found")})
    private ResponseEntity<String> updateReservation(@RequestBody Reservation reservation,@PathVariable Long id){
        return reservationService.updateReservation(reservation,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Reservation details updated successfully")})
    private ResponseEntity<String> deleteReservationById(@PathVariable Long id){
        return reservationService.deleteReservationById(id);
    }
}
