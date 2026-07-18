package production.ready.learn.springdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import production.ready.learn.springdev.entity.SessionEntity;
import production.ready.learn.springdev.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    List<SessionEntity> findByUser(User user);
    Optional<SessionEntity> findbyRefreshToken(String refreshToken);

}
