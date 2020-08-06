package com.antoniovinter.crud.repository;

import com.antoniovinter.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
