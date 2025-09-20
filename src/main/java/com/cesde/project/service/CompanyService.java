package com.cesde.project.service;

import com.cesde.project.dto.CompanyDto;
import com.cesde.project.dto.UserDTO;
import java.util.List;

public interface CompanyService {

    List<CompanyDto> getAllCompanies();

    CompanyDto getCompanyById(Long id);

    CompanyDto createCompany(CompanyDto companyDto);

    CompanyDto updateCompany(Long id, CompanyDto companyDto);

    void deleteCompany(Long id);

    List<UserDTO> getUsersByCompany(Long companyId);

    void assignUserToCompany(Long companyId, Long userId);

    void removeUserFromCompany(Long companyId, Long userId);
}
