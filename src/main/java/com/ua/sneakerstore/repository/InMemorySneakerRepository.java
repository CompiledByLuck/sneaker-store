package com.ua.sneakerstore.repository;

import com.ua.sneakerstore.domain.Sneaker;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemorySneakerRepository implements SneakerRepository {

    private final ConcurrentMap<Long, Sneaker> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public List<Sneaker> findAll() {
        return storage.values().stream()
                .sorted(Comparator.comparing(Sneaker::id))
                .toList();
    }

    @Override
    public Optional<Sneaker> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Sneaker> findByCategory(String category) {
        return storage.values().stream()
                .filter(sneaker -> category.equalsIgnoreCase(sneaker.category()))
                .sorted(Comparator.comparing(Sneaker::id))
                .toList();
    }

    @Override
    public Sneaker save(Sneaker sneaker) {
        Objects.requireNonNull(sneaker, "Кросівки не можуть бути null");

        Sneaker sneakerToSave = sneaker;
        if (sneaker.id() == null) {
            sneakerToSave = sneaker.withId(sequence.incrementAndGet());
        } else {
            sequence.accumulateAndGet(sneaker.id(), Math::max);
        }

        storage.put(sneakerToSave.id(), sneakerToSave);
        return sneakerToSave;
    }

    @Override
    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}
