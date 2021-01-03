package com.alevnarcin.librarymanagementsystem.converter;

import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import org.springframework.stereotype.Component;

@Component
public class BorrowedConverter {

    public BorrowedDto borrowedEntityToBorrowedDto(BorrowedEntity borrowedEntity) {
        BorrowedDto borrowedDto = new BorrowedDto();
        borrowedDto.setId(borrowedEntity.getId());
        borrowedDto.setBorrowanceDate(borrowedEntity.getBorrowanceDate());
        borrowedDto.setReturnDate(borrowedEntity.getReturnDate());

        return borrowedDto;
    }

    public BorrowedEntity borrowedDtoToBorrowedEntity(BorrowedDto borrowedDto) {
        BorrowedEntity borrowedEntity = new BorrowedEntity();
        borrowedEntity.setId(borrowedDto.getId());
        borrowedEntity.setBorrowanceDate(borrowedDto.getBorrowanceDate());
        borrowedEntity.setReturnDate(borrowedDto.getReturnDate());

        return borrowedEntity;


    }


}
