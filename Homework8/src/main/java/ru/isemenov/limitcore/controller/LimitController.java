package ru.isemenov.limitcore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.isemenov.limitcore.dto.LimitUpdateResponseDto;
import ru.isemenov.limitcore.dto.UpdateLimitDto;
import ru.isemenov.limitcore.service.LimitService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/limit")
public class LimitController {

    private final LimitService limitService;

    @GetMapping("/{id}")
    public BigDecimal findQuota(@PathVariable Long id) {
        return limitService.findQuotaByUserId(id);
    }

    // Использую Get-запрос вместо Post, т.к. тело запроса не задействуем, передаем только id.
    @GetMapping("/create/{id}")
    public void createLimit(@PathVariable("id") Long userId) {
        limitService.createLimit(userId);
    }

    /**
     * метод служит как для уменьшения дневного лимита при совершении платежей,
     * так и для восстановления лимита, если платеж совершен не был.
     * Положительные значения paymentSize в updateLimitDto уменьшают лимит,
     * Отрицательные значения paymentSize в updateLimitDto восстанавливают лимит.
     */
    @PutMapping("/update")
    public LimitUpdateResponseDto updateLimit(@RequestBody UpdateLimitDto updateLimitDto) {
        return limitService.update(updateLimitDto);
    }
}
