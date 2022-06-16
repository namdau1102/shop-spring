package com.project.shop.services.declarations;

import com.project.shop.models.Address;
import com.project.shop.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(User user);
    
    void update(User user);
    
    void updateUserByEmail(String email,String isEmail);
    
    void deleteUser(User user);

    boolean isLoginAvailable(String username);

    boolean isPasswordsEqual(String password, String passwordConfirm);

    Page<User> findAllUsers(Pageable pageable);
    
    User findUserByEmail(String email);
    
    void editUserLock(User user);

    User find(Long id);
}
