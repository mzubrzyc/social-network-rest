package app.utils;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.jdbc.core.JdbcTemplate;

@TestComponent
public class TableEraser {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final List<String> TABLES_TO_ERASE = List.of(
        "users",
        "posts"
    );

    public void deleteFromTables() {
        TABLES_TO_ERASE.forEach(table -> jdbcTemplate.execute("delete from " + table));
    }

}
