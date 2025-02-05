package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.RazorpayOrderDto;
import com.indiafirstpandit.model.RazorpayOrder;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    public Order createRazorpayOrder(Double amount)
    {
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_DHRMguF5bhrkLX","Lpefbdbj4MSI7dUaTpdqeuJT");
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount",amount*100); //amount in paise
            orderRequest.put("currency","INR");
            orderRequest.put("receipt", "receipt#1");
            JSONObject notes = new JSONObject();
            notes.put("test1","this is the test of the notes");
            notes.put("tset2", "this is the test of the notes 2");
            orderRequest.put("notes",notes);

            Order razorpayOrder = razorpay.Orders.create(orderRequest);
            System.out.println(razorpayOrder);
            return razorpayOrder;

        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

    }

}
