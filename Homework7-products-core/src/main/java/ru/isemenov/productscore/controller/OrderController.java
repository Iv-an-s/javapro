package ru.isemenov.productscore.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.isemenov.productscore.dto.OrderDetailsRequestDto;
import ru.isemenov.productscore.dto.PaymentExecutorResponseDto;
import ru.isemenov.productscore.service.OrderService;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping
    public PaymentExecutorResponseDto createOrder(@RequestBody OrderDetailsRequestDto orderDetailsDto){
        PaymentExecutorResponseDto response = orderService.createOrder(orderDetailsDto);
        logger.info("OrderService#createOrder() возвращаем response: " + response);
        return response;
    }
}