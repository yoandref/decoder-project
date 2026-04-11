package io.github.yoandref.course.services.impl;

import io.github.yoandref.course.repositories.ModuleRepository;
import io.github.yoandref.course.services.ModuleService;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    public ModuleServiceImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }
}
