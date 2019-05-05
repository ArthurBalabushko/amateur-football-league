package by.itacademy.database.dao;

import by.itacademy.database.connection.ConnectionPool;
import by.itacademy.database.entity.Role;
import by.itacademy.database.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String GET_BY_ID = "SELECT "
            + "users.id           AS user_id,"
            + "users.first_name   AS user_first_name,"
            + "users.last_name    AS user_last_name,"
            + "users.birth_day    AS user_birth_day,"
            + "users.phone_number AS user_phone_number,"
            + "users.email        AS user_email,"
            + "users.password     AS user_password,"
            + "users.role_id      AS role_id, "
            + "role.name          AS role_name "
            + "FROM users "
            + "LEFT JOIN role "
            + "ON users.role_id = role.id "
            + " WHERE users.id = ?";

    @SneakyThrows
    public Optional<User> findById(Integer id) {
        User user = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("user_id"))
                        .firstName(resultSet.getString("user_first_name"))
                        .lastName(resultSet.getString("user_last_name"))
                        .birthDay(resultSet.getDate("user_birth_day").toLocalDate())
                        .phoneNumber(resultSet.getString("user_phone_number"))
                        .email(resultSet.getString("user_email"))
                        .password(resultSet.getString("user_password"))
                        .role(Role.builder()
                                .id(resultSet.getInt("role_id"))
                                .name(resultSet.getString("role_name"))
                                .build())
                        .build();
            }
        }

        return ofNullable(user);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
