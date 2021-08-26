package com.evolunta.api.course

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CourseController {

    @GetMapping("/public/courses")
    fun showAllCourses(): String {
        // todo zavanton - return all courses
        return "all courses - public"
    }

    @GetMapping("/protected/courses")
    fun showAllCoursesProtected(): String {
        // todo zavanton - return all courses
        return "all courses - protected"
    }
}
