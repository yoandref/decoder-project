package io.github.yoandref.course.services.impl;

import io.github.yoandref.course.repositories.LessonRepository;
import io.github.yoandref.course.services.LessonService;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

}
