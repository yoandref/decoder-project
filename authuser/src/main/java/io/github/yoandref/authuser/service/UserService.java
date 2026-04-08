package io.github.yoandref.authuser.service;

import io.github.yoandref.authuser.models.UserModel;
import io.github.yoandref.authuser.specifications.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserModel> findAll();
    Optional<UserModel> findById(UUID userId);
    void deleteById(UUID userId);
    void save(UserModel userModel);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Page<UserModel> findAll(Pageable pageable);

    Page<UserModel> findAll(Pageable pageable, SpecificationTemplate.UserSpec spec);
}
