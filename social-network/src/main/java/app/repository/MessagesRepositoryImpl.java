package app.repository;

import app.model.Message;
import app.repository.interfaces.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MessagesRepositoryImpl implements MessagesRepository {

    private static final String SQL_SELECT_BY_ID = "select * from messages where messageId = ?";
    private static final String SQL_SELECT_ALL = "select * from messages";
    private static final String SQL_INSERT = "insert into messages(userId, text) values (?, ?)";
    private static final String SQL_SELECT_ALL_BY_USER_ID = "select * from post where userId = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Message> messageRowMapper = (row, rowNumber) ->
            Message.builder()
                    .text(row.getString("text"))
                    .messageId(row.getLong("messageId"))
                    .userId(row.getLong("userId"))
                    .build();

    @Override
    public Optional<Message> find(Long id) {
        try {
            Message message = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, messageRowMapper);
            return Optional.ofNullable(message);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, messageRowMapper);
    }

    @Override
    public List<Message> findAllByUserId(Long userId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_USER_ID, messageRowMapper, userId);
    }

    @Override
    public void save(Message entity) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, entity.getUserId());
            statement.setString(2, entity.getText());
            return statement;
        }, keyHolder);

        entity.setUserId(Objects.requireNonNull(keyHolder.getKey()).longValue());

    }

    @Override
    public void delete(Long id) {

    }
}
