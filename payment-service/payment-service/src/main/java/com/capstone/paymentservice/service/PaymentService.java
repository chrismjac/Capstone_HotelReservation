package com.capstone.paymentservice.service;

import com.capstone.paymentservice.entity.Payment;
import com.capstone.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    public ResponseEntity<String> addNewPayment(Payment payment) {
        try {
            paymentRepository.save(payment);
            return new ResponseEntity<>("payment Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new payment" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllPayments() {
        List<Payment> allPayments = paymentRepository.findAll();
        if (!allPayments.isEmpty()) {
            return ResponseEntity.ok(allPayments);
        }
        return new ResponseEntity<>("No payments Added", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getPaymentById(Long id) {
        Optional<Payment> paymentDetails = paymentRepository.findById(id);
        if (paymentDetails.isPresent()) {
            return ResponseEntity.ok(paymentDetails.get());
        }
        return new ResponseEntity<>("No payment details with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> updatePayment(Payment payment, Long id) {
        ResponseEntity<?> paymentDetails = getPaymentById(id);
        if (paymentDetails.getStatusCode().is2xxSuccessful()) {
            try {
                paymentRepository.save(payment);
                return new ResponseEntity<>("Payment Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating payment" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No payment with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
        return new ResponseEntity<>("Payment Details deleted successfully", HttpStatus.OK);
    }
}
