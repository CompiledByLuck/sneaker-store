package com.ua.sneakerstore.service;

import com.ua.sneakerstore.web.dto.SneakerCardDto;
import com.ua.sneakerstore.web.dto.SneakerDetailDto;

import java.util.List;

public interface SneakerService {
    List<SneakerCardDto> findAll();

    SneakerDetailDto findById(Long id);
}
