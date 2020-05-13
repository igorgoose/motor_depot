package by.schepov.motordepot.builder.impl.user;

import by.schepov.motordepot.builder.Builder;
import by.schepov.motordepot.entity.Role;
import by.schepov.motordepot.entity.User;
import by.schepov.motordepot.entity.UserStatus;
import by.schepov.motordepot.specification.Column;

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

    public ResultSetUserBuilder withId(Column idColumn) throws SQLException {
        userBuilder.withId(resultSet.getInt(idColumn.getName()));
        return this;
    }

    public ResultSetUserBuilder withStatus(Column statusColumn) throws SQLException {
        userBuilder.withStatus(UserStatus.valueOf(resultSet.getString(statusColumn.getName())));
        return this;
    }

    public ResultSetUserBuilder withEmail(Column emailColumn) throws SQLException {
        userBuilder.withEmail(resultSet.getString(emailColumn.getName()));
        return this;
    }

    public ResultSetUserBuilder withLogin(Column loginColumn) throws SQLException {
        userBuilder.withLogin(resultSet.getString(loginColumn.getName()));
        return this;
    }

    public ResultSetUserBuilder withPassword(Column passwordColumn) throws SQLException {
        userBuilder.withPassword(resultSet.getString(passwordColumn.getName()));
        return this;
    }

    public ResultSetUserBuilder withRole(Column roleNameColumn) throws SQLException {
        userBuilder.withRole(Role.valueOf(resultSet.getString(roleNameColumn.getName())));
        return this;
    }

    @Override
    public User build() {
        return userBuilder.build();
    }
}
