package com.project.shop.repositories;

import com.project.shop.models.Accessory;
import com.project.shop.models.Pet;
import com.project.shop.models.SearchForm;
import com.project.shop.models.Type;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("select p from Pet p where lower(p.name) LIKE %?1%")
    Page<Pet> findByFilter(@Param("phrase") String phrase,
                           Pageable pageable);
    
    @Query("select p from Pet p")
    List<Pet> findAllList();
    Page<Pet> findAllByType(Type type, Pageable pageable);

}
