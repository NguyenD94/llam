package de.uulm.llam.repository;

import de.uulm.llam.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Course entity.
 */
public interface CourseRepository extends JpaRepository<Course,Long>{

    @Query("select course from Course course left join fetch course.users where course.id =:id")
    Course findOneWithEagerRelationships(@Param("id") Long id);

}
