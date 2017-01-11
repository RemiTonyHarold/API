package com.remirobert;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by remirobert on 11/01/2017.
 */

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
