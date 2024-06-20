package com.stripe_payment_gateway.services.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentIntentCollection;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentListParams;
import com.stripe.param.PaymentIntentUpdateParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.stripe.param.RefundCreateParams;
import com.stripe_payment_gateway.exceptions.MyException;
import com.stripe_payment_gateway.models.PaymentDTO;
import com.stripe_payment_gateway.services.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    // @Value("${stripe.api.key}")
    // private String apiKey;

    
    @Override
    public String createPayment(PaymentDTO payment) throws MyException {

        try {
            // Build the PaymentIntent creation parameters
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(Long.valueOf(payment.getAmount()))
                    .setCurrency(payment.getCurrency())
                    .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.MANUAL)
                    .build();
 // Create the PaymentIntent
            PaymentIntent paymentIntent = PaymentIntent.create(params);
// Confirm the PaymentIntent
            return confirmPayment(paymentIntent);
        } catch (StripeException e) {
            throw new MyException("Error creating PaymentIntent: " + e.getMessage());
        }
    }

    //Confirm the PaymentIntent by creating and attaching a payment method.

    @Override
    public String confirmPayment(PaymentIntent paymentIntent) throws MyException {
        try {
            String testToken = "tok_visa";

            PaymentMethodCreateParams params = PaymentMethodCreateParams.builder()
                    .setType(PaymentMethodCreateParams.Type.CARD)
                    .setCard(PaymentMethodCreateParams.Token.builder().setToken(testToken).build())
                    .build();

            PaymentMethod paymentMethod = PaymentMethod.create(params);
// Attach the PaymentMethod to the PaymentIntent and confirm it
            return attachPayment(paymentIntent.getId(), paymentMethod.getId());
        } catch (StripeException stripeException) {
            throw new MyException("Error creating payment method: " + stripeException.getMessage());
        }
    }

    //Attach a PaymentMethod to the PaymentIntent and confirm it.

    @Override
    public String attachPayment(String paymentIntentId, String paymentMethodId) throws MyException {
       

        try {
            // Build the PaymentIntent update parameters to attach the PaymentMethod
            PaymentIntentUpdateParams params = PaymentIntentUpdateParams.builder()
                    .setPaymentMethod(paymentMethodId)
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
            PaymentIntent updatedPaymentIntent = paymentIntent.update(params);

            PaymentIntentConfirmParams confirmParams = PaymentIntentConfirmParams.builder()
                    .setReturnUrl("https://pratapchandradeo.github.io")
                    .build();

            updatedPaymentIntent = updatedPaymentIntent.confirm(confirmParams);
// Return the confirmed PaymentIntent as a JSON string
            return updatedPaymentIntent.toJson();
        } catch (StripeException stripeException) {
            throw new MyException("Error attaching payment method to PaymentIntent: " + stripeException.getMessage());
        }
    }

    @Override
    public String captureTheCreatedIntent(String paymentIntentId) throws MyException {
        

        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
// Check if the PaymentIntent is in the correct status for capture
            if ("requires_capture".equals(paymentIntent.getStatus())) {
                PaymentIntent capturedPaymentIntent = paymentIntent.capture();
                return capturedPaymentIntent.toJson();
            } else {
                throw new MyException("PaymentIntent cannot be captured. Current status: " + paymentIntent.getStatus());
            }
        } catch (StripeException stripeException) {
            throw new MyException("Error capturing PaymentIntent: " + stripeException.getMessage());
        }
    }

    @Override
    public String createRefundForPayment(String paymentIntentId) throws MyException {
        

        try {
            RefundCreateParams params = RefundCreateParams.builder()
                    .setPaymentIntent(paymentIntentId)
                    .build();
            Refund refund = Refund.create(params);

            return refund.toJson();
        } catch (StripeException stripeException) {
            throw new MyException("Error creating refund: " + stripeException.getMessage());
        }
    }

    @Override
    public List<PaymentIntent> getAllTheIntent() throws MyException {
        

        try {
            List<PaymentIntent> allPaymentIntent = new ArrayList<>();
            PaymentIntentCollection paymentIntents;
            PaymentIntentListParams params = PaymentIntentListParams.builder()
                    .setLimit(100L)
                    .build();

            do {
                paymentIntents = PaymentIntent.list(params);
                allPaymentIntent.addAll(paymentIntents.getData());

                if (paymentIntents.getHasMore()) {
                    params = PaymentIntentListParams.builder()
                            .setStartingAfter(paymentIntents.getData().get(paymentIntents.getData().size() - 1).getId())
                            .build();
                } else {
                    break;
                }
            } while (paymentIntents.getHasMore());

            return allPaymentIntent;
        } catch (StripeException stripeException) {
            throw new MyException("Error retrieving payment intent: " + stripeException.getMessage());
        }
    }
}
