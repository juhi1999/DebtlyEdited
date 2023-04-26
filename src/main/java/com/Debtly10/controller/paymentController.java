package com.Debtly10.controller;

import com.Debtly10.DTOS.MortgageRegistrationDTO;
import com.Debtly10.DTOS.PaymentRegistrationDTO;
import com.Debtly10.Services.PaymentService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/payment")
public class paymentController {
    private PaymentService paymentService;

    public paymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/register_payment/{mid}")
    public String addMortgage(@RequestBody PaymentRegistrationDTO paymentRegistrationDTO, @PathVariable Long mid){
        return paymentService.addPayment(paymentRegistrationDTO, mid);
    }
}
