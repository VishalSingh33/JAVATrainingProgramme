package com.airlines.british.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import com.airlines.british.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManagePaymentController implements PaymentController {

    private final PaymentService paymentService;

    @Override
    public String home() {

        return paymentService.home();
	}

    @Override
    public ModelAndView getRedirect(String customerId, String transactionAmount, String orderId) throws Exception {
       
        return paymentService.getRedirect(customerId, transactionAmount, orderId);
	}

    @Override
    public String getResponseRedirect(HttpServletRequest request, Model model) {
       
        return paymentService.getResponseRedirect(request, model);
	}
    
}
