package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface TrainingService {

    User createUser(User user);

    /**
     * Removes a user by their unique identifier.
     * <p>
     * If the user is not found, this method throws a {@link ResponseStatusException}
     * with a 404 Not Found status.
     *
     * @param userId the ID of the user to be removed
     * @throws ResponseStatusException if the user with the specified ID does not exist
     */
    void removeUser(Long userId) throws ResponseStatusException;

    /**
     * Searches for users by a partial email match, ignoring case sensitivity.
     *
     * @param emailFragment the fragment of the email to search for
     * @return a list of matching simple users
     */
    List<User> findUsersByEmailFragment(String emailFragment);
}
