package app.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private Long postId;
    private UserDto userDto;
    private String content;
    private Long postViews;
    private LocalDateTime creationTimestamp;

}
