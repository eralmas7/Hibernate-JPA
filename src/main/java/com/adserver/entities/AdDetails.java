package com.adserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id", nullable = false, referencedColumnName = "cust_id", insertable = false, updatable = false)
    private AdCustomer adCustomer = new AdCustomer();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false, referencedColumnName = "campaign_id", insertable = false, updatable = false)
    private AdCampaign adCampaign = new AdCampaign();
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_space_id", nullable = false, referencedColumnName = "ad_space_id", insertable = false, updatable = false)
    private AdSpaceDetails adSpace = new AdSpaceDetails();

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

    public AdSpaceDetails getAdSpace() {
        return adSpace;
    }

    public void setAdSpace(AdSpaceDetails adSpace) {
        this.adSpace = adSpace;
    }

    @Override
    public String toString() {
        return "AdDetails [adId=" + adId + ", targetUrl=" + targetUrl + ", location=" + location + ", title=" + title + "]";
    }
}
