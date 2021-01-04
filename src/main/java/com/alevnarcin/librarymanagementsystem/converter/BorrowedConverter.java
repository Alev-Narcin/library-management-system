package com.alevnarcin.librarymanagementsystem.converter;

import com.alevnarcin.librarymanagementsystem.dto.BorrowedDto;
import com.alevnarcin.librarymanagementsystem.entity.BorrowedEntity;
import org.springframework.stereotype.Component;

@Component
public class BorrowedConverter {

    public BorrowedDto borrowedEntityToBorrowedDto(BorrowedEntity borrowedEntity) {
        BorrowedDto borrowedDto = new BorrowedDto();
        borrowedDto.setId(borrowedEntity.getId());
        borrowedDto.setBorrowanceDate(borrowedEntity.getBorrowStartDate());
        borrowedDto.setReturnDate(borrowedEntity.getBorrowReturnDate());

        return borrowedDto;
    }

    public BorrowedEntity borrowedDtoToBorrowedEntity(BorrowedDto borrowedDto) {
        BorrowedEntity borrowedEntity = new BorrowedEntity();
        borrowedEntity.setId(borrowedDto.getId());
        borrowedEntity.setBorrowStartDate(borrowedDto.getBorrowanceDate());
        borrowedEntity.setBorrowReturnDate(borrowedDto.getReturnDate());

        return borrowedEntity;


    }


}
