package production.ready.learn.springdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import production.ready.learn.springdev.entity.SessionEntity;
import production.ready.learn.springdev.entity.User;

import java.util.List;

public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    List<SessionEntity> findByUser(User user);
    SessionEntity findbyRefreshToken(String refreshToken);

}
