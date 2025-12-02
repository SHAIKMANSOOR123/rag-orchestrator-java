package com.mansoor.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository: Spring recognizes this as a data access component.
// JpaRepository<User, Long>: Inherits methods like save(), findAll(), findById(), etc., for the User entity.
@Repository 
public interface UserRepository extends JpaRepository<User, Long> {

    // All necessary CRUD methods are now inherited automatically.
}