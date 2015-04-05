package com.adserver.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AD_MAPPING")
public class AdMapping {

    @Id
    private int id;
    @Column(name = "ad_id")
    private int adId;
    @Column(name = "ad_space_id")
    private int adSpaceId;
    @Column(name = "cust_id")
    private int customerId;
    @Column(name = "campaign_id")
    private int campaignId;
    @Column(name = "category_id")
    private int categoryId;

    public AdMapping() {}

    public AdMapping(int id, int adId, int adSpaceId, int customerId, int campaignId, int categoryId) {
        super();
        this.id = id;
        this.adId = adId;
        this.adSpaceId = adSpaceId;
        this.customerId = customerId;
        this.campaignId = campaignId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getAdSpaceId() {
        return adSpaceId;
    }

    public void setAdSpaceId(int adSpaceId) {
        this.adSpaceId = adSpaceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "AdMapping [id=" + id + ", adId=" + adId + ", adSpaceId=" + adSpaceId + ", customerId=" + customerId + ", campaignId=" + campaignId + ", categoryId=" + categoryId + "]";
    }
}
