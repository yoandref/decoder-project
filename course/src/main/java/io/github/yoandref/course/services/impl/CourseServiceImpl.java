package io.github.yoandref.course.services.impl;

import io.github.yoandref.course.repositories.CourseRepository;
import io.github.yoandref.course.services.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


}
