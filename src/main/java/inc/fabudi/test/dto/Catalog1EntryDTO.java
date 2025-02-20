package inc.fabudi.test.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class Catalog1EntryDTO {
    private UUID id;
    private UUID catalogId;
    private Integer amount;
    private String description;
}
