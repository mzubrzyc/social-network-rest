package app.modules.user.post.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Post {
    private Long postId;
    private Long userId;
    private String content;
    private Long postViews;
    private LocalDateTime creationTimestamp;

    public Post(
        Long userId,
        String content,
        LocalDateTime creationTimestamp
    ) {
        this(null,
             userId,
             content,
             0L,
             creationTimestamp
        );
    }
    public Post(
        Long userId,
        String content,
        Long postViews,
        LocalDateTime creationTimestamp
    ) {
        this(null,
             userId,
             content,
             postViews,
             creationTimestamp
        );
    }

}
