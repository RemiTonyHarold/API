package com.remirobert;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by remirobert on 05/01/2017.
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findById(String id);
    User findByEmailAndPassword(String email, String password);
}
