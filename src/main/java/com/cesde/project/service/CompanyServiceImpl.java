package com.cesde.project.service;

import com.cesde.project.dto.CompanyDto;
import com.cesde.project.dto.UserDTO;
import com.cesde.project.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Override
    public List<CompanyDto> getAllCompanies() {
        return new ArrayList<>();
    }

    @Override
    public CompanyDto getCompanyById(Long companyId) {
        return new CompanyDto();
    }

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        return companyDto;
    }

    @Override
    public CompanyDto updateCompany(Long companyId, CompanyDto companyDto) {
        return companyDto;
    }

    @Override
    public void deleteCompany(Long companyId) {
        // eliminar lógica
    }

    @Override
    public List<UserDTO> getUsersByCompany(Long companyId) {
        return new ArrayList<>();
    }

    @Override
    public void assignUserToCompany(Long companyId, Long userId) {
        // asignar lógica
    }

    @Override
    public void removeUserFromCompany(Long companyId, Long userId) {
        // quitar lógica
    }
}
