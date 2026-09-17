package com.biterush.payment_service.utils;

import com.biterush.payment_service.model.enums.METHOD;
import com.biterush.payment_service.utils.paymentImpls.CardPayment;
import com.biterush.payment_service.utils.paymentImpls.CashOnDeliveryPayment;
import com.biterush.payment_service.utils.paymentImpls.NetBankingPayment;
import com.biterush.payment_service.utils.paymentImpls.UPIPayment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentFunction {

    private final Map<METHOD,PaymentMethods> paymentMethodsMap;
    public PaymentFunction(List<PaymentMethods> methodsFunctions) {
        paymentMethodsMap = methodsFunctions.stream().collect(Collectors.toMap(PaymentMethods::getMethod, Function.identity()));
    }

    public PaymentMethods paymentFunction(METHOD method) {
        return paymentMethodsMap.get(method);
    }
}
