package com.ua.sneakerstore.web.dto;

import java.math.BigDecimal;
import java.util.List;

/** Повна інформація для сторінки конкретної позиції каталогу. */
public record SneakerDetailDto(
        Long id,
        String brand,
        String model,
        Integer size,
        BigDecimal price,
        String color,
        String gender,
        String category,
        String material,
        String season,
        String soleMaterial,
        Boolean waterproof,
        String coverUrl,
        List<String> images,
        String description
) {
}
