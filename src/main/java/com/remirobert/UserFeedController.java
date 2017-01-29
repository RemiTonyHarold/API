package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by remirobert on 21/01/2017.
 */

@RestController
public class UserFeedController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ScheduledTasks scheduledTasks;

    @AuthorizationRequired
    @RequestMapping(value = "/userFeedSource", method = RequestMethod.GET)
    public List<FeedSource> getUserSource(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return feedRepository.findByOwnerId(user.getId());
    }

    @AuthorizationRequired
    @RequestMapping(value = "/userFeedSource/{source}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void deleteUserSource(HttpServletRequest request,
                          @PathVariable("source") String sourceId) {
        User user = (User) request.getAttribute("user");
        FeedSource source = feedRepository.findByIdAndOwnerId(sourceId, user.getId());
        if (source == null) {
            throw new SourceIdNotFoundException();
        }
        List<News> newsList = newsRepository.findBySourceId(sourceId);
        newsRepository.delete(newsList);
        feedRepository.delete(source);
    }

    @AuthorizationRequired
    @RequestMapping(value = "/userFeedSource/{category}", method = RequestMethod.POST)
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
