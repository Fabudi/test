package inc.fabudi.test.controller;

import inc.fabudi.test.mapper.CatalogMapper;
import inc.fabudi.test.model.Catalog;
import inc.fabudi.test.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatalogController.class)
public class CatalogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CatalogService service;

    @Test
    public void shouldReturnAllCatalogsFromService() throws Exception {
        Catalog catalog = new Catalog(UUID.randomUUID(), "TestCatalog", LocalDateTime.now());
        when(service.getAllCatalogs()).thenReturn(List.of(CatalogMapper.toDTO(catalog)));
        this.mockMvc.perform(
                get("/api/v1/catalogs")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(catalog.getId().toString())))
                .andExpect(jsonPath("$[0].name", is(catalog.getName())))
                .andExpect(content().string(containsString("Test")));
    }
}
