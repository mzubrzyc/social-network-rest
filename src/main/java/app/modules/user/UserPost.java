package app.modules.user;

import java.time.LocalDateTime;

public record UserPost(
    Long postId,
    Long userId,
    String content,
    Long postViews,
    LocalDateTime creationTimestamp
) {
}
