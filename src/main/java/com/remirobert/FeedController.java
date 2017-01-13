package com.remirobert;

import com.apple.eawt.AppEvent;
import jdk.internal.org.xml.sax.SAXException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.security.sasl.SaslException;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by remirobert on 12/01/2017.
 */

@RestController
public class FeedController {

    @Autowired
    private FeedRepository feedRepository;

    @RequestMapping(value = "/news/{source}", method = RequestMethod.GET)
    public List<News>getNews(@PathVariable("source") String sourceId) {
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
        }
        catch (Exception e) {
            System.out.println("error parsing uri");
        }

        if (doc != null) {
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    News news = new News(eElement, sourceId);
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    @RequestMapping(value = "/feedSource/{category}", method = RequestMethod.GET)
    public List<FeedSource>getFeedSource(@PathVariable("category") String category) {
        return feedRepository.findByCategoryId(category);
    }
}
