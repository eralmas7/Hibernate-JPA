package com.adserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AD_DETAILS")
public class AdDetails {

    @Id
    @Column(name = "ad_id")
    private int adId;
    @Column(name = "target_url")
    private String targetUrl;
    @Column(name = "image_location")
    private String location;
    @Column(name = "ad_title")
    private String title;
    @Column(name = "ad_height")
    private int height;
    @Column(name = "ad_width")
    private int width;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id", nullable = false, referencedColumnName = "ad_id", insertable = false, updatable = false)
    private AdCustomer adCustomer = new AdCustomer();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false, referencedColumnName = "ad_id", insertable = false, updatable = false)
    private AdCampaign adCampaign = new AdCampaign();

    public AdDetails() {}

    public AdDetails(int adId, String targetUrl, String location, String title) {
        super();
        this.adId = adId;
        this.targetUrl = targetUrl;
        this.location = location;
        this.title = title;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public AdCustomer getAdCustomer() {
        return adCustomer;
    }

    public void setAdCustomer(AdCustomer adCustomer) {
        this.adCustomer = adCustomer;
    }

    public AdCampaign getAdCampaign() {
        return adCampaign;
    }

    public void setAdCampaign(AdCampaign adCampaign) {
        this.adCampaign = adCampaign;
    }

    @Override
    public String toString() {
        return "AdDetails [adId=" + adId + ", targetUrl=" + targetUrl + ", location=" + location + ", title=" + title + "]";
    }
}
