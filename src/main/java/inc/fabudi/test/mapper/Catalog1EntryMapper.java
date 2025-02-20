package inc.fabudi.test.mapper;

import inc.fabudi.test.dto.Catalog1EntryDTO;
import inc.fabudi.test.model.Catalog1Entry;

public class Catalog1EntryMapper {
    public static Catalog1EntryDTO toDTO(Catalog1Entry entry) {
        Catalog1EntryDTO dto = new Catalog1EntryDTO();
        dto.setId(entry.getId());
        dto.setCatalogId(entry.getCatalog().getId());
        dto.setAmount(entry.getAmount());
        dto.setDescription(entry.getDescription());
        return dto;
    }

    public static Catalog1Entry toEntity(Catalog1EntryDTO dto) {
        Catalog1Entry entry = new Catalog1Entry();
        entry.setAmount(dto.getAmount());
        entry.setDescription(dto.getDescription());
        return entry;
    }
}