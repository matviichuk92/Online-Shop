package online.shop.service;

import online.shop.model.User;

public interface UserService extends GenericService<User, Long> {
    User create(User user);

    User update(User user);
}
