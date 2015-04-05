package com.adserver.datatype;

public class AdDBResponse {

    private String location;
    private String url;
    private int categoryId;
    private int campaignId;

    public AdDBResponse() {}

    public AdDBResponse(String location, String url, int categoryId, int campaignId) {
        super();
        this.location = location;
        this.url = url;
        this.categoryId = categoryId;
        this.campaignId = campaignId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public String toString() {
        return "AdDBResponse [location=" + location + ", url=" + url + ", categoryId=" + categoryId + ", campaignId=" + campaignId + "]";
    }
}
