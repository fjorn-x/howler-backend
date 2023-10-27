package com.hamza.howler.controllers;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/razorpay")
public class RazorPayController {
    @Value("${rzp_key_id}")
    private String keyId;

    @Value("${rzp_key_secret}")
    private String secret;

    @GetMapping("/create/order/{amount}")
    public String createPaymentOrderId(@PathVariable String amount) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient(keyId,secret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount); // amount in the smallest currency unit
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "Howler Pro");
        Order order = razorpay.orders.create(orderRequest);
        String orderId = order.get("id");
        return orderId;
    }
}
