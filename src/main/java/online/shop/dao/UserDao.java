package online.shop.dao;

import java.util.Optional;
import online.shop.model.User;

public interface UserDao extends GenericDao<User, Long> {
    Optional<User> findByLogin(String login);
}
