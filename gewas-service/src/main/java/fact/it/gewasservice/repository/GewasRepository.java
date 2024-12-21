package fact.it.gewasservice.repository;

import fact.it.gewasservice.model.Gewas;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GewasRepository extends MongoRepository<Gewas, String> {
    List<Gewas> findByIdIn(List<String> gewas);
}
