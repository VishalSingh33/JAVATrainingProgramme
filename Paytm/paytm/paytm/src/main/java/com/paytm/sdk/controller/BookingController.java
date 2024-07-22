package com.paytm.sdk.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import com.paytm.pg.merchant.PaytmChecksum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.paytm.sdk.entities.PaytmDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private PaytmDetails paytmDetails;

    @Value("${paytm.payment.sandbox.mobile}")
    private String paytmMobile;

    @Value("${paytm.payment.sandbox.email}")
    private String paytmEmail;

    @PostMapping(value = "/make-payment")
    public ModelAndView getPaymentRedirect(@RequestParam String orderId, @RequestParam String txnAmount,
            @RequestParam String customerId) throws Exception {

        log.info("1=========");
        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
        log.info("2=========");

        TreeMap<String, String> parameters = new TreeMap<>();

        paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
        parameters.put("MOBILE_NO", paytmMobile);
        parameters.put("EMAIL", paytmEmail);
        parameters.put("ORDER_ID", orderId);
        parameters.put("TXN_AMOUNT", txnAmount);
        parameters.put("CUST_ID", customerId);
        String checkSum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checkSum);
        log.info("3=========");
        modelAndView.addAllObjects(parameters);
        return modelAndView;

    }

    @PostMapping(value = "/payment-response")
    public ModelAndView getPaymentResponseRedirect(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("redirect:http://localhost:8080/#/payment");// my angular payment

        Map<String, String[]> mapData = request.getParameterMap();
        TreeMap<String, String> parameters = new TreeMap<String, String>();
        String paytmChecksum = "";

        for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {

            if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())) {
                paytmChecksum = requestParamsEntry.getValue()[0];
            } else {
                parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
            }
        }
        String result;
        boolean isValideChecksum = false;
        try {
            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
            if (isValideChecksum && parameters.containsKey("RESPCODE")) {

                if (parameters.get("RESPCODE").equals("01")) {
                    result = "Payment Successful";
                } else {
                    result = "Payment Failed";
                }
            } else {
                result = "Checksum mismatched";
            }
        } catch (Exception e) {
            result = e.toString();
        }
        modelAndView.addObject("result", result);
        parameters.remove("CHECKSUMHASH");
        modelAndView.addObject("parameters", parameters);
        return modelAndView;

    }

    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {

        return PaytmChecksum.verifySignature(parameters, paytmDetails.getMerchantKey(), paytmChecksum);
    }

    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {

        return PaytmChecksum.generateSignature(parameters, paytmDetails.getMerchantKey());
    }

}
