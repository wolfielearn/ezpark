package com.ezpark.io.customer.infrastructure.external.adapter;


import com.ezpark.io.customer.domain.port.outbound.NotificationService;
import com.ezpark.io.shared.kernel.CustomerId;
import com.ezpark.io.shared.kernel.ReservationId;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Component
public class NotificationClientAdapter implements NotificationService {


    private final RestTemplate restTemplate;

    public NotificationClientAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendReceipt(ReservationId reservationId, BigDecimal amount) {
        String uuid = reservationId.value().toString();
        System.out.println("########################################");
        System.out.println("Retrieving the Payment from the External payment service");
        ResponseEntity<Reciept> exchange = restTemplate.exchange(URI.create("http://localhost:8080/payment/" + uuid ), HttpMethod.GET, HttpEntity.EMPTY, Reciept.class);
       System.out.println("Message received from Payments :");

        System.out.println(exchange.getBody());
        System.out.println("########################################");
    }

    @Override

    public void sendReservationFailedNotification(CustomerId customerId, String reason) {

    }


}
