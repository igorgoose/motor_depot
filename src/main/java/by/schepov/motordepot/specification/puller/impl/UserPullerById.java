package by.schepov.motordepot.specification.puller.impl;

import by.schepov.motordepot.builder.impl.user.ResultSetUserBuilder;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.specification.Column;
import by.schepov.motordepot.specification.puller.PullerById;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum UserPullerById implements PullerById<User> {

    INSTANCE;

    private static final String QUERY =
            "SELECT users.id, login, password, role_id, email, is_blocked, role FROM motor_depot.users as users " +
                    "LEFT JOIN motor_depot.roles as roles on role_id = roles.id " +
                    "WHERE users.id = ?";


    @Override
    public User pull(int id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                ResultSetUserBuilder builder = new ResultSetUserBuilder(resultSet);
                if (resultSet.next()) {
                    builder.reset();
                    return (builder.withId(Column.ID)
                            .withBlocked(Column.IS_BLOCKED)
                            .withEmail(Column.EMAIL)
                            .withLogin(Column.LOGIN)
                            .withPassword(Column.PASSWORD)
                            .withRole(Column.ROLE)
                            .build());
                }
                return null;
            }
        }
    }
}
