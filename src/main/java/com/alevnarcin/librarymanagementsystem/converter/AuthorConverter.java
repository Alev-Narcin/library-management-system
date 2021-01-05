package com.alevnarcin.librarymanagementsystem.converter;


import com.alevnarcin.librarymanagementsystem.dto.AuthorDto;
import com.alevnarcin.librarymanagementsystem.entity.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public AuthorDto authorEntityToAuthorDto(AuthorEntity authorEntity) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(authorEntity.getId());
        authorDto.setName(authorEntity.getName());

        return authorDto;
    }

    public AuthorEntity authorDtoToAuthorEntity(AuthorDto authorDto) {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(authorDto.getId());
        authorEntity.setName(authorDto.getName());

        return authorEntity;
    }
}
