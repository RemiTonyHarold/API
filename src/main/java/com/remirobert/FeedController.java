package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by remirobert on 12/01/2017.
 */

@RestController
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value = "/news/{source}", method = RequestMethod.GET)
    public List<News> getNews(@PathVariable("source") String sourceId) {
        FeedSource source = feedRepository.findById(sourceId);
        if (source == null) {
            throw new SourceIdNotFoundException();
        }
        return newsRepository.findBySourceId(sourceId);
    }

    @RequestMapping(value = "/feedSource/{category}", method = RequestMethod.GET)
    public List<FeedSource> getFeedSource(@PathVariable("category") String category) {
        return feedRepository.findByCategoryId(category);
    }
}
