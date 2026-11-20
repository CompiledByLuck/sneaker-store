package com.ua.sneakerstore.web;

import com.ua.sneakerstore.service.SneakerService;
import com.ua.sneakerstore.web.dto.SneakerCardDto;
import com.ua.sneakerstore.web.dto.SneakerDetailDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<SneakerCardDto> list() {
        return sneakerService.findAll();
    }

    @GetMapping("/{id}")
    public SneakerDetailDto getOne(@PathVariable Long id) {
        return sneakerService.findById(id);
    }
}
