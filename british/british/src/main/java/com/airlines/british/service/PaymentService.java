package com.airlines.british.service;

import java.util.Map;
import java.util.TreeMap;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import com.airlines.british.entites.PaytmDetails;
import lombok.RequiredArgsConstructor;
// import com.paytm.pg.merchant.CheckSumServiceHelper;
import com.paytm.pg.merchant.PaytmChecksum;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private PaytmDetails paytmDetails;
    private Environment env;

    public String home() {

        return "home";
    }

    public ModelAndView getRedirect(String customerId, String transactionAmount, String orderId) throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
        TreeMap<String, String> parameters = new TreeMap<>();
        paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
        parameters.put("MOBILE_NO", env.getProperty("paytm.mobile"));
        parameters.put("EMAIL", env.getProperty("paytm.email"));
        parameters.put("ORDER_ID", orderId);
        parameters.put("TXN_AMOUNT", transactionAmount);
        parameters.put("CUST_ID", customerId);
        String checkSum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checkSum);
        modelAndView.addAllObjects(parameters);
        return modelAndView;
    }

    public String getResponseRedirect(HttpServletRequest request, Model model) {

        Map<String, String[]> mapData = request.getParameterMap();
        TreeMap<String, String> parameters = new TreeMap<String, String>();
        mapData.forEach((key, val) -> parameters.put(key, val[0]));
        String paytmChecksum = "";
        if (mapData.containsKey("CHECKSUMHASH")) {
            paytmChecksum = mapData.get("CHECKSUMHASH")[0];
        }
        String result;

        boolean isValideChecksum = false;
        System.out.println("RESULT : " + parameters.toString());
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
        model.addAttribute("result", result);
        parameters.remove("CHECKSUMHASH");
        model.addAttribute("parameters", parameters);
        return "report";
    }

    // private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
    //     return CheckSumServiceHelper.getCheckSumServiceHelper()
    //             .verifycheckSum(paytmDetails.getMerchantKey(), parameters, paytmChecksum);
    // }
    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
        return PaytmChecksum.verifySignature(parameters, paytmDetails.getMerchantKey(), paytmChecksum);
    }

    // private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
    //     return CheckSumServiceHelper.getCheckSumServiceHelper()
    //             .genrateCheckSum(paytmDetails.getMerchantKey(), parameters);
    // }
    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
        return PaytmChecksum.generateSignature(parameters, paytmDetails.getMerchantKey());
    }

}
