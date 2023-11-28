package com.capstone.paymentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "payment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long reservationId;
    private String customerName;
    private String paymentMode;
    private float amt;
    private Timestamp paidOn;
    private String encryptedCardNumber;
    private String promoCodeApplied;
    private boolean isPaymentSuccess;
}
