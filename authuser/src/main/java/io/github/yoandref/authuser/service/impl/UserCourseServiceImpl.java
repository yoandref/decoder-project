package io.github.yoandref.authuser.service.impl;

import io.github.yoandref.authuser.repositories.UserCourseRepository;
import io.github.yoandref.authuser.service.UserCourseService;
import org.springframework.stereotype.Service;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    private final UserCourseRepository userCourseRepository;

    public UserCourseServiceImpl(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }
}
