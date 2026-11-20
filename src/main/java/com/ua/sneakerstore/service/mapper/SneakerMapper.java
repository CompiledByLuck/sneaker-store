package com.ua.sneakerstore.service.mapper;

import com.ua.sneakerstore.domain.Sneaker;
import com.ua.sneakerstore.web.dto.SneakerCardDto;
import com.ua.sneakerstore.web.dto.SneakerDetailDto;
import org.springframework.stereotype.Component;

@Component
public class SneakerMapper {

    public SneakerCardDto toCard(Sneaker sneaker) {
        return new SneakerCardDto(
                sneaker.id(), sneaker.brand(), sneaker.model(), sneaker.size(),
                sneaker.price(), sneaker.color(), sneaker.category(), sneaker.coverUrl()
        );
    }

    public SneakerDetailDto toDetail(Sneaker sneaker) {
        return new SneakerDetailDto(
                sneaker.id(), sneaker.brand(), sneaker.model(), sneaker.size(),
                sneaker.price(), sneaker.color(), sneaker.gender(), sneaker.category(),
                sneaker.material(), sneaker.season(), sneaker.soleMaterial(),
                sneaker.waterproof(), sneaker.coverUrl(), sneaker.images(), sneaker.description()
        );
    }
}
