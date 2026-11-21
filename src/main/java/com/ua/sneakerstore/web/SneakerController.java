package com.ua.sneakerstore.web;

import com.ua.sneakerstore.service.SneakerService;
import com.ua.sneakerstore.web.dto.SneakerCardDto;
import com.ua.sneakerstore.web.dto.SneakerDetailDto;
import com.ua.sneakerstore.web.dto.SneakerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sneakers")
public class SneakerController {

    private final SneakerService sneakerService;

    public SneakerController(SneakerService sneakerService) {
        this.sneakerService = sneakerService;
    }

    @GetMapping
    public List<SneakerCardDto> list(@RequestParam(required = false) String category) {
        return sneakerService.findAll(category);
    }

    @GetMapping("/{id}")
    public SneakerDetailDto getOne(@PathVariable Long id) {
        return sneakerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SneakerDetailDto create(@RequestBody SneakerRequest request) {
        return sneakerService.create(request);
    }

    @PutMapping("/{id}")
    public SneakerDetailDto update(@PathVariable Long id, @RequestBody SneakerRequest request) {
        return sneakerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sneakerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
