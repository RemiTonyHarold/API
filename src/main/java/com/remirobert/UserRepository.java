package com.remirobert;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by remirobert on 05/01/2017.
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findByTokenId(String id);
}
