package registration.example.reg.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registration.example.reg.model.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
