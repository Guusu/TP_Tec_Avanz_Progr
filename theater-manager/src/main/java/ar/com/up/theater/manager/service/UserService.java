package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.model.User;

public interface UserService {
    User getUserByUsername(String username);
}
