package app.modules.user.post.infrastructure;

import app.modules.user.post.domain.Post;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PostEntityMapper {

    public static List<Post> fromPostEntity(List<PostEntity> postEntityList) {
        return postEntityList
            .stream()
            .map(entity ->
                     new Post(
                         entity.getPostId(),
                         entity.getUserId(),
                         entity.getContent(),
                         entity.getPostViews(),
                         entity.getCreationDate()
                     ))
            .toList();
    }

    public static Post fromPostEntity(PostEntity postEntity) {
        return new Post(
            postEntity.getPostId(),
            postEntity.getUserId(),
            postEntity.getContent(),
            postEntity.getPostViews(),
            postEntity.getCreationDate()
        );
    }

    public static PostEntity toPostEntity(Post post) {
        return new PostEntity(
            post.getPostId(),
            post.getContent(),
            post.getPostViews(),
            post.getCreationTimestamp(),
            post.getUserId()
        );
    }

    public static List<PostEntity> toPostEntity(List<Post> postList) {
        return postList.stream()
                       .map(post ->
                                new PostEntity(
                                    post.getContent(),
                                    post.getPostViews(),
                                    post.getCreationTimestamp(),
                                    post.getUserId())
                       )
                       .toList();
    }
}
