package ru.isemenov.limitcore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isemenov.limitcore.configuration.ApplicationProperties;
import ru.isemenov.limitcore.dto.LimitUpdateResponseDto;
import ru.isemenov.limitcore.dto.UpdateLimitDto;
import ru.isemenov.limitcore.entity.Limit;
import ru.isemenov.limitcore.exception.NotEnoughQuotaException;
import ru.isemenov.limitcore.repository.LimitRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository repository;
    private final ApplicationProperties properties;

    public BigDecimal findQuotaByUserId(Long userId) {
        Limit limit = findByUserId(userId);
        return limit.getQuota();
    }


    public Limit createLimit(Long userId) {
        Limit limit = new Limit();
        limit.setUserId(userId);
        limit.setQuota(properties.getDefaultLimit());

        return repository.save(limit);
    }

    @Transactional
    public LimitUpdateResponseDto update(UpdateLimitDto updateLimitDto) {
        Long userId = updateLimitDto.userId();
        Limit limit = findByUserId(userId);

        BigDecimal updatedQuota = limit.getQuota().subtract(updateLimitDto.paymentSize());
        if (updatedQuota.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughQuotaException("Дневной лимит превышен. Уменьшите сумму платежа, либо повторите попытку завтра.");
        }
        // Считаем что лимит не может быть восстановлен сверх его дефолтного значения.
        // (на случай если запрос на уменьшение лимита пришел в одну дату, а запрос на восстановление лимита - уже в следующую.)
        if (updatedQuota.compareTo(properties.getDefaultLimit()) > 0) {
            updatedQuota = properties.getDefaultLimit();
        }

        limit.setQuota(updatedQuota);

        return new LimitUpdateResponseDto(limit.getUserId(), limit.getQuota(), HttpStatus.OK.toString());
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void updateAllLimits() {
        List<Limit> limits = repository.findAll();
        limits.forEach(l -> l.setQuota(properties.getDefaultLimit()));
        repository.saveAll(limits);
    }

    @Transactional
    private Limit findByUserId(Long userId) {
        return repository.findByUserId(userId).orElseGet(() -> createLimit(userId));
    }
}
