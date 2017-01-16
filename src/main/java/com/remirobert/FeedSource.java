package com.remirobert;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.w3c.dom.*;

import java.util.Date;

/**
 * Created by remirobert on 12/01/2017.
 */
@Document(collection = "feedSource")
public class FeedSource {

    private static final String DESCRIPTION_ELEMENT = "description";
    private static final String LANGUAGE_ELEMENT = "language";
    private static final String PUBLICATION_DATE_ELEMENT = "pubDate";
    private static final String LAST_BUILD_DATE_ELEMENT = "lastBuildDate";
    private static final String TITLE_ELEMENT = "title";
    private static final String MEDIA_ELEMENT = "image";
    private static final String TTL_ELEMENT = "ttl";
    private static final Integer DEFAULT_TTL = 60;

    private String id;
    private String categoryId;
    private String name;
    private String url;
    private String language;
    private String description;
    private String imageUrl;
    private String publicationDate;
    private String lastBuildDate;
    private Integer ttl;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date lastUpdate;

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public FeedSource() {
        ttl = DEFAULT_TTL;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getTtl() {
        return ttl;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void updateInformations(Element element) {
        if (element.getElementsByTagName(TITLE_ELEMENT).getLength() > 0) {
            name = element.getElementsByTagName(TITLE_ELEMENT).item(0).getTextContent();
        }
        if (element.getElementsByTagName(DESCRIPTION_ELEMENT).getLength() > 0) {
            description = element.getElementsByTagName(DESCRIPTION_ELEMENT).item(0).getTextContent();
        }
        if (element.getElementsByTagName(LANGUAGE_ELEMENT).getLength() > 0) {
            language = element.getElementsByTagName(LANGUAGE_ELEMENT).item(0).getTextContent();
        }
        if (element.getElementsByTagName(PUBLICATION_DATE_ELEMENT).getLength() > 0) {
            publicationDate = element.getElementsByTagName(PUBLICATION_DATE_ELEMENT).item(0).getTextContent();
        }
        if (element.getElementsByTagName(LAST_BUILD_DATE_ELEMENT).getLength() > 0) {
            lastBuildDate = element.getElementsByTagName(LAST_BUILD_DATE_ELEMENT).item(0).getTextContent();
        }
        if (element.getElementsByTagName(TTL_ELEMENT).getLength() > 0) {
            String ttlValue = element.getElementsByTagName(TTL_ELEMENT).item(0).getTextContent();
            ttl = Integer.parseInt(ttlValue);
        }
        if (element.getElementsByTagName(MEDIA_ELEMENT).getLength() > 0) {
            Node nodeImage = element.getElementsByTagName(MEDIA_ELEMENT).item(0);
            if (nodeImage.getNodeType() == Node.ELEMENT_NODE) {
                NodeList nodeListImage = ((Element)nodeImage).getElementsByTagName("url");
                if (nodeListImage != null && nodeListImage.getLength() > 0) {
                    imageUrl = nodeListImage.item(0).getTextContent();
                }
            }
        }
    }
}
