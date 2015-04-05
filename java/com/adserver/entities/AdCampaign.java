package com.adserver.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AD_CAMPAIGN")
public class AdCampaign {

    @Id
    @Column(name = "ad_campaign")
    private int campaignId;
    @Column(name = "camp_active")
    private byte isCampaignActive;
    @Column(name = "camp_start_date")
    private Date campaignStartDate;
    @Column(name = "camp_end_date")
    private Date campaignEndEate;
    @Column(name = "camp_max_hit_limit")
    private int campaignMaxHitLimit;

    public AdCampaign() {}

    public AdCampaign(int campaignId, byte isCampaignActive, Date campaignStartDate, Date campaignEndEate, int campaignMaxHitLimit) {
        this.campaignId = campaignId;
        this.isCampaignActive = isCampaignActive;
        this.campaignStartDate = campaignStartDate;
        this.campaignEndEate = campaignEndEate;
        this.campaignMaxHitLimit = campaignMaxHitLimit;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public byte getIsCampaignActive() {
        return isCampaignActive;
    }

    public void setIsCampaignActive(byte isCampaignActive) {
        this.isCampaignActive = isCampaignActive;
    }

    public Date getCampaignStartDate() {
        return campaignStartDate;
    }

    public void setCampaignStartDate(Date campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public Date getCampaignEndEate() {
        return campaignEndEate;
    }

    public void setCampaignEndEate(Date campaignEndEate) {
        this.campaignEndEate = campaignEndEate;
    }

    public int getCampaignMaxHitLimit() {
        return campaignMaxHitLimit;
    }

    public void setCampaignMaxHitLimit(int campaignMaxHitLimit) {
        this.campaignMaxHitLimit = campaignMaxHitLimit;
    }

    @Override
    public String toString() {
        return "AdCampaign [campaignId=" + campaignId + ", isCampaignActive=" + isCampaignActive + ", campaignStartDate=" + campaignStartDate + ", campaignEndEate=" + campaignEndEate + ", campaignMaxHitLimit=" + campaignMaxHitLimit + "]";
    }
}
