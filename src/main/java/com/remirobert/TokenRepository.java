package com.remirobert;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by remirobert on 02/01/2017.
 */

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {
    Token findByToken(String token);
}
