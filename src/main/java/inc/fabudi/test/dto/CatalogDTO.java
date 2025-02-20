package inc.fabudi.test.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CatalogDTO {
    private UUID id;
    private String name;
}