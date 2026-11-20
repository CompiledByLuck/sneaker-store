package com.ua.sneakerstore.web.dto;

import java.math.BigDecimal;

/** Дані для картки кросівок у загальному каталозі. */
public record SneakerCardDto(
        Long id,
        String brand,
        String model,
        Integer size,
        BigDecimal price,
        String color,
        String category,
        String coverUrl
) {
}
