package com.stripe_payment_gateway.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.model.PaymentIntent;
import com.stripe_payment_gateway.exceptions.MyException;
import com.stripe_payment_gateway.models.PaymentDTO;
import com.stripe_payment_gateway.services.serviceImpl.PaymentServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @PostMapping("/create_intent")
	public ResponseEntity<String> createIntentForPaymentHandler(@RequestBody PaymentDTO paymentRequest) throws MyException {
		return new ResponseEntity<> (this.paymentServiceImpl.createPayment(paymentRequest), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/capture_intent/{id}")
	public ResponseEntity<String> captureTheCreatedIntentHandler(@PathVariable("id") String paymentIntentId) throws MyException {
		return new ResponseEntity<> (this.paymentServiceImpl.captureTheCreatedIntent(paymentIntentId), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/create_refund/{id}")
	public ResponseEntity<String> createTheRefundByPaymentIntentHandler(@PathVariable("id") String paymentIntentId) throws MyException {
		return new ResponseEntity<> (this.paymentServiceImpl.createRefundForPayment(paymentIntentId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/get_intents")
	public ResponseEntity<List<PaymentIntent>> getAllTheIntentsHandler()throws MyException{
		return new ResponseEntity<>(this.paymentServiceImpl.getAllTheIntent(), HttpStatus.OK);
	}
}
