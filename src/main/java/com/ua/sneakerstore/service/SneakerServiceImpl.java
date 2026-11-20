package com.ua.sneakerstore.service;

import com.ua.sneakerstore.domain.Sneaker;
import com.ua.sneakerstore.exception.SneakerNotFoundException;
import com.ua.sneakerstore.repository.SneakerRepository;
import com.ua.sneakerstore.service.mapper.SneakerMapper;
import com.ua.sneakerstore.web.dto.SneakerCardDto;
import com.ua.sneakerstore.web.dto.SneakerDetailDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SneakerServiceImpl implements SneakerService {

    private final SneakerRepository repository;
    private final SneakerMapper mapper;

    public SneakerServiceImpl(SneakerRepository repository, SneakerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<SneakerCardDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toCard)
                .toList();
    }

    @Override
    public SneakerDetailDto findById(Long id) {
        Sneaker sneaker = repository.findById(id)
                .orElseThrow(() -> new SneakerNotFoundException(id));
        return mapper.toDetail(sneaker);
    }
}
