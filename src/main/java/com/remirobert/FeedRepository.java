package com.remirobert;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by remirobert on 12/01/2017.
 */

@Repository
public interface FeedRepository extends MongoRepository<FeedSource, String> {
    List<FeedSource> findByCategoryId(String categoryId);
}
