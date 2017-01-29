package com.remirobert;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by remirobert on 13/01/2017.
 */

@Repository
public interface NewsRepository extends MongoRepository<News, String> {
    News findBySourceIdAndTitle(String sourceId, String title);
    List<News> findBySourceId(String sourceId);
}
