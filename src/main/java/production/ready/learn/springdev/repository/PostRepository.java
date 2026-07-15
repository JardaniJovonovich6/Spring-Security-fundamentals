package production.ready.learn.springdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import production.ready.learn.springdev.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity , Long> {
}
