package online.shop.security;

import online.shop.exception.AuthenticationException;
import online.shop.model.User;

public interface AuthenticationService {
    User login(String login, String password) throws AuthenticationException;
}
