package io.github.yoandref.course.services.impl;

import io.github.yoandref.course.models.CourseModel;
import io.github.yoandref.course.models.LessonModel;
import io.github.yoandref.course.models.ModuleModel;
import io.github.yoandref.course.repositories.CourseRepository;
import io.github.yoandref.course.repositories.LessonRepository;
import io.github.yoandref.course.repositories.ModuleRepository;
import io.github.yoandref.course.services.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    public CourseServiceImpl(CourseRepository courseRepository, ModuleRepository moduleRepository, LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.moduleRepository = moduleRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    @Transactional
    public void delete(CourseModel courseModel) {
        List<ModuleModel> moduleModelList = moduleRepository.findAllModulesIntoCourse(courseModel.getCourseId());
        if (!moduleModelList.isEmpty()) {
            for (ModuleModel module : moduleModelList) {
                List<LessonModel> lessonsIntoModule = lessonRepository.findAllLessonsIntoModule(module.getModuleId());
                if (!lessonsIntoModule.isEmpty()) {
                    lessonRepository.deleteAll(lessonsIntoModule);
                }
            }
            moduleRepository.deleteAll(moduleModelList);
        }
        courseRepository.delete(courseModel);
    }

    @Override
    public CourseModel save(CourseModel courseModel) {
        return courseRepository.save(courseModel);
    }

    @Override
    public Optional<CourseModel> findById(UUID courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public List<CourseModel> findAll() {
        return courseRepository.findAll();
    }
}
