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
import java.util.*;

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
        try {
            return new News(element, sourceId);
        } catch (InterruptedException e) {
            return null;
        }
    }

    private void fetchNewsFromSource(FeedSource source) {
        System.out.println("current source : " + source.getUrl());
        List<News> newsList = new ArrayList();
        String uriSource = source.getUrl();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(uriSource);
        } catch (Exception e) {
            System.out.println("error parsing uri : " + uriSource);
        }

        if (doc != null) {
            doc.getDocumentElement().normalize();
            updateSourceInformations(source, doc);
            NodeList nList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    News createNews = getOrCreateNews((Element) nNode, source.getId());
                    if (createNews != null) {
                        newsList.add(createNews);
                    }

                }
            }
        }
        source.setLastUpdate(new Date());
        feedRepository.save(source);
        newsRepository.save(newsList);
    }

    private void updateSourceInformations(FeedSource source, Document document) {
        Node nodeChannel = document.getElementsByTagName("channel").item(0);
        if (nodeChannel.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) nodeChannel;
            source.updateInformations(element);
            feedRepository.save(source);
        }
    }

    @Scheduled(fixedDelay = DELAY_EXECUTION)
    public void fetchNews() {
        System.out.println("Download news task started");
        for (FeedSource source : feedRepository.findAll()) {
            if (source.getLastUpdate() == null) {
                fetchNewsFromSource(source);
            } else {
                Date lastUpdate = source.getLastUpdate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(lastUpdate);
                cal.add(Calendar.MINUTE, source.getTtl());
                if (new Date().compareTo(cal.getTime()) > 0) {
                    fetchNewsFromSource(source);
                }
            }
        }
        System.out.println("Download news task completed");
    }
}
