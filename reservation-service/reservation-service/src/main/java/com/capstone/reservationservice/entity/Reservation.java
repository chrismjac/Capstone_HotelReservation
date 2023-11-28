package com.capstone.reservationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Table(name = "reservation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private int noOfGuests;
    private int noOfRooms;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private boolean isReservationSuccess;
}
