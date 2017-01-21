package com.remirobert;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by remirobert on 12/01/2017.
 */

@RestController
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ScheduledTasks scheduledTasks;

    @RequestMapping(value = "/feedSource/{category}", method = RequestMethod.GET)
    public List<FeedSource> getFeedSource(HttpServletRequest request,
                                          @PathVariable("category") String category) {
        User user = (User)request.getAttribute("user");
        return feedRepository.findByCategoryIdAndOwnerId(category, user != null ? user.getId() : null);
    }

    @AuthorizationRequired
    @RequestMapping(value = "/feedSource/{category}", method = RequestMethod.POST)
    public FeedSource postSource(HttpServletRequest request,
                                 @PathVariable("category") String categoryId,
                                 @RequestParam(value = "url", required = false) String urlSource) {
        Category category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException();
        }
        FeedSource feedSource = feedRepository.findByCategoryIdAndUrl(categoryId, urlSource);
        if (feedSource != null) {
            return feedSource;
        }
        User user = (User)request.getAttribute("user");

        FeedSource newSource = new FeedSource();
        newSource.setUrl(urlSource);
        newSource.setOwnerId(user.getId());
        newSource.setCategoryId(categoryId);
        feedRepository.save(newSource);
        newSource = scheduledTasks.fetchNewsFromSource(newSource);
        return newSource;
    }
}
