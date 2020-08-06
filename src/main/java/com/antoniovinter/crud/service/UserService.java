package com.antoniovinter.crud.service;

import com.antoniovinter.crud.converter.UserConverter;
import com.antoniovinter.crud.dto.UserDto;
import com.antoniovinter.crud.exceptions.UserNotFound;
import com.antoniovinter.crud.model.User;
import com.antoniovinter.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository repo;
    private UserConverter userConverter;

    public UserService(UserRepository repo, UserConverter userConverter) {
        this.repo = repo;
        this.userConverter = userConverter;
    }

    public UserDto findById(int id){
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFound("user with id not found, id: " + id));
        return userConverter.UserToDto(user);
    }

    public UserDto findByName(String name){
        User user = repo.findByName(name);
        if(user !=null){
            return userConverter.UserToDto(user);
        }else{
            throw new UserNotFound("user with name not found, name: " + name);
        }
    }

    public List<UserDto> findAllUsers(){
        List<User> users = repo.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> userConverter.UserToDto(user))
                .collect(Collectors.toList());

        return userDtos;
    }

    public User addUser(User user){
        if(user !=null){
            return repo.save(user);
        }else{
            throw new UserNotFound("user can't be added");
        }
    }

    public User replaceUser(User newUser, int id){
        User user = repo.findById(id)
                .orElseThrow( () -> new UserNotFound("user with id not found"));
        user.setName(newUser.getName());
        user.setPassword(newUser.getPassword());
        user.setRole(newUser.getRole());
        repo.save(user);
        return user;
    }
}
