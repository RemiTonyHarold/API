package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private List<NewsCategoryResponse> sortNews(List<NewsCategoryResponse> newsList, long timestamp) {
        if (newsList.isEmpty()) {
            return newsList;
        }
        newsList = newsList.stream().filter(n -> n.getDate().getTime() >= timestamp).collect(Collectors.toList());
        Collections.sort(newsList, Comparator.comparing(NewsCategoryResponse::getDate));
        return newsList;
    }

    private List<NewsCategoryResponse> getNewsForCategories(List<Category> categoryList) {
        List<NewsCategoryResponse> responseList = new ArrayList<>();
        for (Category category : categoryList) {
            List<FeedSource> feedSourceList = feedRepository.findByCategoryId(category.getId());
            for (FeedSource source : feedSourceList) {
                List<News> newsList = newsRepository.findBySourceId(source.getId());
                for (News news : newsList) {
                    responseList.add(new NewsCategoryResponse(category, news));
                }
            }
        }
        return responseList;
    }

    private List<Category> getListCategories(String categoryId) {
        List<Category> categoryList = new ArrayList<>();
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId);
            categoryList.add(category);
        } else {
            categoryList.addAll(categoryRepository.findAll());
        }
        return categoryList;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public List<NewsCategoryResponse> getNews(@RequestParam(value = "source", required = false) String sourceId,
                                              @RequestParam(value = "category", required = false) String categoryId,
                                              @RequestParam(value = "timestamp", required = false, defaultValue = "0") long timestamp) {
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
            List<News> newsList = newsRepository.findBySourceId(sourceId);
            for (News news : newsList) {
                responseList.add(new NewsCategoryResponse(category, news));
            }
            return sortNews(responseList, timestamp);
        }
        return sortNews(getNewsForCategories(getListCategories(categoryId)), timestamp);
    }

    @RequestMapping(value = "/feedSource/{category}", method = RequestMethod.GET)
    public List<FeedSource> getFeedSource(@PathVariable("category") String category) {
        return feedRepository.findByCategoryId(category);
    }
}
