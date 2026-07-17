package production.ready.learn.springdev.repository;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import production.ready.learn.springdev.entity.SessionEntity;
import production.ready.learn.springdev.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);


}
