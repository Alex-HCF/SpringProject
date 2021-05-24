package org.example.repository;

import org.example.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value =
            "SELECT id, name, parent_id FROM category " +
            "WHERE parent_id IS NULL ",
            nativeQuery = true)
    List<Category> findHeadCategories();


    @Query(value =
            "WITH RECURSIVE r AS (" +
            "SELECT id, name, parent_id, 1 AS level from category " +
            "WHERE id = ?1 " +
            "UNION " +
            "SELECT c.id, c.name, c.parent_id, r.level + 1 as level from category AS c, r " +
            "WHERE c.id = r.parent_id " +
            ") " +
            "SELECT id, name, parent_id from r " +
            "ORDER BY level DESC ", nativeQuery = true)
    List<Category> findPathTo(Long id);


    List<Category> findCategoriesByParentId(Long parentId);

    Optional<Category> findCategoryByNameAndParentId(String name, Long parentId);

    boolean existsById(Long id);

    boolean existsByParentIdAndName(Long parentId, String name);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value =
            "WITH RECURSIVE r AS(" +
            "SELECT id, parent_id, 1 AS level from category " +
            "WHERE id = ?1 " +
            "UNION " +
            "SELECT category.id, category.parent_id, r.level + 1 AS level FROM category JOIN r ON category.parent_id = r.id" +
             ") " +
            "DELETE FROM category " +
            "WHERE id IN (SELECT id FROM r)" , nativeQuery = true)
    void deleteRecursive(Long id);



}
