package com.cesde.project.service;

import com.cesde.project.dto.UserDTO;
import com.cesde.project.model.User;
import com.cesde.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * UserService - Contiene la logica de negocio para gestionar usuarios
 *
 * @Service: Indica que esta clase es un servicio de Spring (componente de negocio)
 * Aquí implementamos la logica de negocio, validaciones, reglas, etc.
 */
@Service
public class UserService {
  // Inyección de dependencia - Spring nos da una instancia del repositorio
  @Autowired
  private UserRepository userRepository;

  // ========================================
  // MÉTODOS BÁSICOS CRUD
  // ========================================

  /**
   * Obtenemos todos los usuarios y los convertimos a DTOs (sin la logica de paginacion)
   */
  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();

    // Convertir de User a UserDTO usando Stream API
    return users.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
  }

  /**
   * Obtener todos los usuarios con paginacion y convertirlos a DTOs
   * Gracias a JpaRepository, tenemos paginacion automatica.
   */
  public Page<UserDTO> getAllUsers(Pageable pageable) {
    Page<User> users = userRepository.findAll(pageable);

    // Convertir User a List<UserDTO> usando Stream API
    List<UserDTO> userDTOs = users.getContent().stream().map(UserDTO::fromEntity).collect(Collectors.toList());

    // Convertir List<UserDTO> a Page<UserDTO>
    return new PageImpl<>(userDTOs, pageable, users.getTotalElements());
  }

  /**
   * Obtener un usuario por ID
   */
  public UserDTO getUserById(Long id) {
    Optional<User> user = userRepository.findById(id);

    if (user.isPresent()) {
      return UserDTO.fromEntity(user.get());
    } else {
      throw new RuntimeException("User not found with id: " + id);
    }
  }

  /**
   * Crear un nuevo usuario
   */
  public UserDTO createUser(UserDTO userDTO) {
    // Validar que el email no exista
    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new RuntimeException("Email already in use: " + userDTO.getEmail());
    }

    // Convertir DTO a entidad
    User user = userDTO.toEntity();

    // Guardar la entidad en la base de datos
    User savedUser = userRepository.save(user);

    return UserDTO.fromEntity(savedUser);
  }

  /**
   * Actualizar un usuario existente
   */
  public UserDTO updateUser(Long id, UserDTO userDTO) {
    Optional<User> existingUser = userRepository.findById(id);

    if (existingUser.isEmpty()) {
      throw new RuntimeException("User not found with id: " + id);
    }

    User user = existingUser.get();

    // Verifica si el email ha cambiado y si ya existe en la base de datos
    if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
      throw new RuntimeException("Email already in use: " + userDTO.getEmail());
    }

    user.setFirstName(userDTO.getFirstName());
    user.setLastName(userDTO.getLastName());
    user.setEmail(userDTO.getEmail());
    user.setPhone(userDTO.getPhone());

    User updatedUser = userRepository.save(user);
    return UserDTO.fromEntity(updatedUser);
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found with id: " + id);
    }

    userRepository.deleteById(id);
  }

  // ========================================
  // MÉTODOS DE BÚSQUEDA PERSONALIZADA
  // ========================================
}
