package com.example.demo.service;

import com.example.demo.dto.FlightBookingAcknowledgement;
import com.example.demo.dto.FlightBookingRequest;
import com.example.demo.entity.PassengerInfo;
import com.example.demo.entity.PaymentInfo;
import com.example.demo.repo.PassengerInfoRepo;
import com.example.demo.repo.PaymentInfoRepo;
import com.example.demo.utils.PaymentUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightBookingService {

    private final PassengerInfoRepo passengerInfoRepo;
    private final PaymentInfoRepo paymentInfoRepo;

    @Transactional//(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request) {

        PassengerInfo passengerInfo = request.getPassengerInfo();
        passengerInfo = passengerInfoRepo.save(passengerInfo);

        PaymentInfo paymentInfo = request.getPaymentInfo();

        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

        paymentInfo.setPassengerId(passengerInfo.getId());
        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfoRepo.save(paymentInfo);
        return new FlightBookingAcknowledgement("SUCCESS", passengerInfo.getFare(), UUID.randomUUID().toString().split("-")[0], passengerInfo);

    }

}
