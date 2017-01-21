package com.remirobert;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by remirobert on 12/01/2017.
 */

@Repository
public interface FeedRepository extends MongoRepository<FeedSource, String> {
    List<FeedSource> findByCategoryIdAndOwnerId(String categoryId, String ownerId);
    FeedSource findByIdAndOwnerId(String id, String ownerId);
    List<FeedSource> findByOwnerId(String ownerId);
    FeedSource findByCategoryIdAndUrl(String categoryId, String url);
    FeedSource findById(String id);
}
