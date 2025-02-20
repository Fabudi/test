package inc.fabudi.test.repository;

import inc.fabudi.test.model.Catalog1Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface Catalog1EntryRepository extends JpaRepository<Catalog1Entry, UUID> {
    List<Catalog1Entry> findByCatalogId(UUID catalogId);
}