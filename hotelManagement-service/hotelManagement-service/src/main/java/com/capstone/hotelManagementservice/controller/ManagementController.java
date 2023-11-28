package com.capstone.hotelManagementservice.controller;

import com.capstone.hotelManagementservice.entity.Room;
import com.capstone.hotelManagementservice.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/manage/rooms")
public class ManagementController {
    @Autowired
    RoomService roomService;

    @PostMapping
    @Operation(summary = "Add a new Room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add Room")})
    private ResponseEntity<String> addNewRoom(@RequestBody Room room){
        return roomService.addNewRoom(room);
    }

    @GetMapping
    @Operation(summary = "Retrieve all Rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rooms retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No Rooms Added")})
    private ResponseEntity<?> getAllRooms(){
        return roomService.getAllRooms();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a Room by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Room details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No Room with specified Id found")})
    private ResponseEntity<?> getRoomById(@PathVariable int id){
        return roomService.getRoomById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a Room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Room details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified Room details updating failed"),
            @ApiResponse(responseCode = "404", description = "No Room with specified Id found")})
    private ResponseEntity<String> updateRoom(@RequestBody Room room,@PathVariable int id){
        return roomService.updateRoom(room,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified Room details updated successfully")})
    private ResponseEntity<String> deleteRoomById(@PathVariable int id){
        return roomService.deleteRoomById(id);
    }
}
