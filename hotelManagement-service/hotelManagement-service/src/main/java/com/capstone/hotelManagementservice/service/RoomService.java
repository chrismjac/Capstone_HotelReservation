package com.capstone.hotelManagementservice.service;

import com.capstone.hotelManagementservice.entity.Room;
import com.capstone.hotelManagementservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public ResponseEntity<String> addNewRoom(Room room) {
        try {
            roomRepository.save(room);
            return new ResponseEntity<>("Room Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new room" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllRooms() {
        List<Room> allRooms = roomRepository.findAll();
        if (!allRooms.isEmpty()) {
            return ResponseEntity.ok(allRooms);
        }
        return new ResponseEntity<>("No Rooms Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getRoomById(int id) {
        Optional<Room> roomDetails = roomRepository.findById(id);
        if (roomDetails.isPresent()) {
            return ResponseEntity.ok(roomDetails.get());
        }
        return new ResponseEntity<>("No room with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updateRoom(Room room, int id) {
        ResponseEntity<?> roomDetails = getRoomById(id);
        if (roomDetails.getStatusCode().is2xxSuccessful()) {
            try {
                roomRepository.save(room);
                return new ResponseEntity<>("Room Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating room" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No room with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteRoomById(int id) {
        roomRepository.deleteById(id);
        return new ResponseEntity<>("Room Details deleted successfully", HttpStatus.OK);
    }
}
