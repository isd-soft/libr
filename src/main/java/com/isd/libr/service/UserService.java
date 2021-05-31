package com.isd.libr.service;

import com.isd.libr.web.dto.UserDto;
import com.isd.libr.web.dto.requests.RegisterRequest;
import com.isd.libr.web.dto.requests.UpdatePasswordRequest;
import com.isd.libr.web.dto.requests.UpdateUserRequest;
import com.isd.libr.web.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * <p>UserService is an interface for User entity. Please see {@link com.isd.libr.web.entity.User} </p>
 *
 * <p>It implements {@link UserDetailsService} for authentication purposes.</p>
 *
 * <p>Preferred implementation is {@code UserServiceImpl}</p>
 *
 * @author Grosu Kirill
 * @see UserDetailsService
 */
public interface UserService extends UserDetailsService {
    /**
     * <p>Returns all users regardless his role. It converts Entity class {@link User} into Data Transfer Object {@link UserDto}</p>
     *
     * @return List of all instances of {@link User} in database converted into {@link UserDto}.
     * @since 1.0
     */
    List<UserDto> findAll();

    /**
     * <p>Registers {@link User} and inserts provided data to database</p>
     *
     * <p>Also hashes the given password using PasswordEncoder</p>
     *
     * @param request object of type {@link RegisterRequest} containing fields: <ul>
     *                <li>Email</li>
     *                <li>First Name</li>
     *                <li>Last Name</li>
     *                <li>Phone</li>
     *                <li>Age</li>
     *                <li>Password</li>
     *                <li>Role</li>
     *                </ul>
     * @return Registered {@link User}
     * @see org.springframework.security.crypto.password.PasswordEncoder
     * @since 1.0
     */
    User create(RegisterRequest request);

    /**
     * Updates the password of {@link User} by providing it's id
     *
     * @param id      unique identifier for {@link User}
     * @param request object of type {@link UpdatePasswordRequest} containing field: <ul>
     *                <li>New Password</li>
     *                </ul
     * @throws SamePasswordException in case of matching old and new passwords.
     * @since 1.0
     */
    void updatePassword(long id, UpdatePasswordRequest request) throws SamePasswordException;

    /**
     * Returns a {@link Optional} of class User if this is present in database, in other case returns empty value.
     *
     * @param id unique identifier for {@link User} that will be used to find specific User instance.
     * @return a {@link Optional} of class User if this is present in database, in other case returns empty value.
     * @see Optional
     * @since 1.0
     */
    Optional<User> findById(long id);

    /**
     * Deletes an instance of User in database if present by providing it's ID.
     *
     * @param id unique identifier for {@link User} that will be used to delete a specific User instance.
     * @since 1.0
     */
    void deleteUserById(long id);

    /**
     * Updates a specific {@link User} instance by providing it's ID.
     *
     * @param id      unique identifier for {@link User} that will be used to find specific User instance.
     * @param request object of type {@link UpdateUserRequest} containing all fields of a User besides password: <ul>
     *                <li>Email</li>
     *                <li>First Name</li>
     *                <li>Last Name</li>
     *                <li>Phone</li>
     *                <li>Age</li>
     *                <li>Role</li>
     *                </ul>
     * @since 1.0
     */
    void updateUser(long id, UpdateUserRequest request);

    /**
     * Implementation for {@link UserDetailsService}
     *
     * @param email unique credential used to log in.
     * @return stored information about {@link User} by providing it's email.
     * @throws UsernameNotFoundException in case when there is no User with such email
     * @see UserDetailsService
     * @since 1.0
     */
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    /**
     * Counts all users presents in database.
     *
     * @return number of all users.
     * @since 1.0
     */
    Integer countUsers();
}
