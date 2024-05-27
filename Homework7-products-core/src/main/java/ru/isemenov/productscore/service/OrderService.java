package ru.isemenov.productscore.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isemenov.productscore.dto.OrderDetailsRequestDto;
import ru.isemenov.productscore.dto.PaymentExecutorResponseDto;
import ru.isemenov.productscore.entity.*;
import ru.isemenov.productscore.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public PaymentExecutorResponseDto createOrder(OrderDetailsRequestDto orderDetailsDto) {
        logger.info("OrderService#createOrder() получен запрос с OrderDetailsRequestDto: " + orderDetailsDto);

        User user = userService.findById(orderDetailsDto.userId());
        Product product = productService.findById(orderDetailsDto.productId());
        Order order = new Order();
            order.setUsername(user.getUsername());

        OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(orderDetailsDto.amount());
            orderItem.setOrder(order);
            orderItem.setPricePerProduct(product.getPrice());
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(orderDetailsDto.amount())));

        List<OrderItem> items = List.of(orderItem);
        BigDecimal totalPrice = calculateOrderTotalPrice(items);

            order.setItems(items);
            order.setTotalPrice(totalPrice);

        calculateBalance(user, totalPrice);
        calculateProductQuantity(product, orderDetailsDto.amount());

        save(order);

        logger.info("OrderService#createOrder() order после сохранения: " + order);

        return new PaymentExecutorResponseDto(order.getId(), HttpStatus.CREATED.toString());
    }

    private BigDecimal calculateOrderTotalPrice(List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void calculateProductQuantity(Product product, Integer amount) {
        Integer availableCountOfProduct = product.getAvailableCount();
        product.setAvailableCount(availableCountOfProduct - amount);
    }

    private void calculateBalance(User user, BigDecimal totalPrice) {
        Account account = user.getAccount();
        BigDecimal balance = account.getBalance();
        account.setBalance(balance.subtract(totalPrice));
    }

    public void save(Order order) {
        orderRepository.save(order);
    }
}
