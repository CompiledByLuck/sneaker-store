package com.ua.sneakerstore.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SneakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnFiveSneakerCards() throws Exception {
        mockMvc.perform(get("/api/sneakers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].brand").value("Adidas"))
                .andExpect(jsonPath("$[0].size").value(42))
                .andExpect(jsonPath("$[0].coverUrl").value("/images/adidas-ultraboost.svg"))
                .andExpect(jsonPath("$[0].description").doesNotExist());
    }

    @Test
    void shouldReturnSneakerDetails() throws Exception {
        mockMvc.perform(get("/api/sneakers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Adidas"))
                .andExpect(jsonPath("$.model").value("Ultraboost Light"))
                .andExpect(jsonPath("$.size").value(42))
                .andExpect(jsonPath("$.material").value("Текстиль Primeknit"))
                .andExpect(jsonPath("$.images", hasSize(2)))
                .andExpect(jsonPath("$.description").isNotEmpty());
    }

    @Test
    void shouldReturn404ForUnknownSneaker() throws Exception {
        mockMvc.perform(get("/api/sneakers/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldServeSneakerImageAsSvg() throws Exception {
        mockMvc.perform(get("/images/adidas-ultraboost.svg"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("image/svg+xml")));
    }
}
