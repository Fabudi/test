package inc.fabudi.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import inc.fabudi.test.dto.Catalog1EntryDTO;
import inc.fabudi.test.mapper.Catalog1EntryMapper;
import inc.fabudi.test.model.Catalog;
import inc.fabudi.test.model.Catalog1Entry;
import inc.fabudi.test.service.Catalog1EntryService;
import inc.fabudi.test.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Catalog1EntryController.class)
public class Catalog1ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CatalogService service;
    @MockitoBean
    private Catalog1EntryService entryService;

    private final UUID catalogId = UUID.randomUUID();
    private final UUID entryId = UUID.randomUUID();
    @Autowired
    ObjectMapper objectMapper;
    @Test
    public void shouldReturnEntryByID() throws Exception {
        Catalog catalog = new Catalog(catalogId, "TestCatalog", LocalDateTime.now());
        Catalog1Entry entry = new Catalog1Entry();
        entry.setId(entryId);
        entry.setCatalog(catalog);
        entry.setDescription("TestDescription");
        entry.setAmount(42);
        Catalog1EntryDTO mockEntry = Catalog1EntryMapper.toDTO(entry);

        when(entryService.getEntryById(entryId)).thenReturn(mockEntry);

        this.mockMvc.perform(
                get("/api/v1/catalogs/{catalogId}/entries/{entryId}", catalogId, entryId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(entryId.toString())))
                    .andExpect(jsonPath("$.catalogId", is(catalogId.toString())))
                    .andExpect(jsonPath("$.amount", is(42)))
                    .andExpect(jsonPath("$.description", is("TestDescription")));
    }

    @Test
    void shouldAddEntry() throws Exception {
        Catalog1EntryDTO requestEntry = new Catalog1EntryDTO();
        requestEntry.setCatalogId(catalogId);
        requestEntry.setDescription("TestDescription");
        requestEntry.setAmount(42);

        Catalog1EntryDTO savedEntry = new Catalog1EntryDTO();
        savedEntry.setId(entryId);
        savedEntry.setCatalogId(catalogId);
        savedEntry.setDescription("TestDescription");
        savedEntry.setAmount(42);

        when(entryService.addEntry(eq(catalogId), any(Catalog1EntryDTO.class))).thenReturn(savedEntry);

        mockMvc.perform(
                post("/api/v1/catalogs/{catalogId}/entries", catalogId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestEntry)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(entryId.toString()))
                    .andExpect(jsonPath("$.catalogId", is(catalogId.toString())))
                    .andExpect(jsonPath("$.amount", is(42)))
                    .andExpect(jsonPath("$.description", is("TestDescription")));
    }

    @Test
    void shouldUpdateEntry() throws Exception {
        Catalog1EntryDTO updatedEntry = new Catalog1EntryDTO();
        updatedEntry.setId(entryId);
        updatedEntry.setCatalogId(catalogId);
        updatedEntry.setDescription("TestDescription");
        updatedEntry.setAmount(42);

        when(entryService.updateEntry(eq(entryId), any(Catalog1EntryDTO.class))).thenReturn(updatedEntry);

        mockMvc.perform(
                put("/api/v1/catalogs/{catalogId}/entries/{entryId}", catalogId, entryId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(updatedEntry)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(entryId.toString()))
                    .andExpect(jsonPath("$.catalogId", is(catalogId.toString())))
                    .andExpect(jsonPath("$.amount", is(42)))
                    .andExpect(jsonPath("$.description", is("TestDescription")));
    }

    @Test
    void shouldDeleteEntry() throws Exception {
        doNothing().when(entryService).deleteEntry(entryId);
        mockMvc.perform(
                delete("/api/v1/catalogs/{catalogId}/entries/{entryId}", catalogId, entryId))
                    .andExpect(status().isOk());
        verify(entryService, times(1)).deleteEntry(entryId);
    }

}
