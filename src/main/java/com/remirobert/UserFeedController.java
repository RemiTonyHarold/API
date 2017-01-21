package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by remirobert on 21/01/2017.
 */

@RestController
public class UserFeedController {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private NewsRepository newsRepository;

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
}
