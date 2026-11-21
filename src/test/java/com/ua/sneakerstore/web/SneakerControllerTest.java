package com.ua.sneakerstore.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SneakerControllerTest {

    private static final String CREATE_REQUEST = """
            {
              "brand": "Reebok",
              "model": "Classic Leather",
              "size": 42,
              "price": 3499.00,
              "color": "Білий",
              "gender": "Унісекс",
              "category": "Тестові",
              "material": "Натуральна шкіра",
              "season": "Демісезон",
              "soleMaterial": "Гума",
              "waterproof": false,
              "coverUrl": "/images/reebok-classic.svg",
              "images": ["/images/reebok-classic.svg"],
              "description": "Тестова пара для перевірки CRUD."
            }
            """;

    private static final String UPDATE_REQUEST = """
            {
              "brand": "Reebok",
              "model": "Classic Leather Updated",
              "size": 43,
              "price": 3699.00,
              "color": "Чорний",
              "gender": "Унісекс",
              "category": "Оновлені тестові",
              "material": "Натуральна шкіра",
              "season": "Демісезон",
              "soleMaterial": "Гума",
              "waterproof": false,
              "coverUrl": "/images/reebok-classic.svg",
              "images": ["/images/reebok-classic.svg"],
              "description": "Оновлений опис тестової пари."
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void shouldFilterSneakersByCategoryIgnoringCase() throws Exception {
        mockMvc.perform(get("/api/sneakers").param("category", "бігові"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].category").value("Бігові"))
                .andExpect(jsonPath("$[1].category").value("Бігові"));
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
    void shouldCreateSneakerAndReturn201() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/sneakers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CREATE_REQUEST))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.brand").value("Reebok"))
                .andExpect(jsonPath("$.model").value("Classic Leather"))
                .andExpect(jsonPath("$.size").value(42))
                .andExpect(jsonPath("$.images", hasSize(1)))
                .andReturn();

        long id = readId(result);
        mockMvc.perform(delete("/api/sneakers/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateSneakerAndPreserveItsId() throws Exception {
        long id = createSneaker();

        mockMvc.perform(put("/api/sneakers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UPDATE_REQUEST))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.model").value("Classic Leather Updated"))
                .andExpect(jsonPath("$.size").value(43))
                .andExpect(jsonPath("$.category").value("Оновлені тестові"));

        mockMvc.perform(delete("/api/sneakers/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenUpdatingUnknownSneaker() throws Exception {
        mockMvc.perform(put("/api/sneakers/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UPDATE_REQUEST))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteSneakerAndReturn204() throws Exception {
        long id = createSneaker();

        mockMvc.perform(delete("/api/sneakers/{id}", id))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        mockMvc.perform(get("/api/sneakers/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn404WhenDeletingUnknownSneaker() throws Exception {
        mockMvc.perform(delete("/api/sneakers/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldServeSneakerImageAsSvg() throws Exception {
        mockMvc.perform(get("/images/adidas-ultraboost.svg"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("image/svg+xml")));
    }

    private long createSneaker() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/sneakers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CREATE_REQUEST))
                .andExpect(status().isCreated())
                .andReturn();
        return readId(result);
    }

    private long readId(MvcResult result) throws Exception {
        JsonNode json = objectMapper.readTree(result.getResponse().getContentAsString());
        return json.get("id").asLong();
    }
}
