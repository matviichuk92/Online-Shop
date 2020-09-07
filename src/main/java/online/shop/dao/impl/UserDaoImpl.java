package online.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import online.shop.dao.UserDao;
import online.shop.db.Storage;
import online.shop.model.User;

public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        Storage.addUser(user);
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        return Storage.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll(User user) {
        return Storage.users;
    }

    @Override
    public User update(User user) {
        IntStream.range(0, Storage.users.size())
                .filter(i -> Storage.users.get(i).getId().equals(user.getId()))
                .forEach(i -> Storage.users.set(i, user));
        return user;
    }

    @Override
    public boolean deleteById(User user) {
        return Storage.users.removeIf(someUser -> someUser.getId().equals(user.getId()));
    }
}
