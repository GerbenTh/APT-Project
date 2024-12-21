package fact.it.veldservice.repository;

import fact.it.veldservice.model.Veld;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface VeldRepository extends JpaRepository<Veld, Integer> { }
