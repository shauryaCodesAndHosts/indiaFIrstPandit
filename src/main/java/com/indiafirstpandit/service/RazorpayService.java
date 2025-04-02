package com.indiafirstpandit.service;

import com.indiafirstpandit.dto.RazorpayOrderDto;
import com.indiafirstpandit.model.RazorpayOrder;
import com.indiafirstpandit.model.User;
import com.indiafirstpandit.response.OptionsResponse;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    String key = "rzp_test_DHRMguF5bhrkLX";
    String secret = "Lpefbdbj4MSI7dUaTpdqeuJT";
    public Order createRazorpayOrder(Double amount)
    {
        try {
            RazorpayClient razorpay = new RazorpayClient(key,secret);
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

    public OptionsResponse createOptions(Order razorPayOrder, User currentUser)
    {

        JSONObject orderJson = new JSONObject(razorPayOrder.toString());
        Integer amount = orderJson.has("amount") ? orderJson.getInt("amount") : 0;
        String orderId = orderJson.has("id") ? orderJson.getString("id") : null;
        String currency = orderJson.has("currency") ? orderJson.getString("currency") : "INR";
        String receipt = orderJson.has("receipt") ? orderJson.getString("receipt") : "default_receipt";

        OptionsResponse.Prefill prefill = OptionsResponse.Prefill.builder()
            .contact(currentUser.getPhoneNumber())
            .email(currentUser.getEmail())
            .build();
        OptionsResponse response =  OptionsResponse.builder()
                .key(key)
                .amount(amount)
                .name(currentUser.getName())
                .order_id(orderId)
                .description("")
                .timeout(120)
                .prefill(prefill)
                .build();
        System.out.println("Options created -> "+response);
        return response;
    }

}
