package com.jeff.service;

import com.jeff.entity.User;

public interface UserService {

    User findUser(String username, String password);

}
