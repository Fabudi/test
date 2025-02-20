package inc.fabudi.test.controller;

import inc.fabudi.test.dto.Catalog1EntryDTO;
import inc.fabudi.test.service.Catalog1EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/catalogs/{catalogId}/entries")
@RequiredArgsConstructor
public class Catalog1EntryController {
    private final Catalog1EntryService entryService;

    @GetMapping("/{entryId}")
    public Catalog1EntryDTO getEntryById(@PathVariable UUID entryId) {
        return entryService.getEntryById(entryId);
    }

    @PostMapping
    public Catalog1EntryDTO addEntry(@RequestBody Catalog1EntryDTO entry) {
        return entryService.addEntry(entry);
    }

    @PutMapping("/{entryId}")
    public Catalog1EntryDTO updateEntry(@RequestBody Catalog1EntryDTO entry) {
        return entryService.updateEntry(entry);
    }

    @DeleteMapping("/{entryId}")
    public void deleteEntry(@PathVariable UUID entryId) {
        entryService.deleteEntry(entryId);
    }
}
