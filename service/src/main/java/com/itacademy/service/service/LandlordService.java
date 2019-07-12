package com.itacademy.service.service;

import com.itacademy.database.dto.CreateLandlordDto;
import com.itacademy.database.dto.ViewLandlordDto;
import com.itacademy.database.entity.Landlord;
import com.itacademy.database.repository.LandlordRepository;
import com.itacademy.service.mapper.LandlordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LandlordService {

    private final LandlordRepository landlordRepository;
    private final LandlordMapper landlordMapper;
    private final PasswordEncoder passwordEncoder;

    public ViewLandlordDto findById(Long landlordId) {
        ViewLandlordDto landlordDto = null;

        Optional<Landlord> landlord = landlordRepository.findById(landlordId);
        if (landlord.isPresent()) {
            landlordDto = landlordMapper.landlordTolandlordDto(landlord.get());
        }

        return landlordDto;
    }

    public ViewLandlordDto findByEmail(String email) {
        ViewLandlordDto landlordDto = null;

        Optional<Landlord> landlord = landlordRepository.findByEmail(email);
        if (landlord.isPresent()) {
            landlordDto = landlordMapper.landlordTolandlordDto(landlord.get());
        }

        return landlordDto;
    }

    public ViewLandlordDto save(CreateLandlordDto landlordDto) {

        landlordDto.setPassword(passwordEncoder.encode(landlordDto.getPassword()));

        Landlord landlord = landlordRepository.save(landlordMapper.createLandlordDtoToLandlord(landlordDto));

        return landlordMapper.landlordTolandlordDto(landlord);
    }
}
