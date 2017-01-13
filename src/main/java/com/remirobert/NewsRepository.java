package com.remirobert;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by remirobert on 13/01/2017.
 */

@Repository
public interface NewsRepository extends MongoRepository<News, String> {
    public News findBySourceIdAndTitle(String sourceId, String title);
}
