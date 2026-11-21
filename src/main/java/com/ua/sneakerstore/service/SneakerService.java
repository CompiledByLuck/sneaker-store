package com.ua.sneakerstore.service;

import com.ua.sneakerstore.web.dto.SneakerCardDto;
import com.ua.sneakerstore.web.dto.SneakerDetailDto;
import com.ua.sneakerstore.web.dto.SneakerRequest;

import java.util.List;

public interface SneakerService {
    List<SneakerCardDto> findAll(String category);

    SneakerDetailDto findById(Long id);

    SneakerDetailDto create(SneakerRequest request);

    SneakerDetailDto update(Long id, SneakerRequest request);

    void delete(Long id);
}
