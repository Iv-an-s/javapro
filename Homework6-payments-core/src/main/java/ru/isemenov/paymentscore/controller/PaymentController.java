package ru.isemenov.paymentscore.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.isemenov.paymentscore.dto.PaymentDto;
import ru.isemenov.paymentscore.dto.PaymentExecutorResponseDto;
import ru.isemenov.paymentscore.dto.ProductDto;
import ru.isemenov.paymentscore.dto.ProductResponseDto;
import ru.isemenov.paymentscore.service.ProductService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    public PaymentController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Пример для тела запроса:
     * {
     *     "userId": 1,
     *     "productId": 1,
     *     "amount": 10
     * }
     *
     */
    @PostMapping("/execute")
    public PaymentExecutorResponseDto executePayment(@RequestBody PaymentDto payment){
        return productService.executePayment(payment.getUserId(), payment.getProductId(), payment.getAmount());
    }

    @GetMapping("/products")
    public ProductResponseDto getProducts(){
        logger.info("PaymentsController#getProducts: получен запрос");
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDto getProducts(@PathVariable Long id){
        logger.info("PaymentsController#getProducts: получен запрос");
        return productService.getProductById(id);
    }
}
