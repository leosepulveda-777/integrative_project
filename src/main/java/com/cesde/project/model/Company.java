package com.cesde.project.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Company {
    // ID unico para cada usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental
    private Long id;

    // Nombre de la empresa (obligatorio, no puede estar vacío)
    @NotBlank(message = " name is mandatory")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    // Email de la empresa  (obligatorio, no puede estar vacío, debe ser único y válido)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    // Teléfono de la empresa (opcional)
    @Column(name = "phone", length = 15)
    private String phone;

    // Fecha de creación de la empresa  (no puede ser nulo, no se puede actualizar)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    // Constructor vacío (Requerido por JPA)
    public Company() { }


    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = " name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = " name is mandatory") String name) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Company(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createdAt = LocalDateTime.now();

    }


}
