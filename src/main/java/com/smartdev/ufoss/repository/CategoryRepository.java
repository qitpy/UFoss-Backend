package com.smartdev.ufoss.repository;

import com.smartdev.ufoss.dto.CategoryDTO;
import com.smartdev.ufoss.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);

    @Query(value = "select * from category where parent_id is null" , nativeQuery = true)
    List<CategoryEntity> getMenu();

    @Modifying
    @Transactional
    @Query(value = "update category set name = ?1, parent_id = ?2 where id = ?3", nativeQuery = true)
    void updateParentID(String name, Long parentID, Long Id);

    @Modifying
    @Transactional
    @Query(value = "insert into category (name,parent_id) values (?1, ?2)", nativeQuery = true)
    void newCategory(String name, Long id);

    @Query(value = "select * from category where name = ?1" , nativeQuery = true)
    List<CategoryEntity> getSubCategory(String category);
}
