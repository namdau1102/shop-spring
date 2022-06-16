package com.project.shop.repositories;

import com.project.shop.models.Accessory;
import com.project.shop.models.Feed;
import com.project.shop.models.Pet;
import com.project.shop.models.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
    @Query("select f from Feed f where " +
            "(" +
            ":phrase is null OR :phrase = '' OR " +
            "lower(f.name) = lower(:phrase)" +
            ") " +
            "AND " +
            "(:minPrice is null OR :minPrice <= f.price) " +
            "AND (:maxPrice is null OR :maxPrice >= f.price)" +
            "AND (f.deleted = false)")
    Page<Feed> findByFilter(@Param("phrase") String phrase,
                            @Param("maxPrice") Float maxPrice,
                            @Param("minPrice") Float minPrice,
                            Pageable pageable);

    Page<Feed> findAllByType(Type type, Pageable pageable);

}
