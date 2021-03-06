package online.shop.service.impl;

import java.util.List;
import java.util.Optional;
import online.shop.dao.UserDao;
import online.shop.lib.Inject;
import online.shop.lib.Service;
import online.shop.model.User;
import online.shop.service.UserService;
import online.shop.util.HashUtil;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User create(User user) {
        user.setSalt(HashUtil.getSalt());
        user.setPassword(HashUtil.hashPassword(user.getPassword(), user.getSalt()));
        return userDao.create(user);
    }

    @Override
    public User get(Long id) {
        return userDao.getById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.deleteById(id);
    }
}
