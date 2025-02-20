package inc.fabudi.test.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.util.UUID;

@Data
public class Catalog1EntryDTO {
    private UUID id;
    private UUID catalogId;
    private JsonNode data;
    private Integer amount;
    private String description;
}
