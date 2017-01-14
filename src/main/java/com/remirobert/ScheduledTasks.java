package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by remirobert on 14/01/2017.
 */

@Component
public class ScheduledTasks {

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private NewsRepository newsRepository;

    private static final long DELAY_EXECUTION = 300000;

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

    private void fetchNewsFromSource(FeedSource source) {
        List<News> newsList = new ArrayList();
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
                    newsList.add(getOrCreateNews((Element)nNode, source.getId()));
                }
            }
        }
        newsRepository.save(newsList);
    }

    @Scheduled(fixedDelay=DELAY_EXECUTION)
    public void fetchNews() {
        System.out.println("Download news task started");
        for (FeedSource source : feedRepository.findAll()) {
            fetchNewsFromSource(source);
        }
        System.out.println("Download news task completed");
    }
}
