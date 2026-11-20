package com.ua.sneakerstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SneakerNotFoundException extends RuntimeException {

    public SneakerNotFoundException(Long id) {
        super("Кросівки з id=" + id + " не знайдено");
    }
}
