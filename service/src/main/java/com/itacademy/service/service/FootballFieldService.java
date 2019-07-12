package com.itacademy.service.service;

import com.itacademy.database.dto.CreateFootballFieldDto;
import com.itacademy.database.dto.ViewFootballFieldDto;
import com.itacademy.database.entity.FootballField;
import com.itacademy.database.repository.FootballFieldRepository;
import com.itacademy.service.mapper.FootballFieldMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FootballFieldService {

    private final FootballFieldRepository footballFieldRepository;
    private final FootballFieldMapper fieldMapper;

    public ViewFootballFieldDto findById(Long fieldId) {
        ViewFootballFieldDto fieldDto = null;

        Optional<FootballField> field = footballFieldRepository.findById(fieldId);
        if (field.isPresent()) {
            fieldDto = fieldMapper.fieldToFieldDto(field.get());
        }

        return fieldDto;
    }

    public ViewFootballFieldDto save(CreateFootballFieldDto fieldDto) {

        FootballField field = footballFieldRepository.save(fieldMapper.createFieldDtoToField(fieldDto));

        return fieldMapper.fieldToFieldDto(field);
    }
}
