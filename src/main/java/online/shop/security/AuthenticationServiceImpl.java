package online.shop.security;

import online.shop.exception.AuthenticationException;
import online.shop.lib.Inject;
import online.shop.lib.Service;
import online.shop.model.User;
import online.shop.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String login, String password) throws AuthenticationException {
        User userFromDB = userService.findByLogin(login).orElseThrow(() ->
                new AuthenticationException("Incorrect login or password!"));
        if (userFromDB.getPassword().equals(password) && userFromDB.getLogin().equals(login)) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect login o password!");
    }
}
