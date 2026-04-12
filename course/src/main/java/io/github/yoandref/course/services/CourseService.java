package io.github.yoandref.course.services;

import io.github.yoandref.course.models.CourseModel;

public interface CourseService {

    void delete(CourseModel courseModel);
    void save(CourseModel courseModel);

}
