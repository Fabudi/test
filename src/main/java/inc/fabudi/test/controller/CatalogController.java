package inc.fabudi.test.controller;

import inc.fabudi.test.dto.CatalogDTO;
import inc.fabudi.test.model.Catalog;
import inc.fabudi.test.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;

    @GetMapping
    public List<CatalogDTO> getAllCatalogs() {
        return catalogService.getAllCatalogs();
    }

}
