package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by remirobert on 21/01/2017.
 */

@RestController
public class NewsController {

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
        return newsList.size() > 300 ? newsList.subList(0, 300) : newsList;
    }

    private List<NewsCategoryResponse> getNewsForCategories(List<Category> categoryList, User user) {
        List<NewsCategoryResponse> responseList = new ArrayList<>();
        for (Category category : categoryList) {
            List<FeedSource> feedSourceList = feedRepository.findByCategoryIdAndOwnerId(category.getId(), null);

            if (user != null) {
                feedSourceList.addAll(feedRepository.findByCategoryIdAndOwnerId(category.getId(), user.getId()));
            }
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
    public List<NewsCategoryResponse> getNews(HttpServletRequest request,
                                              @RequestParam(value = "source", required = false) String sourceId,
                                              @RequestParam(value = "category", required = false) String categoryId,
                                              @RequestParam(value = "timestamp", required = false, defaultValue = "0") long timestamp) {
        User user = (User)request.getAttribute("user");
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
        return sortNews(getNewsForCategories(getListCategories(categoryId), user), timestamp);
    }
}
