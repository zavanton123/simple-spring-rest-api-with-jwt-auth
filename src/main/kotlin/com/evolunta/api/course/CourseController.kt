package com.evolunta.api.course

import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CourseController {

    @GetMapping("/public/courses")
    fun showAllCourses(): String {
        return "all courses - public"
    }

    @GetMapping("/protected/courses")
    @Secured("ADMIN")
    fun showAllCoursesProtected(): String {
        return "all courses - protected"
    }
}
