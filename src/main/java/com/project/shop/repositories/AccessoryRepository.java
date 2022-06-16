package com.project.shop.repositories;

import com.project.shop.models.Accessory;
import com.project.shop.models.Feed;
import com.project.shop.models.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
    @Query("select a from Accessory a where " +
            "(" +
            ":phrase is null OR :phrase = '' OR " +
            "lower(a.name) = lower(:phrase)" +
            ") " +
            "AND " +
            "(:minPrice is null OR :minPrice <= a.price) " +
            "AND (:maxPrice is null OR :maxPrice >= a.price) " +
            "AND (a.deleted = false)")
    Page<Accessory> findByFilter(@Param("phrase") String phrase,
                                 @Param("maxPrice") Float maxPrice,
                                 @Param("minPrice") Float minPrice,
                                 Pageable pageable);

    Page<Accessory> findAllByType(Type type, Pageable pageable);

}
