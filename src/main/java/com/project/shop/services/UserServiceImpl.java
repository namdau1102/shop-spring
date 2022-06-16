package com.project.shop.services;

import com.project.shop.exceptions.UserAlreadyExistsException;
import com.project.shop.models.Address;
import com.project.shop.models.Role;
import com.project.shop.models.User;
import com.project.shop.repositories.RoleRepository;
import com.project.shop.repositories.UserRepository;
import com.project.shop.services.declarations.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(s);

		if (user == null)
			throw new UsernameNotFoundException(s);

		return user;
	}

	/*
	 * private UserDetails convertToUserDetails ( User user ) {
	 * Set<GrantedAuthority> grantedAuthorities = user.getRoles() .stream() .map(
	 * role -> new SimpleGrantedAuthority( role.getType().toString() ) ) .collect(
	 * Collectors.toSet() );
	 * 
	 * return new org.springframework.security.core.userdetails.User(
	 * user.getLogin(), user.getPassword(), grantedAuthorities ); }
	 */

	@Override
	public void save(User user) {
		user.setRoles(new HashSet<>(Arrays.asList(roleRepository.findRoleByUserType(Role.UserTypes.ROLE_USER))));
		user.setEnabled(true);
		user.setPasswordConfirm(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.findById(user.getId()).ifPresent(userToDelete -> userToDelete.setLocked(Boolean.TRUE));
	}

	@Override
	public boolean isLoginAvailable(String password) {
		return !userRepository.existsUserByUsername(password);
	}

	@Override
	public boolean isPasswordsEqual(String password, String passwordConfirm) {
		return StringUtils.equals(password, passwordConfirm);
	}

	@Override
	public Page<User> findAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public void editUserLock(User user) {
		user.setLocked(!user.getLocked());
		userRepository.save(user);
	}

	@Override
	public User find(Long id) {
		Optional<User> a = userRepository.findById(id);
		return a.orElse(null);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findUserByEmail(email);

		return user;
	}

	@Override
	public void update(User user) {
		save(user);
	}

	@Override
	public void updateUserByEmail(String email, String isEmail) {
		System.out.println("Email Update "+ email);
		System.out.println("Email isEmail "+ isEmail);

		userRepository.updateUserByEmail(email, isEmail);

	}
}
