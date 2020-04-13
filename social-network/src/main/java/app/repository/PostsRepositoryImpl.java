package app.repository;

import app.model.Post;
import app.repository.interfaces.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class PostsRepositoryImpl implements PostsRepository {

    private static final String SQL_SELECT_BY_ID = "select * from post where postId = ? order by date desc";
    private static final String SQL_SELECT_ALL = "select * from post order by date desc";
    private static final String SQL_INSERT = "insert into post(authorId, whereId, text, date) values (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_BY_USER_ID = "select * from post where whereId = ? order by date desc";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Post> postRowMapper = (row, rowNumber) ->
            Post.builder()
                    .text(row.getString("text"))
                    .date(row.getTimestamp("date"))
                    .whereId(row.getLong("whereId"))
                    .authorId(row.getLong("authorId"))
                    .build();

    @Override
    public Optional<Post> find(Long id) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, postRowMapper);
            return Optional.ofNullable(post);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, postRowMapper);
    }

    @Override
    public List<Post> findAllByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_USER_ID, postRowMapper, userId);
    }

    @Override
    public void save(Post entity) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, entity.getAuthorId());
            statement.setLong(2, entity.getWhereId());
            statement.setString(3, entity.getText());
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            return statement;
        }, keyHolder);

        entity.setPostId(Objects.requireNonNull(keyHolder.getKey()).longValue());

    }

    @Override
    public void delete(Long aLong) {

    }
}
