package com.project.shop.repositories;

import com.project.shop.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TypeRepository extends JpaRepository<Type, Long> {

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE animal_categories set category = ' ?1 ' where id= ?2", nativeQuery = true)
	@Transactional
	void updateTypeById(@Param(value = "category") String category, @Param(value = "id") Long id);

}
