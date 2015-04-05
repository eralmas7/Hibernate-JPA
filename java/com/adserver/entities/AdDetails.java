package com.adserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

    public AdDetails() {}

    public AdDetails(int adId, String targetUrl, String location) {
        super();
        this.adId = adId;
        this.targetUrl = targetUrl;
        this.location = location;
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

    @Override
    public String toString() {
        return "AdDetails [adId=" + adId + ", targetUrl=" + targetUrl + ", location=" + location + "]";
    }
}
