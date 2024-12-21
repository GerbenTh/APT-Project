package fact.it.gewasservice.repository;

import fact.it.gewasservice.model.Gewas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GewasRepository extends MongoRepository<Gewas, String> {

    Optional<Gewas> findByUuid(UUID uuid);
}
