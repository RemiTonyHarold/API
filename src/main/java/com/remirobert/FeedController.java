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

    private News getOrCreateNews(Element element, String sourceId) {
        if (element.getElementsByTagName(News.TITLE_ELEMENT).getLength() > 0) {
            String title = element.getElementsByTagName(News.TITLE_ELEMENT).item(0).getTextContent();
            News news = newsRepository.findBySourceIdAndTitle(sourceId, title);
            if (news != null) {
                return news;
            }
        }
        return new News(element, sourceId);
    }

    @RequestMapping(value = "/news/{source}", method = RequestMethod.GET)
    public List<News> getNews(@PathVariable("source") String sourceId) {
        List<News> newsList = new ArrayList();
        FeedSource source = feedRepository.findById(sourceId);
        if (source == null) {
            return newsList;
        }
        String uriSource = source.getUrl();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(uriSource);
        } catch (Exception e) {
            System.out.println("error parsing uri");
        }

        if (doc != null) {
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    newsList.add(getOrCreateNews((Element)nNode, sourceId));
                }
            }
        }
        newsRepository.save(newsList);
        return newsList;
    }

    @RequestMapping(value = "/feedSource/{category}", method = RequestMethod.GET)
    public List<FeedSource> getFeedSource(@PathVariable("category") String category) {
        return feedRepository.findByCategoryId(category);
    }
}
