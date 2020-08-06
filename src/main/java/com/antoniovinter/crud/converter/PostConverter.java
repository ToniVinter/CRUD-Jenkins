package com.antoniovinter.crud.converter;

import com.antoniovinter.crud.dto.PostDto;
import com.antoniovinter.crud.dto.UserDto;
import com.antoniovinter.crud.model.Post;
import com.antoniovinter.crud.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {
    public PostDto PostToDto(Post post){
        ModelMapper modelMapper = new ModelMapper();
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }
}
