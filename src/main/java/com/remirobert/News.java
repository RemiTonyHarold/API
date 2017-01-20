package com.remirobert;

import org.springframework.data.mongodb.core.mapping.Document;
import org.w3c.dom.*;
import sun.security.pkcs11.wrapper.CK_CREATEMUTEX;

import javax.annotation.processing.SupportedSourceVersion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.jar.Attributes;

/**
 * Created by remirobert on 13/01/2017.
 */

@Document(collection = "news")
public class News {

    public static final String TITLE_ELEMENT = "title";
    private static final String DESCRIPTION_ELEMENT = "description";
    private static final String LINK_ELEMENT = "link";
    private static final String PUB_DATE_ELEMENT = "pubDate";
    private static final String GUID_ELEMENT = "guid";
    private static final String CREATOR_ELEMENT = "dc:creator";
    private static final String MEDIA_ELEMENT = "media:thumbnail";

    private String id;
    private String sourceId;
    private String title;
    private Date pubDate;
    private String description;
    private String link;
    private String guid;
    private String creator;
    private Date dateCreation;
    private String thumbnail;

    private void parseThumnail(Element eElement) {
        if (eElement.getElementsByTagName(MEDIA_ELEMENT).getLength() > 0) {
            NamedNodeMap nodeImage = eElement.getElementsByTagName(MEDIA_ELEMENT).item(0).getAttributes();
            for (int temp = 0; temp < nodeImage.getLength(); temp++) {
                Node nNode = nodeImage.item(temp);
                if (nNode.getNodeType() == Node.ATTRIBUTE_NODE) {
                    Attr e = (Attr) nNode;
                    if (e.getName() == "url") {
                        thumbnail = e.getValue();
                        return;
                    }
                }
            }
        }
    }

    public News() {}

    public News(Element eElement, String sourceId) throws InterruptedException {
        id = UUID.randomUUID().toString();
        this.sourceId= sourceId;
        dateCreation = new Date();
        if (eElement.getElementsByTagName(TITLE_ELEMENT).getLength() > 0) {
            title = eElement.getElementsByTagName(TITLE_ELEMENT).item(0).getTextContent();
        }
        if (eElement.getElementsByTagName(DESCRIPTION_ELEMENT).getLength() > 0) {
            description = eElement.getElementsByTagName(DESCRIPTION_ELEMENT).item(0).getTextContent();
        }
        if (eElement.getElementsByTagName(LINK_ELEMENT).getLength() > 0) {
            link = eElement.getElementsByTagName(LINK_ELEMENT).item(0).getTextContent();
        }
        if (eElement.getElementsByTagName(PUB_DATE_ELEMENT).getLength() > 0) {
            String dateString = eElement.getElementsByTagName(PUB_DATE_ELEMENT).item(0).getTextContent();
            if (dateString != null) {
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
                try {
                    pubDate = formatter.parse(dateString);
                } catch (ParseException e) {
                    throw new InterruptedException();
                }
            }
        }
        if (eElement.getElementsByTagName(GUID_ELEMENT).getLength() > 0) {
            guid = eElement.getElementsByTagName(GUID_ELEMENT).item(0).getTextContent();
        }
        if (eElement.getElementsByTagName(CREATOR_ELEMENT).getLength() > 0) {
            creator = eElement.getElementsByTagName(CREATOR_ELEMENT).item(0).getTextContent();
        }
        parseThumnail(eElement);
        if (title == null || pubDate == null) {
            throw new InterruptedException();
        }
    }

    public String getId() {
        return id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getTitle() {
        return title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getGuid() {
        return guid;
    }

    public String getCreator() {
        return creator;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
