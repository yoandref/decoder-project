package io.github.yoandref.course.services.impl;

import io.github.yoandref.course.repositories.CourseUserRepository;
import io.github.yoandref.course.services.CourseUserService;
import org.springframework.stereotype.Service;

@Service
public class CourseUserServiceImpl implements CourseUserService {

    private final CourseUserRepository courseUserRepository;

    public CourseUserServiceImpl(CourseUserRepository courseUserRepository) {
        this.courseUserRepository = courseUserRepository;
    }
}
