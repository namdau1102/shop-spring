package com.project.shop.repositories;

import com.project.shop.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    User findUserByEmail(String email);
    
    User findUserByUsername(String username);

    @Modifying(flushAutomatically  = true)
    @Query(value="update users set email= '?1' where email= '?2'",nativeQuery =true)
    @Transactional
    void updateUserByEmail(@Param("email") String email,@Param("isEmail") String isEmail);
    
    Page<User> findAll(Pageable pageable);
}
