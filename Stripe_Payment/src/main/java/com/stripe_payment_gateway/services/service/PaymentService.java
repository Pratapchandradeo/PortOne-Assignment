package com.stripe_payment_gateway.services.service;

import java.util.List;

import com.stripe.model.PaymentIntent;
import com.stripe_payment_gateway.exceptions.MyException;
import com.stripe_payment_gateway.models.PaymentDTO;

public interface PaymentService {
    public String createPayment(PaymentDTO payment) throws MyException;
    public String attachPayment(String paymentIntentId, String paymentMethodId) throws MyException;
	public String captureTheCreatedIntent(String paymentIntentId) throws MyException;
	public String createRefundForPayment(String paymentIntentId) throws MyException;
	public List<PaymentIntent> getAllTheIntent()throws MyException;
	public String confirmPayment(PaymentIntent paymentIntent)throws MyException;
}
