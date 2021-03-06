package online.shop.security;

import java.util.Optional;
import online.shop.exception.AuthenticationException;
import online.shop.lib.Inject;
import online.shop.lib.Service;
import online.shop.model.User;
import online.shop.service.UserService;
import online.shop.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        Optional<User> userFromDB = userService.findByLogin(login);
        if (userFromDB.isPresent() && HashUtil.hashPassword(password, userFromDB.get().getSalt())
                .equals(userFromDB.get().getPassword())) {
            return userFromDB.get();
        }
        throw new AuthenticationException("Incorrect login o password!");
    }
}
