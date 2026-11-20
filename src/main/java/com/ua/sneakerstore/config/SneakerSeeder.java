package com.ua.sneakerstore.config;

import com.ua.sneakerstore.domain.Sneaker;
import com.ua.sneakerstore.repository.SneakerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SneakerSeeder implements ApplicationRunner {

    private final SneakerRepository repository;

    public SneakerSeeder(SneakerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {
        repository.save(new Sneaker(null, "Adidas", "Ultraboost Light", 42,
                new BigDecimal("5299.00"), "Чорний / білий", "Унісекс", "Бігові",
                "Текстиль Primeknit", "Демісезон", "Continental Rubber", false,
                "/images/adidas-ultraboost.svg",
                List.of("/images/adidas-ultraboost.svg", "/images/adidas-ultraboost-detail.svg"),
                "Легкі бігові кросівки з м'якою амортизацією та дихаючим текстильним верхом. Підходять для щоденних пробіжок і тривалих тренувань."));

        repository.save(new Sneaker(null, "Nike", "Air Zoom Pegasus 41", 43,
                new BigDecimal("4899.00"), "Білий / синій", "Чоловічі", "Бігові",
                "Сітчастий текстиль", "Літо / демісезон", "Гума", false,
                "/images/nike-pegasus.svg",
                List.of("/images/nike-pegasus.svg", "/images/nike-pegasus-detail.svg"),
                "Універсальна модель для бігу та активного повсякденного використання. Пружна підошва забезпечує комфорт під час тренувань на асфальті."));

        repository.save(new Sneaker(null, "Puma", "Suede Classic XXI", 41,
                new BigDecimal("3199.00"), "Темно-зелений", "Унісекс", "Повсякденні",
                "Натуральна замша", "Демісезон", "Гума", false,
                "/images/puma-suede.svg",
                List.of("/images/puma-suede.svg", "/images/puma-suede-detail.svg"),
                "Класичні міські кросівки із замшевим верхом. Лаконічний силует поєднується з повсякденним одягом."));

        repository.save(new Sneaker(null, "New Balance", "574 Core", 44,
                new BigDecimal("3999.00"), "Сірий / бордовий", "Унісекс", "Повсякденні",
                "Замша та сітка", "Демісезон", "Гума", false,
                "/images/newbalance-574.svg",
                List.of("/images/newbalance-574.svg", "/images/newbalance-574-detail.svg"),
                "Комфортна повсякденна модель із комбінованим верхом та амортизацією для прогулянок містом."));

        repository.save(new Sneaker(null, "ASICS", "Gel-Venture 9", 42,
                new BigDecimal("3599.00"), "Чорний / помаранчевий", "Чоловічі", "Трейлові",
                "Сітка та синтетичні вставки", "Демісезон", "Трейлова гума", true,
                "/images/asics-gel-venture.svg",
                List.of("/images/asics-gel-venture.svg", "/images/asics-gel-venture-detail.svg"),
                "Кросівки для прогулянок пересіченою місцевістю. Рельєфний протектор забезпечує зчеплення на ґрунті та нерівних поверхнях."));
    }
}
