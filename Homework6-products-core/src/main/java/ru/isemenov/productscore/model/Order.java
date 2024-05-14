package ru.isemenov.productscore.model;

import java.util.List;

public class Order {
    private Long id;
    private String username;
    private List<OrderItem> items;
    private Integer totalPrice;
}
