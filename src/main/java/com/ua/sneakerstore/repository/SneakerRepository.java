package com.ua.sneakerstore.repository;

import com.ua.sneakerstore.domain.Sneaker;

import java.util.List;
import java.util.Optional;

public interface SneakerRepository {
    List<Sneaker> findAll();

    Optional<Sneaker> findById(Long id);

    Sneaker save(Sneaker sneaker);
}
