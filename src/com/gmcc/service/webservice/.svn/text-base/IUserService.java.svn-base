package com.gmcc.service.webservice;

import java.util.List;

import javax.jws.WebService;

import com.gmcc.model.User;

import com.ibm.service.UserExistsException;

/**
 * Web Service interface so hierarchy of Universal and Generic Managers isn't carried through.
 */

@WebService
public interface IUserService {
    /**
     * Retrieves a user by userId.  An exception is thrown if user not found
     *
     * @param userId the identifier for the user
     * @return User
     */
    User getUser(String userId);


    /**
     * Retrieves a list of users, filtering with parameters on a user object
     * @param user parameters to filter on
     * @return List
     */
    List<User> getUsers();

    /**
     * Saves a user's information
     *
     * @param user the user's information
     * @throws UserExistsException thrown when user already exists
     * @return updated user
     */
    User saveUser(User user) throws UserExistsException;

    /**
     * Removes a user from the database by their userId
     *
     * @param userId the user's id
     */
    void removeUser(String userId);
}
