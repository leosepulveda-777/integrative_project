package com.cesde.project.dto;

import com.cesde.project.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CompanyDto {


    private Long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    private String phone;

    public CompanyDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name is mandatory") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CompanyDto(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;


    }
}
