package org.example.repository;

import org.example.data.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Override
    Optional<Note> findById(Long id);

    boolean existsById(Long id);

    @Query(value = "" +
            "select * from note n " +
            "left join location l on l.id = n.location_id " +
            "left join category c on c.id = n.category_id " +
            "left join person p on n.owner_id = p.id " +
            "where n.price >= :minPrice and n.price <= :maxPrice " +
            "and n.category_id in (:categoryId) " +
            "and l.house_fias_id = :fiasId", nativeQuery = true)
    List<Note> findByPriceAndCategoryAndHouse(@Param("minPrice") Double minPrice,
                                              @Param("maxPrice") Double maxPrice,
                                              @Param("categoryId") List<Long> categoryIds,
                                              @Param("fiasId") UUID fiasId);

    @Query(value = "" +
            "select * from note n " +
            "left join location l on l.id = n.location_id " +
            "left join category c on c.id = n.category_id " +
            "left join person p on n.owner_id = p.id " +
            "where n.price >= :minPrice and n.price <= :maxPrice " +
            "and n.category_id in (:categoryId) " +
            "and l.street_fias_id = :fiasId", nativeQuery = true)
    List<Note> findByPriceAndCategoryAndStreet(@Param("minPrice")Double minPrice,
                                              @Param("maxPrice") Double maxPrice,
                                              @Param("categoryId") List<Long> categoryIds,
                                              @Param("fiasId") UUID fiasId);

    @Query(value = "" +
            "select * from note n " +
            "left join location l on l.id = n.location_id " +
            "left join category c on c.id = n.category_id " +
            "left join person p on n.owner_id = p.id " +
            "where n.price >= :minPrice and n.price <= :maxPrice " +
            "and n.category_id in (:categoryId) " +
            "and l.city_fias_id = :fiasId", nativeQuery = true)
    List<Note> findByPriceAndCategoryAndCity(@Param("minPrice")Double minPrice,
                                              @Param("maxPrice") Double maxPrice,
                                              @Param("categoryId") List<Long> categoryIds,
                                              @Param("fiasId") UUID fiasId);

    @Query(value = "" +
            "select * from note n " +
            "left join location l on l.id = n.location_id " +
            "left join category c on c.id = n.category_id " +
            "left join person p on n.owner_id = p.id " +
            "where n.price >= :minPrice and n.price <= :maxPrice " +
            "and n.category_id in (:categoryId) " +
            "and l.settlement_fias_id = :fiasId", nativeQuery = true)
    List<Note> findByPriceAndCategoryAndSettlement(@Param("minPrice")Double minPrice,
                                             @Param("maxPrice") Double maxPrice,
                                             @Param("categoryId") List<Long> categoryIds,
                                             @Param("fiasId") UUID fiasId);

    @Query(value = "" +
            "select * from note n " +
            "left join location l on l.id = n.location_id " +
            "left join category c on c.id = n.category_id " +
            "left join person p on n.owner_id = p.id " +
            "where n.price >= :minPrice and n.price <= :maxPrice " +
            "and n.category_id in (:categoryId) " +
            "and l.area_fias_id = :fiasId", nativeQuery = true)
    List<Note> findByPriceAndCategoryAndArea(@Param("minPrice")Double minPrice,
                                             @Param("maxPrice") Double maxPrice,
                                             @Param("categoryId") List<Long> categoryIds,
                                             @Param("fiasId") UUID fiasId);


    @Query(value = "" +
            "select * from note n " +
            "left join location l on l.id = n.location_id " +
            "left join category c on c.id = n.category_id " +
            "left join person p on n.owner_id = p.id " +
            "where n.price >= :minPrice and n.price <= :maxPrice " +
            "and n.category_id in (:categoryId) " +
            "and l.region_fias_id = :fiasId", nativeQuery = true)
    List<Note> findByPriceAndCategoryAndRegion(@Param("minPrice")Double minPrice,
                                             @Param("maxPrice") Double maxPrice,
                                             @Param("categoryId") List<Long> categoryIds,
                                             @Param("fiasId") UUID fias);


}
