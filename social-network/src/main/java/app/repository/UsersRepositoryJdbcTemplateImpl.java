package app.repository;

import app.model.User;
import app.repository.interfaces.UsersRepository;
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
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    //language=`SQL`
    private static final String SQL_SELECT_BY_ID = "select * from socialNetworkUser where userId = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from socialNetworkUser";
    //language=SQL
    private static final String SQL_INSERT = "insert into socialNetworkUser(firstName, secondName," +
            "email, password, pathToAva) values (?, ?, ?, ?, '/static/ava.jpg')";

    private static final String SQL_SELECT_BY_EMAIL = "select * from socialNetworkUser where email = ?";

    private static final String SQL_UPDATE_AVA_PATH = "update socialNetworkUser set pathToAva = ? where userId = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .userId(row.getLong("userId"))
                    .firstName(row.getString("firstName"))
                    .secondName(row.getString("secondName"))
                    .email(row.getString("email"))
                    .password(row.getString("password"))
                    .birthDate(row.getDate("birthDate"))
                    .phone(row.getString("phone"))
                    .town(row.getString("town"))
                    .pathToAva(row.getString("pathToAva"))
                    .build();


    public Optional<User> find(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public void save(User entity) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getSecondName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            return statement;
        }, keyHolder);

        entity.setUserId(Objects.requireNonNull(keyHolder.getKey()).longValue());

    }

    public void delete(Long id) {

    }

    public void updateAvaPath(Long userId, String newPath) {

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_UPDATE_AVA_PATH);
            statement.setString(1, newPath);
            statement.setLong(2, userId);
            return statement;
        });

    }
}