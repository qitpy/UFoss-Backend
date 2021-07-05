package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.entity.CategoryEntity;
import com.smartdev.ufoss.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CoursesRepository extends JpaRepository<CourseEntity, UUID> {

    List<CourseEntity> findByCategory(CategoryEntity category);

    Optional<CourseEntity> findByIDAndCategory(UUID id, CategoryEntity category);

    Optional<CourseEntity> findByTitleAndCategory(String title, CategoryEntity category);

    void deleteByIDAndCategory(UUID id, CategoryEntity category);

    @Modifying
    @Query(value =
            "select * " +
                    "from course c " +
                    "inner join( " +
                    "select r.course_id as temp_id,count(user_id) as total_rate " +
                    "from rate r " +
                    "group by r.course_id " +
                    "having avg(r.score) > ?1 " +
                    ") as temp " +
                    "on temp.temp_id = c.id " +
                    "where category_id = ?2 " +
                    "order by temp.total_rate desc",
            nativeQuery = true
    )
    List<CourseEntity> findByCategoryAndfilterWithTotalRateDesc(Double rate, Long category);

    @Modifying
    @Query(value =
            "select * " +
                    "from course c " +
                    "inner join( " +
                    "select r.course_id as temp_id,count(user_id) as total_rate " +
                    "from rate r " +
                    "group by r.course_id " +
                    "having avg(r.score) > ?1 " +
                    ") as temp " +
                    "on temp.temp_id = c.id " +
                    "where category_id = ?2 " +
                    "order by c.create_at desc ",
            nativeQuery = true
    )
    List<CourseEntity> findByCategoryAndfilterWithCreateAtDesc(Double rate, Long category);
}

