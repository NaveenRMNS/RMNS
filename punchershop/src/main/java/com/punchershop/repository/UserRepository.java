package com.punchershop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.punchershop.model.User;

/**
 * @author Naveen
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmailIgnoreCase(String username);

}
