package inc.fabudi.test.service;

import inc.fabudi.test.dto.CatalogDTO;
import inc.fabudi.test.mapper.CatalogMapper;
import inc.fabudi.test.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogService {
    private final CatalogRepository catalogRepository;

    public List<CatalogDTO> getAllCatalogs() {
        log.info("Fetching list of all catalogs");
        return catalogRepository.findAll().stream().map(CatalogMapper::toDTO).collect(Collectors.toList());
    }

}
