package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoursesRepository extends JpaRepository<CourseEntity, UUID> {

    List<CourseEntity> findTop5ByTitleContainingIgnoreCaseOrderByTitle(String title);

    List<CourseEntity> findTop5ByDescriptionContainingIgnoreCaseOrderByTitle(String desc);

    List<CourseEntity> findTop5ByTitleContainingOrDescriptionContainingAllIgnoreCaseOrderByTitle(
            String title, String desc);

    Optional<CourseEntity> findByIDAndCategory(UUID id, CategoryEntity category);

    Optional<CourseEntity> findByTitleAndCategory(String title, CategoryEntity category);

    void deleteByIDAndCategory(UUID id, CategoryEntity category);

    @Query(value =
            "select * " +
                    "from course c " +
                    "full join( " +
                    "select r.course_id as temp_id " +
                    "from rate r " +
                    "group by r.course_id " +
                    ") as temp " +
                    "on temp.temp_id = c.id " +
                    "where category_id = ?1  and c.id not in ( " +
                    "select bill.course_id " +
                    "from payment bill " +
                    "where bill.user_id = ?2 " +
                    ") ",
            nativeQuery = true)
    Page<CourseEntity> findByCategoryWithFilterAndNewestNotRating(Long category, UUID userID, Pageable pageable);

    @Query(value =
            "select * " +
                    "from course c " +
                    "inner join( " +
                    "select r.course_id as temp_id " +
                    "from rate r " +
                    "group by r.course_id " +
                    "having avg(r.score/2) > ?1 " +
                    ") as temp " +
                    "on temp.temp_id = c.id " +
                    "where category_id = ?2 and c.id not in( " +
                    "select bill.course_id " +
                    "from payment bill " +
                    "where bill.user_id = ?3 " +
                    ") ",
            nativeQuery = true)
    Page<CourseEntity> findByCategoryWithFilterAndNewestAndRating(Double rating, Long category, UUID userID, Pageable pageable);

    @Query(value =
            "select * " +
                    "from course c " +
                    "full join ( " +
                    "select  p.course_id as tb1_course_id_FK, count(p.user_id) as sellest " +
                    "from payment p " +
                    "group by p.course_id " +
                    ") as temp1 " +
                    "on temp1.tb1_course_id_FK = c.id " +
                    "full join( " +
                    "select r.course_id as tb2_course_id_FK, avg(r.score/2) as score_rate " +
                    "from rate r " +
                    "group by r.course_id " +
                    ") as temp2 " +
                    "on temp2.tb2_course_id_FK = c.id " +
                    "where c.category_id = ?1 and c.id not in ( " +
                    "select bill.course_id " +
                    "from payment bill " +
                    "where bill.user_id = ?2 " +
                    ") ",
            nativeQuery = true)
    Page<CourseEntity> findByCategoryWithFilterAndSellestNotRating(Long category, UUID userID, Pageable pageable);

    @Query(value =
            "select * " +
                    "from course c " +
                    "full join ( " +
                    "select  p.course_id as tb1_course_id_FK, count(p.user_id) as sellest " +
                    "from payment p " +
                    "group by p.course_id " +
                    ") as temp1 " +
                    "on temp1.tb1_course_id_FK = c.id " +
                    "full join( " +
                    "select r.course_id as tb2_course_id_FK, avg(r.score/2) as score_rate " +
                    "from rate r " +
                    "group by r.course_id " +
                    ") as temp2 " +
                    "on temp2.tb2_course_id_FK = c.id " +
                    "where c.category_id = ?1 and temp2.score_rate >= ?2 and c.id not in (" +
                    "select bill.course_id " +
                    "from payment bill " +
                    "where bill.user_id = ?3 " +
                    ") ",
            nativeQuery = true)
    Page<CourseEntity> findByCategoryWithFilterAndSellestAndRating(Long category, Double rating, UUID userID, Pageable pageable);

    @Query(value =
            "select  * " +
                    "from course c " +
                    "where c.category_id = ?1 >= ?2 and c.id not in ( " +
                    "select bill.course_id " +
                    "from payment bill " +
                    "where bill.user_id = ?3 " +
                    ") ",
            nativeQuery = true)
    Page<CourseEntity> findAllByCategoryInHome(CategoryEntity category, Pageable pageable);
}
