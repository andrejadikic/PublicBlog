package com.example.publicblog.repositories.user;

import com.example.publicblog.model.User;

public interface UserRepository {
  User findUser(String username);
}
