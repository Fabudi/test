package inc.fabudi.test.mapper;

import inc.fabudi.test.dto.CatalogDTO;
import inc.fabudi.test.model.Catalog;

public class CatalogMapper {
    public static CatalogDTO toDTO(Catalog catalog) {
        CatalogDTO dto = new CatalogDTO();
        dto.setId(catalog.getId());
        dto.setName(catalog.getName());
        return dto;
    }
}
