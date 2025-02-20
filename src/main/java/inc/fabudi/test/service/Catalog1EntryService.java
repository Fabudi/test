package inc.fabudi.test.service;

import inc.fabudi.test.dto.Catalog1EntryDTO;
import inc.fabudi.test.mapper.Catalog1EntryMapper;
import inc.fabudi.test.model.Catalog1Entry;
import inc.fabudi.test.repository.Catalog1EntryRepository;
import inc.fabudi.test.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class Catalog1EntryService {
    private final Catalog1EntryRepository entryRepository;
    private final CatalogRepository catalogRepository;

    public List<Catalog1EntryDTO> getEntriesByCatalogId(UUID catalogId) {
        log.info("Fetching list of all entries in catalog with ID: {}", catalogId);
        return entryRepository.findByCatalogId(catalogId).stream().map(Catalog1EntryMapper::toDTO).collect(Collectors.toList());
    }

    public Catalog1EntryDTO getEntryById(UUID entryId) {
        log.info("Fetching entry with ID: {}", entryId);
        return entryRepository.findById(entryId).map(Catalog1EntryMapper::toDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found"));
    }

    public Catalog1EntryDTO addEntry(Catalog1EntryDTO data) {
        Catalog1Entry entry = entryRepository.save(Catalog1EntryMapper.toEntity(data));
        return Catalog1EntryMapper.toDTO(entry);
    }

    public Catalog1EntryDTO updateEntry(Catalog1EntryDTO updatedEntry) {
        log.info("Updating entry with ID: {}", updatedEntry.getId());
        return entryRepository.findById(updatedEntry.getId()).map(entry -> {
            entry.setAmount(updatedEntry.getAmount());
            entry.setDescription(updatedEntry.getDescription());
            Catalog1Entry savedEntry = entryRepository.save(entry);
            log.info("Entry with ID: {} saved", updatedEntry.getId());
            return Catalog1EntryMapper.toDTO(savedEntry);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entry not found"));
    }

    public void deleteEntry(UUID entryId) {
        log.info("Deleting entry with ID: {}", entryId);
        entryRepository.deleteById(entryId);
    }
}
