package app.modules.user.infrastructure;

import app.modules.user.post.infrastructure.PostEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserEntity {

    @Id
    @SequenceGenerator(sequenceName = "user_id_seq", name = "user_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "login", columnDefinition = "varchar(26) not null unique")
    private String login;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "nick")
    private String nick;
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @BatchSize(size = 100)
    private List<PostEntity> postEntityList;

    public UserEntity(
        Long userId,
        String login,
        String name,
        String surname,
        String nick,
        LocalDate creationDate
    ) {
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.nick = nick;
        this.creationDate = creationDate;
    }

    public UserEntity(
        String login,
        String name,
        String surname,
        String nick,
        LocalDate creationDate
    ) {
        this(null,
             login,
             name,
             surname,
             nick,
             creationDate);
    }
}
