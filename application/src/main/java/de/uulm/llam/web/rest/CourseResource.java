package de.uulm.llam.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.uulm.llam.domain.Course;
import de.uulm.llam.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Course.
 */
@RestController
@RequestMapping("/api")
public class CourseResource {

    private final Logger log = LoggerFactory.getLogger(CourseResource.class);

    @Inject
    private CourseRepository courseRepository;

    /**
     * POST  /courses -> Create a new course.
     */
    @RequestMapping(value = "/courses",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Course course) {
        log.debug("REST request to save Course : {}", course);
        courseRepository.save(course);
    }

    /**
     * GET  /courses -> get all the courses.
     */
    @RequestMapping(value = "/courses",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Course> getAll() {
        log.debug("REST request to get all Courses");
        return courseRepository.findAll();
    }

    /**
     * GET  /courses/:id -> get the "id" course.
     */
    @RequestMapping(value = "/courses/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Course> get(@PathVariable Long id) {
        log.debug("REST request to get Course : {}", id);
        return Optional.ofNullable(courseRepository.findOneWithEagerRelationships(id))
            .map(course -> new ResponseEntity<>(
                course,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /courses/:id -> delete the "id" course.
     */
    @RequestMapping(value = "/courses/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Course : {}", id);
        courseRepository.delete(id);
    }
}
