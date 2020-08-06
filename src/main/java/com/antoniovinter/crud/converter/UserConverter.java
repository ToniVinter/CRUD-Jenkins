package com.antoniovinter.crud.converter;

import com.antoniovinter.crud.dto.UserDto;
import com.antoniovinter.crud.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto UserToDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
