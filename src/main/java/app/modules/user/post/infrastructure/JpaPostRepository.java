package app.modules.user.post.infrastructure;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "select * from posts order by post_views desc, post_id", nativeQuery = true)
    List<PostEntity> findPostsWithMostViewsLimitedTo(Pageable pageable);

}
