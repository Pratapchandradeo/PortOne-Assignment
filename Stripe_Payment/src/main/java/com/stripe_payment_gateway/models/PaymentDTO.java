package com.stripe_payment_gateway.models;

import lombok.Data;


@Data
public class PaymentDTO {
    private int amount;
    private String currency;
}
