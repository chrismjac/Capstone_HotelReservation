package com.capstone.reservationservice.service;

import com.capstone.reservationservice.entity.Reservation;
import com.capstone.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    public ResponseEntity<String> addNewReservation(Reservation reservation) {
        try {
            reservationRepository.save(reservation);
            return new ResponseEntity<>("reservation Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new reservation" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllReservations() {
        List<Reservation> allReservations = reservationRepository.findAll();
        if (!allReservations.isEmpty()) {
            return ResponseEntity.ok(allReservations);
        }
        return new ResponseEntity<>("No reservations Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getReservationById(Long id) {
        Optional<Reservation> reservationDetails = reservationRepository.findById(id);
        if (reservationDetails.isPresent()) {
            return ResponseEntity.ok(reservationDetails.get());
        }
        return new ResponseEntity<>("No reservation details with specified Id found", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> updateReservation(Reservation reservation, Long id) {
        ResponseEntity<?> reservationDetails = getReservationById(id);
        if (reservationDetails.getStatusCode().is2xxSuccessful()) {
            try {
                reservationRepository.save(reservation);
                return new ResponseEntity<>("Reservation Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating reservation" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No reservation with specified Id found", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
        return new ResponseEntity<>("Reservation Details deleted successfully", HttpStatus.OK);

    }
}
