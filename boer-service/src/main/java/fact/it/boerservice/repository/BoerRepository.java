package fact.it.boerservice.repository;
import fact.it.boerservice.model.Boer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface BoerRepository extends JpaRepository<Boer, Long> {

    Optional<Boer> findByUuid(UUID uuid);
    List<Boer> findByNameIn(List<String> name);

    List<Boer> findBoerByUuid(UUID uuid);
}






