package app.modules.user.post.infrastructure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PostEntity {

    @Id
    @SequenceGenerator(sequenceName = "post_seq", name = "post_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "post_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "content")
    private String content;
    @Column(name = "post_views", columnDefinition = "bigint default 0 not null")
    private Long postViews;
    @Setter
    @Column(name = "creation_timestamp")
    private LocalDateTime creationDate;
    @Column(name = "user_id")
    private Long userId;

    public PostEntity(
        String content,
        Long postViews,
        LocalDateTime creationDate,
        Long userId
    ) {
        this(null,
             content,
             postViews,
             creationDate,
             userId
        );
    }
}
