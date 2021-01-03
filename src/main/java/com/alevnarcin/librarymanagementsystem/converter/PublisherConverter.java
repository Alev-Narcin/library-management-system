package com.alevnarcin.librarymanagementsystem.converter;

import com.alevnarcin.librarymanagementsystem.dto.PublisherDto;
import com.alevnarcin.librarymanagementsystem.entity.PublisherEntity;
import org.springframework.stereotype.Component;

@Component
public class PublisherConverter {

    public PublisherDto publisherEntityToPublisherDto(PublisherEntity publisherEntity) {
        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setId(publisherEntity.getId());
        publisherDto.setName(publisherEntity.getName());

        return publisherDto;
    }

    public PublisherEntity publisherDtoToPublisherEntity(PublisherDto publisherDto) {
        PublisherEntity publisherEntity = new PublisherEntity();
        publisherEntity.setId(publisherDto.getId());
        publisherEntity.setName(publisherDto.getName());

        return publisherEntity;
    }

}
