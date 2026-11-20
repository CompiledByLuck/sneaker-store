package com.ua.sneakerstore.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Доменна модель кросівок. Не залежить від Spring чи HTTP-рівня.
 * Один запис описує конкретну модель у конкретному розмірі.
 */
public record Sneaker(
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
    public Sneaker {
        images = images == null ? List.of() : List.copyOf(images);
    }

    public Sneaker withId(Long newId) {
        return new Sneaker(newId, brand, model, size, price, color, gender,
                category, material, season, soleMaterial, waterproof,
                coverUrl, images, description);
    }
}
