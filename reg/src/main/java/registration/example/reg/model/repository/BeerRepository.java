package registration.example.reg.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import registration.example.reg.model.entity.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}
