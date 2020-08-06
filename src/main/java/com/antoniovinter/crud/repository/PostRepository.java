package com.antoniovinter.crud.repository;

import com.antoniovinter.crud.model.Post;
import com.antoniovinter.crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findByUser(User user);
}
