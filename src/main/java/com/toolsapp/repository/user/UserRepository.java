package com.toolsapp.repository.user;

import com.toolsapp.domain.user.User;
import com.toolsapp.repository.CommonRepository;

import java.util.Optional;

public interface UserRepository extends CommonRepository<User> {

    Optional<User> findByName(String name);
}
