package io.github.yoandref.course.repositories;

import io.github.yoandref.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {
}
