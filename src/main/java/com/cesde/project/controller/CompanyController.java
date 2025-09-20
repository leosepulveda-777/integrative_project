package com.cesde.project.controller;

import com.cesde.project.dto.CompanyDto;
import com.cesde.project.dto.UserDTO;
import com.cesde.project.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @Operation(summary = "Get all companies", description = "Retrieve a list of all companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.createCompany(companyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyDto companyDto) {
        return ResponseEntity.ok(companyService.updateCompany(id, companyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    // --- USERS by Company ---

    @GetMapping("/{companyId}/users")
    public ResponseEntity<List<UserDTO>> getUsersByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.getUsersByCompany(companyId));
    }

    @PostMapping("/{companyId}/users/{userId}")
    public ResponseEntity<Void> assignUserToCompany(@PathVariable Long companyId, @PathVariable Long userId) {
        companyService.assignUserToCompany(companyId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{companyId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromCompany(@PathVariable Long companyId, @PathVariable Long userId) {
        companyService.removeUserFromCompany(companyId, userId);
        return ResponseEntity.noContent().build();
    }
}