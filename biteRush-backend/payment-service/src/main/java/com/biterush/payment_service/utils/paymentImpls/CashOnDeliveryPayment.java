package com.biterush.payment_service.utils.paymentImpls;

import com.biterush.payment_service.model.enums.METHOD;
import com.biterush.payment_service.utils.PaymentMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Component
public class CashOnDeliveryPayment implements PaymentMethods {
    @Override
    public METHOD getMethod() {
        return METHOD.CASH_ON_DELIVERY;
    }

    @Override
    public Boolean pay(UUID transactionId, BigDecimal amount) {
            log.info("COD Payment Method Called");
            return true;
    }
}
