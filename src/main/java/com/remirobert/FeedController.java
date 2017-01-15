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

    @Autowired
    private CategoryRepository categoryRepository;

    private List<NewsCategoryResponse> getNewsForCategories(List<Category> categoryList) {
        List<NewsCategoryResponse> responseList = new ArrayList<>();
        for (Category category : categoryList) {
            List<News> newsList = new ArrayList<>();
            List<FeedSource> feedSourceList = feedRepository.findByCategoryId(category.getId());
            for (FeedSource source : feedSourceList) {
                newsList.addAll(newsRepository.findBySourceId(source.getId()));
            }
            responseList.add(new NewsCategoryResponse(category, newsList));
        }
        return responseList;
    }

    private List<Category> getListCategories(String categoryId) {
        List<Category> categoryList = new ArrayList<>();
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId);
            categoryList.add(category);
        }
        else {
            categoryList.addAll(categoryRepository.findAll());
        }
        return categoryList;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List<NewsCategoryResponse> getNews(@RequestParam(value = "source", required = false) String sourceId,
                              @RequestParam(value = "category", required = false) String categoryId) {
        if (sourceId != null) {
            FeedSource source = feedRepository.findById(sourceId);
            if (source == null) {
                throw new SourceIdNotFoundException();
            }
            Category category = categoryRepository.findById(source.getCategoryId());
            if (category == null) {
                throw new SourceIdNotFoundException();
            }
            List<NewsCategoryResponse> responseList = new ArrayList<>();
            NewsCategoryResponse response = new NewsCategoryResponse(category, newsRepository.findBySourceId(sourceId));
            responseList.add(response);
            return responseList;
        }
        return getNewsForCategories(getListCategories(categoryId));
    }

    @RequestMapping(value = "/feedSource/{category}", method = RequestMethod.GET)
    public List<FeedSource> getFeedSource(@PathVariable("category") String category) {
        return feedRepository.findByCategoryId(category);
    }
}
