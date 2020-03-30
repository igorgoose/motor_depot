package by.schepov.motordepot.builder.impl.user;

import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUserBuilder implements Builder<User> {

    private final UserBuilder userBuilder = new UserBuilder();
    private final ResultSet resultSet;

    public ResultSetUserBuilder(ResultSet resultSet){
        this.resultSet = resultSet;
    }

    @Override
    public void reset() {
        userBuilder.reset();
    }

    public ResultSetUserBuilder withId(String idColumnName) throws SQLException {
        userBuilder.withId(resultSet.getInt(idColumnName));
        return this;
    }

    public ResultSetUserBuilder withBlocked(String blockedColumnName) throws SQLException {
        userBuilder.withBlocked(resultSet.getBoolean(blockedColumnName));
        return this;
    }

    public ResultSetUserBuilder withEmail(String emailColumnName) throws SQLException {
        userBuilder.withEmail(resultSet.getString(emailColumnName));
        return this;
    }

    public ResultSetUserBuilder withLogin(String loginColumnName) throws SQLException {
        userBuilder.withLogin(resultSet.getString(loginColumnName));
        return this;
    }

    public ResultSetUserBuilder withPassword(String passwordColumnName) throws SQLException {
        userBuilder.withPassword(resultSet.getString(passwordColumnName));
        return this;
    }

    public ResultSetUserBuilder withRole(String roleNameColumn) throws SQLException {
        userBuilder.withRole(Role.valueOf(resultSet.getString(roleNameColumn)));
        return this;
    }

    @Override
    public User build() {
        return userBuilder.build();
    }
}
