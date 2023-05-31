package app.modules.user.post.infrastructure;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "FROM PostEntity p order by p.postViews desc, p.postId")
    List<PostEntity> findPostsWithMostViewsLimitedTo(Pageable pageable);

}
