package online.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import online.shop.dao.UserDao;
import online.shop.exception.DataProcessingException;
import online.shop.lib.Dao;
import online.shop.model.Role;
import online.shop.model.User;
import online.shop.util.ConnectionUtil;

@Dao
public class UserDaoJdbcImpl implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUserFromResultSet(resultSet, connection));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find by login : " + login, e);
        }
    }

    @Override
    public User create(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "INSERT INTO users(name, login, password) values(?, ?, ?)";
            PreparedStatement statement =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            addUserRoles(user, connection);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create user : " + user, e);
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE user_id = ? AND deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUserFromResultSet(resultSet, connection));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't find by id : " + id, e);
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet, connection));
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get list of users!", e);
        }
    }

    @Override
    public User update(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE users SET name = ?, login = ?, password = ? WHERE user_id = ?"
                    + "AND deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create user : " + user, e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "UPDATE users SET deleted = true WHERE user_id = ? AND deleted = false";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't deleted the user by id: " + id, e);
        }
    }

    private User getUserFromResultSet(ResultSet resultSet, Connection connection) throws SQLException {
        Long id = resultSet.getLong("user_id");
        String name = resultSet.getString("name");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        Set<Role> roles = getUserRole(id, connection);
        User user = new User(name, login, password);
        user.setId(id);
        user.setRoles(roles);
        return user;
    }

    private Set<Role> getUserRole(Long userId, Connection connection) throws SQLException {
        Set<Role> roles = new HashSet<>();

        String query = "SELECT role_name FROM roles INNER JOIN users_roles "
                + "ON users_roles.role_id = roles.role_id "
                + "WHERE users_roles.user_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, userId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            roles.add(Role.of(resultSet.getString("role_name")));
        }
        return roles;
    }

    private void addUserRoles(User user, Connection connection) throws SQLException {
        String query = "INSERT INTO users_roles(role_id, user_id) VALUE(?, ?)";
        for (Role role : user.getRoles()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, getRoleById(role.getRoleName()));
            statement.setLong(2, user.getId());
            statement.executeUpdate();
        }
    }

    private Long getRoleById(Role.RoleName roleName) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String query = "SELECT role_id FROM roles WHERE role_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, roleName.name());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong("role_id");
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get the role by id : " + roleName, e);
        }
    }
}
