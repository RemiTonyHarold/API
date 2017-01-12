package com.remirobert;

import com.apple.eawt.AppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBContext;
import java.util.List;

/**
 * Created by remirobert on 12/01/2017.
 */

@RestController
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;

    @RequestMapping(value = "/feedSource/{category}", method = RequestMethod.GET)
    public List<FeedSource>getFeedSource(@PathVariable("category") String category) {
        return feedRepository.findByCategoryId(category);
    }
}
