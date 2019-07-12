package com.itacademy.service.service;

import com.itacademy.database.dto.ViewManagerDto;
import com.itacademy.database.entity.Manager;
import com.itacademy.database.repository.ManagerRepository;
import com.itacademy.service.mapper.ManagerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public ViewManagerDto findById(Long managerId) {
        ViewManagerDto managerDto = null;

        Optional<Manager> manager = managerRepository.findById(managerId);
        if (manager.isPresent()) {
            managerDto = managerMapper.managerToManagerDto(manager.get());
        }

        return managerDto;
    }

    public ViewManagerDto findByEmail(String email) {
        ViewManagerDto managerDto = null;

        Optional<Manager> manager = managerRepository.findByEmail(email);
        if (manager.isPresent()) {
            managerDto = managerMapper.managerToManagerDto(manager.get());
        }

        return managerDto;
    }
}
