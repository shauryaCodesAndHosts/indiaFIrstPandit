package com.indiafirstpandit.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class RazorPayVerificationRequest {
//      "razorpay_payment_id": "pay_29QQoUBi66xm2f",
//              "razorpay_order_id": "order_9A33XWu170gUtm",
//              "razorpay_signature": "9ef4dffbfd84f1318f6739a3ce19f9d85851857ae648f114332d8401e0949a3d"

    public RazorPayVerificationRequest(String razorpay_payment_id,
    String razorpay_order_id, String razorpay_signature)
    {
        this.razorpay_order_id = razorpay_order_id;
        this.razorpay_signature = razorpay_signature;
        this.razorpay_payment_id = razorpay_payment_id;
    }

    private UUID localOrderId;
    private String razorpay_payment_id;
    private String razorpay_order_id;
    private String razorpay_signature;
}