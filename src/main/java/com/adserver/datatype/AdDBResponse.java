package com.adserver.datatype;

public class AdDBResponse {

    private String location;
    private String url;
    private int categoryId;
    private int campaignId;
    private String title;

    public AdDBResponse() {}

    public AdDBResponse(final String location, final String url, final int categoryId, final int campaignId, final String title) {
        super();
        this.location = location;
        this.url = url;
        this.categoryId = categoryId;
        this.campaignId = campaignId;
        this.title = title;
    }

    public AdDBResponse(final String location, final String url, final String title) {
        super();
        this.location = location;
        this.url = url;
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(final int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(final int campaignId) {
        this.campaignId = campaignId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "AdDBResponse [location=" + location + ", url=" + url + ", categoryId=" + categoryId + ", campaignId=" + campaignId + ", title=" + title + "]";
    }
}
