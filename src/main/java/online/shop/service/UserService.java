package online.shop.service;

import java.util.List;
import online.shop.model.User;

public interface UserService {
    User create(User user);

    User get(Long id);

    List<User> getAll();

    User update(User user);

    boolean delete(Long id);
}
