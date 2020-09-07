package online.shop.dao;

import java.util.List;
import java.util.Optional;
import online.shop.model.User;

public interface UserDao {
    User create(User user);

    Optional<User> getById(Long id);

    List<User> getAll(User user);

    User update(User user);

    boolean deleteById(User user);

}
